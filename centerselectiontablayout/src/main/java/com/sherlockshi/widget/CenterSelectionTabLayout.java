package com.sherlockshi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      SherlockShi
 * Email:       sherlock_shi@163.com
 * Date:        2018-08-18 16:30
 * Description:
 */
public class CenterSelectionTabLayout extends FrameLayout {

    // 默认选中项：下标为1的项（因为第0项为空 View）
    public static final int DEFAULT_SELECTED_POSITION   = 1;

    // 子项默认宽度
    private static final float DEFAULT_ITEM_WIDTH_DP = 80;

    // 默认背景颜色
    private static final int DEFAULT_BACKGROUND_COLOR      = 0xFF196FFA;

    // 选中框默认宽度
    private static final float DEFAULT_SELECTION_BACKGROUND_WIDTH_DP    = 60;

    // 选中框默认高度
    private static final float DEFAULT_SELECTION_BACKGROUND_HEIGHT_DP   = 30;

    // 字体颜色
    private static final int NORMAL_TEXT_COLOR      = 0xFF8FB4FC;
    private static final int SELECTED_TEXT_COLOR    = 0xFFFFFFFF;

    private Context mContext;

    private List<String> mTitleList = new ArrayList<String>();

    private FrameLayout mFrameLayout;
    private RecyclerView mRecyclerView;
    private View mViewSelectionBackground;

    private MyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    // 上一次选中项的下标
    private int mLastSelectedPosition = DEFAULT_SELECTED_POSITION;

    // 子项宽度
    private float mItemWidth;

    // 背景
    private int mBackground;

    // 选中框配置
    private int mSelectionBackground;
    private float mSelectionBackgroundWidth;
    private float mSelectionBackgroundHeight;

    // 字体颜色
    private int mNormalTextColor;
    private int mSelectedTextColor;

    private float mDownX;
    private float mLastX;
    private float mUpX;

    private onItemSelectListener mOnItemSelectListener;

    public interface onItemSelectListener {
        void onItemSelect(int position);
    }

    public CenterSelectionTabLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CenterSelectionTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        setCustomAttrs(context, attrs);

