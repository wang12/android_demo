package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

/**
 * Created by xiaoqiang on 2017/9/28.
 */

public class TabGradientViewLayout extends TabLayout {
    public TabGradientViewLayout(Context context) {
        this(context,null);
    }

    public TabGradientViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabGradientViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
