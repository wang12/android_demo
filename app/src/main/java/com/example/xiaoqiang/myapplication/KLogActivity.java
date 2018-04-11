package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: [xiaoqiang]
 * @Description: [KLogActivity]
 * @CreateDate: [2018/2/8]
 * @UpdateDate: [2018/2/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class KLogActivity extends Activity {
    @BindView(R.id.btn_time1)
    Button btnTime1;
    @BindView(R.id.btn_time2)
    Button btnTime2;
    @BindView(R.id.btn_time3)
    Button btnTime3;
    @BindView(R.id.btn_time4)
    Button btnTime4;
    @BindView(R.id.btn_time5)
    Button btnTime5;
    @BindView(R.id.btn_stop_time)
    Button btnStopTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_time1, R.id.btn_time2, R.id.btn_time3, R.id.btn_time4, R.id.btn_time5, R.id.btn_stop_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_time1:
                break;
            case R.id.btn_time2:
                break;
            case R.id.btn_time3:
                break;
            case R.id.btn_time4:
                break;
            case R.id.btn_time5:
                break;
            case R.id.btn_stop_time:
                break;
        }
    }
}