        initView();
    }

    private void setCustomAttrs(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CenterSelectionTabLayout);
        try {
            // 子项宽度
            mItemWidth = a.getDimension(R.styleable.CenterSelectionTabLayout_itemWidth, ScreenUtil.dp2px(mContext, DEFAULT_ITEM_WIDTH_DP));

            // 背景
            mBackground = a.getColor(R.styleable.CenterSelectionTabLayout_backgroundColor, DEFAULT_BACKGROUND_COLOR);

            // 选中框配置
            mSelectionBackground = a.getResourceId(R.styleable.CenterSelectionTabLayout_selectionBackground, R.drawable.bg_selected);
            mSelectionBackgroundWidth = a.getDimension(R.styleable.CenterSelectionTabLayout_selectionBackgroundWidth, ScreenUtil.dp2px(mContext, DEFAULT_SELECTION_BACKGROUND_WIDTH_DP));
            mSelectionBackgroundHeight = a.getDimension(R.styleable.CenterSelectionTabLayout_selectionBackgroundHeight, ScreenUtil.dp2px(mContext, DEFAULT_SELECTION_BACKGROUND_HEIGHT_DP));

            // 正常文字颜色
            mNormalTextColor = a.getColor(R.styleable.CenterSelectionTabLayout_normalTextColor, NORMAL_TEXT_COLOR);

            // 选中文字颜色
            mSelectedTextColor = a.getColor(R.styleable.CenterSelectionTabLayout_selectedTextColor, SELECTED_TEXT_COLOR);
        } finally {
            a.recycle();
        }
    }

    public CenterSelectionTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public void setOnItemSelectListener(onItemSelectListener onItemSelectListener) {
        this.mOnItemSelectListener = onItemSelectListener;
    }

    public void setData(List<String> titleList) {
        this.mTitleList.clear();
        this.mTitleList.add("");
        this.mTitleList.addAll(titleList);
        this.mTitleList.add("");
        mAdapter.setData(titleList);
        mAdapter.notifyDataSetChanged();

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 设置默认选中项
                scrollToPosition(mLastSelectedPosition);
            }
        }, 50);
    }

    public void setScreenWidth(int screenWidth) {
        mAdapter.setScreenWidth(screenWidth);
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_center_selection_tab, this, true);
        mFrameLayout = findViewById(R.id.frame_layout);
        mFrameLayout.setBackgroundColor(mBackground);

        initSelectionBackgroundView();

        initRecyclerView();

        setScreenWidth(ScreenUtil.getScreenWidth(mContext));
    }

    private void initSelectionBackgroundView() {
        mViewSelectionBackground = findViewById(R.id.view_selection_background);

        // 设置宽、高
        mViewSelectionBackground.setLayoutParams(new FrameLayout.LayoutParams((int) mSelectionBackgroundWidth, (int) mSelectionBackgroundHeight));
        FrameLayout.LayoutParams layoutParams = (LayoutParams) mViewSelectionBackground.getLayoutParams();

        // 设置居中
        layoutParams.gravity = Gravity.CENTER;

        mViewSelectionBackground.setLayoutParams(layoutParams);

        // 设置背景
        mViewSelectionBackground.setBackgroundResource(mSelectionBackground);
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new MyAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setItemWidth(mItemWidth);

        // 设置字体颜色
        mAdapter.setNormalTextColor(mNormalTextColor);

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取手指的操作--》按下、移动、松开
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:   //按下
                        mDownX = event.getRawX();
                        mLastX = mDownX;
                        Log.d("OnTouch", "ACTION_DOWN=" + mDownX);
                        break;

                    case MotionEvent.ACTION_MOVE:   //移动
                        float currentMoveX = event.getRawX();
                        float offsetX = currentMoveX - mLastX;
                        Log.d("OnTouch", "ACTION_MOVE=" + (-offsetX));
                        mRecyclerView.scrollBy((int) -offsetX, 0);
                        mLastX = currentMoveX;
                        break;

                    case MotionEvent.ACTION_UP:     //松开
                        mUpX = event.getRawX();
                        Log.d("OnTouch", "ACTION_UP=" + mUpX);
                        if (mDownX == mUpX) {
                            moveClickItemToCenter(mUpX);
                        } else {
                            moveToCenter();
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void moveClickItemToCenter(float clickX) {
        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        for (int i = 0; i <= lastPosition-firstPosition; i++) {
            View view = mRecyclerView.getChildAt(i);

            if (view != null) {
                float tvX = view.getX();

                if ((clickX > tvX) && (clickX <= (tvX + mItemWidth))) {
                    scrollToPosition(firstPosition+i);
                    return;
                }
            }
        }
    }

    private void moveToCenter() {
        // 中心 TextView 的左上角 X 坐标，刚好为第一个空 View 的宽度
        int xCenter = 0;

        // 寻找与 xCenter 距离最小的 View
        float currentSpacing = ScreenUtil.getScreenWidth(mContext);

        int currentIndex = 0;

        View emptyView = mRecyclerView.getChildAt(0);
        xCenter = (ScreenUtil.getScreenWidth(mContext) - (int) mItemWidth) / 2;

        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        for (int i = 0; i <= lastPosition-firstPosition; i++) {
            View view = mRecyclerView.getChildAt(i);

            if (view != null) {
                float tvX = view.getX();

                float spacing = Math.abs(tvX - xCenter);

                if (spacing < currentSpacing) {
                    currentIndex = (firstPosition+i);
                    currentSpacing = Math.abs(tvX - xCenter);
                }
            }
        }

        scrollToPosition(currentIndex);
    }

    public void scrollToPosition(int position) {
        // 前、后两个空 View 点击无效
        if (position <= 0 || (position >= mTitleList.size() - 1)) {
            return;
        }

        int centerX = (ScreenUtil.getScreenWidth(mContext) - (int) mItemWidth) / 2;

        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();

        // 上一个选中的 View
        TextView lastView = (TextView) mRecyclerView.getChildAt(mLastSelectedPosition - firstPosition);
        lastView.setTextColor(mNormalTextColor);

        // 当前选中的 View
        TextView currentView = (TextView) mRecyclerView.getChildAt(position - firstPosition);
        currentView.setTextColor(mSelectedTextColor);

        mLastSelectedPosition = position;

        float currentTvX = currentView.getX();

        mRecyclerView.smoothScrollBy((int) currentTvX - centerX, 0);

        // 前、后有两个空 View，所以 position 需 -1
        if (mOnItemSelectListener != null) {
            mOnItemSelectListener.onItemSelect(position - 1);
        }
    }
}
