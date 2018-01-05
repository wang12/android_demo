package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.xiaoqiang.myapplication.view.ColorFulImageSeekBar;
import com.example.xiaoqiang.myapplication.view.ColorFulSeekbar;
import com.example.xiaoqiang.myapplication.view.ColorSeekbar;

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
    private ColorSeekbar mColorSeek;
    private ColorFulImageSeekBar mImageSeekbar;
    private Handler mHandler;
    private int mColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        mSeekbar = (ColorFulSeekbar) findViewById(R.id.seek_bar);
        mColorSeek = (ColorSeekbar) findViewById(R.id.color_seek_bar);
        mImageSeekbar = (ColorFulImageSeekBar) findViewById(R.id.image_seek_bar);
        mImageSeekbar.setOnSeekBarChangeListener(new ColorFulSeekbar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(ColorFulSeekbar seekBar, int progress, boolean fromUser) {
                Log.e(TAG, "onProgressChanged,progress:" + progress + ",fromUser:" + fromUser);
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        Bitmap[] bitmaps = new Bitmap[14];
        for (int i =0;i<bitmaps.length ;i++){
            bitmaps[i] = bitmap;
        }
        mImageSeekbar.setBackBitmap(bitmaps);

//        mColorSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.e(TAG, "onProgressChanged,progress:" + progress + ",fromUser:" + fromUser);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.e(TAG, "onStartTrackingTouch");
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.e(TAG, "onStopTrackingTouch");
//            }
//        });

        findViewById(R.id.add_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgressOffset(0xff000000);
                mColorSeek.setProgressOffset(0xff000000);
                mImageSeekbar.setProgressOffset(0x66000000);
            }
        });
        findViewById(R.id.add_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgressOffset(0xff0000ff);
                mColorSeek.setProgressOffset(0xff0000ff);
                mImageSeekbar.setProgressOffset(0x660000ff);
            }
        });
        findViewById(R.id.add_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgressOffset(0xffff0000);
                mColorSeek.setProgressOffset(0xffff0000);
                mImageSeekbar.setProgressOffset(0x66ff0000);
            }
        });
        findViewById(R.id.add_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(mSeekbar.getProgress() + 5);
                mColorSeek.setProgress(mColorSeek.getProgress() + 5);
                mImageSeekbar.setProgress(mImageSeekbar.getProgress() + 5);
            }
        });
        findViewById(R.id.remove_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setProgress(mSeekbar.getProgress() - 5);
                mColorSeek.setProgress(mColorSeek.getProgress() - 5);
                mImageSeekbar.setProgress(mImageSeekbar.getProgress() - 5);
            }
        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if( mHandler == null){
                    mHandler = new Handler();
                    mColor = 0x990000ff;
                    mHandler.postDelayed(mDelayedRun,100);
                    ((Button)v).setText("关闭定时刷新");
                }else{
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler = null;
                    ((Button)v).setText("开启定时刷新");
                }
            }
        });
    }
    private Runnable mDelayedRun = new Runnable() {
        @Override
        public void run() {
            mColor++;
            mSeekbar.setProgressOffset(mColor);
            mColorSeek.setProgressOffset(mColor);
            mImageSeekbar.setProgressOffset(mColor);
            mHandler.postDelayed(mDelayedRun,100);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
