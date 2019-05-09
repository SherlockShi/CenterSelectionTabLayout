package com.sherlockshi.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      SherlockShi
 * Email:       sherlock_shi@163.com
 * Date:        2018-08-18 20:47
 * Description: 多布局（https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type）
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int EMPTY   = 1;
    public static final int CONTENT = 2;

    private Context mContext;
    private List<BaseItemEntity> mTitleList = new ArrayList<BaseItemEntity>();
    private int mScreenWidth;
    private float mItemWidth;

    // 字体颜色
    private int mNormalStateTextColor;

    // 字体大小
    private float mNormalStateTextSize;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BaseItemEntity> titleList) {
        // 第0项和 size-1 项为空 View
        this.mTitleList.clear();
        this.mTitleList.add(null);
        this.mTitleList.addAll(titleList);
        this.mTitleList.add(null);
    }

    public void setScreenWidth(int screenWidth) {
        this.mScreenWidth = screenWidth;
    }

    public void setItemWidth(float itemWidth) {
        this.mItemWidth = itemWidth;
    }

    public void setNormalStateTextColor(int normalStateTextColor) {
        this.mNormalStateTextColor = normalStateTextColor;
    }

    public void setNormalStateTextSize(float normalStateTextSize) {
        this.mNormalStateTextSize = normalStateTextSize;
    }

    @Override
    public int getItemViewType(int position) {
        // 1. RecyclerView 的第0项和 size-1 项为空 View
        // 2. 第1项到第 size-2 项才是真正的内容
        if (position == 0 || position == mTitleList.size() - 1) {
            return EMPTY;
        } else if (position > 0 && position <= mTitleList.size()) {
            return CONTENT;
        } else {
            return EMPTY;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View emptyView      = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false);
        View contentView    = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_tab, parent, false);

        switch (viewType) {
            case EMPTY:     return new ViewHolderEmpty(emptyView);
            case CONTENT:   return new ViewHolderContent(contentView);
            default:        return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case EMPTY:
                // 为了使首项和末项内容居中，故在前、后增加了2个空View，空View的宽度为 (屏幕宽度 - 固定的TextView宽度) / 2
                int emptyViewWidth = (mScreenWidth - (int) mItemWidth) / 2;

                ViewHolderEmpty viewHolderEmpty = (ViewHolderEmpty) holder;
                viewHolderEmpty.view.setLayoutParams(new LinearLayout.LayoutParams(emptyViewWidth, 1));
                break;

            case CONTENT:
                ViewHolderContent viewHolderContent = (ViewHolderContent) holder;
                viewHolderContent.textView.setText(mTitleList.get(position).getItemTitle());
                viewHolderContent.textView.setTextColor(mNormalStateTextColor);
                viewHolderContent.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mNormalStateTextSize);
                viewHolderContent.textView.setLayoutParams(new LinearLayout.LayoutParams((int) mItemWidth, ViewGroup.LayoutParams.MATCH_PARENT));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTitleList.size();
    }

    class ViewHolderEmpty extends RecyclerView.ViewHolder {

        View view;

        public ViewHolderEmpty(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_empty);
        }
    }

    class ViewHolderContent extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolderContent(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
