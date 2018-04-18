package com.example.xiaoqiang.myapplication.designMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.xiaoqiang.myapplication.R;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.SimpleFactoryPattern;

/**
 * @Author: [xiaoqiang]
 * @Description: [DesignModeActivity]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class DesignModeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        findViewById(R.id.SimpleFactoryPattern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesignModeActivity.this, SimpleFactoryPattern.class);
                startActivity(intent);
            }
        });
    }
}
