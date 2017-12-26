package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.xiaoqiang.myapplication.view.ColorFulSeekbar;

import static android.content.ContentValues.TAG;

/**
 * @Author: [xiaoqiang]
 * @Description: [SeekbarActivity]
 * @CreateDate: [2017/12/22]
 * @UpdateDate: [2017/12/22]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class SeekbarActivity extends Activity {
    private final static String TAG = SeekbarActivity.class.getName();

    private ColorFulSeekbar mSeekbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        mSeekbar = (ColorFulSeekbar) findViewById(R.id.seek_bar);
        mSeekbar.setOnSeekBarChangeListener(new ColorFulSeekbar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(ColorFulSeekbar seekBar, int progress, boolean fromUser) {
//                Log.e(TAG, "onProgressChanged,progress:" + progress + ",fromUser:" + fromUser);
            }

            @Override
            public void onStartTrackingTouch(ColorFulSeekbar seekBar) {
                Log.e(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(ColorFulSeekbar seekBar) {
                Log.e(TAG, "onStopTrackingTouch");
            }
        });

        findViewById(R.id.add_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(0xff000000, mSeekbar.getProgress() + 1);
            }
        });
        findViewById(R.id.add_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(0xff0000ff, mSeekbar.getProgress() + 1);
            }
        });
        findViewById(R.id.add_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(0xffff0000, mSeekbar.getProgress() + 1);
            }
        });
        findViewById(R.id.add_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(mSeekbar.getProgress() + 5);
            }
        });
        findViewById(R.id.remove_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(mSeekbar.getProgress() - 5);
            }
        });
    }
}
