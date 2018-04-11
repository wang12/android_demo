package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaoqiang.myapplication.mvp.BaseActivity;
import com.example.xiaoqiang.myapplication.mvp.BasePresenter;
import com.example.xiaoqiang.myapplication.mvp.login.LoginController;
import com.example.xiaoqiang.myapplication.mvp.login.LoginPresenter;

/**
 * @Author: [xiaoqiang]
 * @Description: [MVPActivity]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class MVPActivity extends BaseActivity<LoginPresenter> implements LoginController {

    private TextView mTextView;
    private Button mButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isActivated()) {
                    mPresenter.logout();
                } else {
                    mPresenter.login("xiaoqiang", "xiaoqiang");
                }
                v.setActivated(!v.isActivated());
            }
        });
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showData(Object t) {
        mTextView.setText("登录状态：" + t);
        if ((boolean) t) {
            mButton.setText("登出");
        } else {
            mButton.setText("登录");
        }
    }

    @Override
    public void showTest() {

    }
}
