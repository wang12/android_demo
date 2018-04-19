package com.example.xiaoqiang.myapplication.designMode.builder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * @Author: [xiaoqiang]
 * @Description: [BuilderActivity]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class BuilderActivity extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("通过建造者模式创建魅族手机对象\n");
        stringBuffer.append(new Mobile.Builder().bindCell("魅族").bindCell("魅族").bindExterior("红色").build().toString());
        stringBuffer.append("\n通过建造者模式创建小米手机对象\n");
        stringBuffer.append(new Mobile.Builder().bindCell("小米").bindCell("小米").bindExterior("蓝色").build().toString());

        mTextView.setText(stringBuffer.toString());

    }
}
