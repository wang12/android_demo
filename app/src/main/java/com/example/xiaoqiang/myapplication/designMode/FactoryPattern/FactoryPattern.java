package com.example.xiaoqiang.myapplication.designMode.FactoryPattern;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobileFactory;

/**
 * @Author: [xiaoqiang]
 * @Description: [FactoryPattern]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class FactoryPattern extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("通过简单工厂模式创建魅族手机对象\n");
        stringBuffer.append(new MeizuFactory().createMobilePhone().makeCall());
        stringBuffer.append("\n通过简单工厂模式创建小米手机对象\n");
        stringBuffer.append(new XiaoMiFactory().createMobilePhone().makeCall());
        stringBuffer.append("\n通过简单工厂模式创建三星手机对象\n");
        stringBuffer.append(new SansungFactory().createMobilePhone().makeCall());

        mTextView.setText(stringBuffer.toString());

    }

}
