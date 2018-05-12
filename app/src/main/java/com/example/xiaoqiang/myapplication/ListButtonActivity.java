package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @Author: [xiaoqiang]
 * @Description: [ListButtonActivity]
 * @CreateDate: [2018/5/8]
 * @UpdateDate: [2018/5/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ListButtonActivity extends Activity {
    private ScrollView mScrollView;
    protected LinearLayout mListView;
    private LinearLayout.LayoutParams mParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScrollView = new ScrollView(this);
        mListView = new LinearLayout(this);
        mListView.setOrientation(LinearLayout.VERTICAL);

        mScrollView.addView(mListView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        setContentView(mScrollView);
        mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelOffset(R.dimen.button_height));
        mParams.bottomMargin = 10;
        mParams.topMargin = 10;
    }

    protected View addView(String string) {
        Button button = new Button(this);
        button.setText(string);
        mListView.addView(button, mParams);
        return button;
    }
}
