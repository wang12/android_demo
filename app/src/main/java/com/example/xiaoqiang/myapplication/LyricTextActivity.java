package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.example.xiaoqiang.myapplication.view.LyricTextView;

/**
 * Created by xiaoqiang on 2017/9/28.
 */

public class LyricTextActivity extends Activity {
    private SeekBar mSeekBar;
    private SwitchCompat mSwitch;
    private LyricTextView mLyricTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_lyric_text);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSwitch = (SwitchCompat) findViewById(R.id.switch_com);
        mLyricTextView = (LyricTextView) findViewById(R.id.lyric);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLyricTextView.setProgress(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mLyricTextView.setDirection(LyricTextView.RIGHT);
                }else{
                    mLyricTextView.setDirection(LyricTextView.LEFT);
                }
            }
        });
    }
}
