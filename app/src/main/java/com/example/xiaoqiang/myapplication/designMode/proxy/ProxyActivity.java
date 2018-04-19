package com.example.xiaoqiang.myapplication.designMode.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * @Author: [xiaoqiang]
 * @Description: [ProxyActivity]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ProxyActivity extends Activity {
    private TextView mTextView;

    private GameProxy mGameProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);
        mGameProxy = new GameProxy();
        mTextView.setText(mGameProxy.play());

    }
}
