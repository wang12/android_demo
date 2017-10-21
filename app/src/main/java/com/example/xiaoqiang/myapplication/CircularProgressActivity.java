package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xiaoqiang.myapplication.view.CircularProgress;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by xiaoqiang on 2017/10/21.
 */

public class CircularProgressActivity extends Activity {
    private CircularProgress mCircularProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCircularProgress = new CircularProgress(this);
        setContentView(R.layout.activity_circular_progress);
        mCircularProgress = (CircularProgress) findViewById(R.id.btn_circular_progress);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<=100;i++){
                    mCircularProgress.setProgress(i / 100f);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        Observable.range(0, 100).delay(1, TimeUnit.MICROSECONDS).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                mCircularProgress.setProgress(integer / 100f);
//            }
//        });
    }
}
