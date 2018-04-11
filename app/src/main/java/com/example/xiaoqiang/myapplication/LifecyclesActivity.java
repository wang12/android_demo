package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaoqiang.myapplication.lifecycles.LoginObserver;

/**
 * @Author: [xiaoqiang]
 * @Description: [LifecyclesActivity]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class LifecyclesActivity extends Activity implements LifecycleOwner {
    private TextView mTextView;
    private Button mButton;
    private LifecycleRegistry mLifecycleRegistry;

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
                } else {
                }
                v.setActivated(!v.isActivated());
            }
        });

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.addObserver(new LoginObserver());
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
