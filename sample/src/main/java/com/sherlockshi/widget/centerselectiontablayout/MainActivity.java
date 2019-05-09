package com.sherlockshi.widget.centerselectiontablayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sherlockshi.widget.BaseItemEntity;
import com.sherlockshi.widget.CenterSelectionTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CenterSelectionTabLayout mCenterSelectionTabLayout;
    private CenterSelectionTabLayout mCenterSelectionTabLayout2;
    private CenterSelectionTabLayout mCenterSelectionTabLayout3;

    private List<BaseItemEntity> mBodyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSampleData();

        mCenterSelectionTabLayout = findViewById(R.id.center_selection_tab_layout);
        mCenterSelectionTabLayout
                .setData(mBodyList)
                .setSelectedPosition(0)
                .setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
                    @Override
                    public void onItemSelect(int position) {
                        Toast.makeText(MainActivity.this, mBodyList.get(position).getItemTitle(), Toast.LENGTH_SHORT).show();
                    }
        }).create();

        mCenterSelectionTabLayout2 = findViewById(R.id.center_selection_tab_layout2);
        mCenterSelectionTabLayout2
                .setData(mBodyList)
                .setSelectedPosition(1)
                .setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
                    @Override
                    public void onItemSelect(int position) {
                        Toast.makeText(MainActivity.this, mBodyList.get(position).getItemTitle(), Toast.LENGTH_SHORT).show();
                    }
        }).create();

        mCenterSelectionTabLayout3 = findViewById(R.id.center_selection_tab_layout3);
        mCenterSelectionTabLayout3
                .setData(mBodyList)
                .setSelectedPosition(2)
                .setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
                    @Override
                    public void onItemSelect(int position) {
                        Toast.makeText(MainActivity.this, mBodyList.get(position).getItemTitle(), Toast.LENGTH_SHORT).show();
                    }
        }).create();
    }

    private void getSampleData() {
        String[] idArray    = new String[]{"001", "002", "003", "004", "005", "006", "007", "008"};
        String[] titleArray = new String[]{"头部", "颈部", "胸部", "腹部", "腰部", "臀部", "上肢", "下肢"};

        for (int i = 0; i < titleArray.length; i++) {
            mBodyList.add(new BodyEntity(idArray[i], titleArray[i]));
        }
    }
}
