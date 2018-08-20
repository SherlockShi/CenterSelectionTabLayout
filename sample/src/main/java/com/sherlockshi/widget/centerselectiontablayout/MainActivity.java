package com.sherlockshi.widget.centerselectiontablayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sherlockshi.widget.CenterSelectionTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CenterSelectionTabLayout mCenterSelectionTabLayout;
    private CenterSelectionTabLayout mCenterSelectionTabLayout2;
    private CenterSelectionTabLayout mCenterSelectionTabLayout3;

    private List<String> mTitleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] titleArray = new String[]{"头部", "颈部", "胸部", "腹部", "腰部", "臀部", "上肢", "下肢"};

        mTitleList.addAll(Arrays.asList(titleArray));

        mCenterSelectionTabLayout = findViewById(R.id.center_selection_tab_layout);
        mCenterSelectionTabLayout.setData(mTitleList);
        mCenterSelectionTabLayout.setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                Toast.makeText(MainActivity.this, mTitleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        mCenterSelectionTabLayout2 = findViewById(R.id.center_selection_tab_layout2);
        mCenterSelectionTabLayout2.setData(mTitleList);
        mCenterSelectionTabLayout2.setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                Toast.makeText(MainActivity.this, mTitleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        mCenterSelectionTabLayout3 = findViewById(R.id.center_selection_tab_layout3);
        mCenterSelectionTabLayout3.setData(mTitleList);
        mCenterSelectionTabLayout3.setOnItemSelectListener(new CenterSelectionTabLayout.onItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                Toast.makeText(MainActivity.this, mTitleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
