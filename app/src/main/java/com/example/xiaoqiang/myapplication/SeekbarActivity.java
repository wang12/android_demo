package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.xiaoqiang.myapplication.view.EffectSeekBar;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.add_red)
    Button addRed;
    @BindView(R.id.add_blue)
    Button addBlue;
    @BindView(R.id.add_black)
    Button addBlack;
    @BindView(R.id.clear_color)
    Button clearColor;
    @BindView(R.id.start_add_time)
    Button startAddTime;
    @BindView(R.id.stop_add_time)
    Button stopAddTime;
    @BindView(R.id.start_sub_time)
    Button startSubTime;
    @BindView(R.id.stop_sub_time)
    Button stopSubTime;

    int progress = 0;

    private EffectSeekBar mEffectSeekBar;
    private Bitmap[] mBitmaps = new Bitmap[14];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        ButterKnife.bind(this);
        mEffectSeekBar = (EffectSeekBar) findViewById(R.id.effects_seek_bar);
        initSeekBar();
    }


    private void initSeekBar() {
        for (int i = 0; i < mBitmaps.length; i++) {
            mBitmaps[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        }
        mEffectSeekBar.setBackBitmap(mBitmaps);
        mEffectSeekBar.setMax(5000);
    }

    @OnClick(R.id.add_red)
    public void onAddRedClicked() {
        mEffectSeekBar.setColor(0xAAFFF687);
    }

    @OnClick(R.id.add_blue)
    public void onAddBlueClicked() {
        mEffectSeekBar.setColor(0xAA8AC0FF);
    }

    @OnClick(R.id.add_black)
    public void onAddBlackClicked() {
        mEffectSeekBar.setColor(0xAAFF2E4E);
    }

    @OnClick(R.id.clear_color)
    public void onClearColorClicked() {
        mEffectSeekBar.clearColor();
    }

    @OnClick(R.id.start_add_time)
    public void onStartAddTimeClicked() {
        mAddDisposable =  Observable.interval(500,3, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        progress ++;
                        if(progress >=  mEffectSeekBar.getMax()){
                            progress = 0;
                        }
                        mEffectSeekBar.setProgress(progress);
                    }
                });
    }

    private Disposable mAddDisposable;
    private Disposable mSubDisposable;

    @OnClick(R.id.stop_add_time)
    public void onStopAddTimeClicked() {
        if(mAddDisposable != null){
            mAddDisposable.dispose();
        }
        mAddDisposable = null;
    }

    @OnClick(R.id.start_sub_time)
    public void onStartSubTimeClicked() {
        mSubDisposable =  Observable.interval(500,3, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        progress --;
                        if(progress <  0){
                            progress = mEffectSeekBar.getMax();
                        }
                        mEffectSeekBar.setProgress(progress);
                    }
                });
    }

    @OnClick(R.id.stop_sub_time)
    public void onStopSubTimeClicked() {
        if(mSubDisposable != null){
            mSubDisposable.dispose();
        }
        mSubDisposable = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onStopAddTimeClicked();
        onStopSubTimeClicked();
    }
}
