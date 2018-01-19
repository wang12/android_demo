package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.xiaoqiang.myapplication.view.ThreeLayersButton;

/**
 * @Author: [xiaoqiang]
 * @Description: [ThreeButtonActivity]
 * @CreateDate: [2018/1/19]
 * @UpdateDate: [2018/1/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ThreeButtonActivity extends Activity {
    private ThreeLayersButton mButton;
    int progress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_threebutton);
        mButton = (ThreeLayersButton) findViewById(R.id.btn_three_button);


        findViewById(R.id.btn_set_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress < 100) {
                    mButton.setProgress(++progress);
                }
            }
        });
    }
}
