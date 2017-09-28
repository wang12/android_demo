package com.example.xiaoqiang.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.xiaoqiang.myapplication.adapter.TabFragmentAdapter;
import com.example.xiaoqiang.myapplication.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoqiang on 2017/9/28.
 */

public class BarActivity extends AppCompatActivity {
    private String titles[] ={"我的","呵呵","哈哈","我的","呵呵","哈哈","我的","呵呵","哈哈","我的","呵呵","哈哈"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<Fragment>();

        for (int i = 0; i < titles.length; i++) {
            Fragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text",titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        viewPager.setAdapter(new TabFragmentAdapter(fragments, titles, getSupportFragmentManager(), this));

        TabLayout tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(getResources().getColor(android.R.color.darker_gray), Color.WHITE);
    }
}
