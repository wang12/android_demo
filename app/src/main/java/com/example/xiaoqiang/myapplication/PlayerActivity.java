package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.xiaoqiang.myapplication.play.kingsoft.KingSoftActivity;

/**
 * @Author: [xiaoqiang]
 * @Description: [PlayerActivity]
 * @CreateDate: [2018/5/8]
 * @UpdateDate: [2018/5/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class PlayerActivity extends ListButtonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView("金山云播放").setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerActivity.this, KingSoftActivity.class);
                startActivity(intent);
            }
        });
    }
}
