package com.example.xiaoqiang.myapplication.designMode.AbstractFactoryPattern;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.xiaoqiang.myapplication.designMode.FactoryPattern.MeizuFactory;
import com.example.xiaoqiang.myapplication.designMode.FactoryPattern.SansungFactory;
import com.example.xiaoqiang.myapplication.designMode.FactoryPattern.XiaoMiFactory;

/**
 * @Author: [xiaoqiang]
 * @Description: [AbstractFactoryPattern]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class AbstractFactoryPattern extends Activity {
    private TextView mTextView;
    private MobileFactory mMobileFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);

        mMobileFactory = new AbstractFactory();
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("通过简单工厂模式创建魅族手机对象\n");
        stringBuffer.append(mMobileFactory.createMeizu().makeCall());
        stringBuffer.append("\n通过简单工厂模式创建小米手机对象\n");
        stringBuffer.append(mMobileFactory.createXiaoMi().makeCall());
        stringBuffer.append("\n通过简单工厂模式创建三星手机对象\n");
        stringBuffer.append(mMobileFactory.createSansung().makeCall());

        mTextView.setText(stringBuffer.toString());

    }
}
