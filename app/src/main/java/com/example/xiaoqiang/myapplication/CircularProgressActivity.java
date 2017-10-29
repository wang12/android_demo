package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.xiaoqiang.myapplication.view.CircularProgress;
import com.example.xiaoqiang.myapplication.view.DownloadCircularProgress;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by xiaoqiang on 2017/10/21.
 */

public class CircularProgressActivity extends Activity {
    private CircularProgress mCircularProgress;
    private DownloadCircularProgress downloadCircularProgress;
    private int mClick = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCircularProgress = new CircularProgress(this);
        setContentView(R.layout.activity_circular_progress);
        mCircularProgress = (CircularProgress) findViewById(R.id.btn_circular_progress);

        mCircularProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            mCircularProgress.setProgress(i / 100f, i + "%");
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
        downloadCircularProgress = (DownloadCircularProgress) findViewById(R.id.btn_download_circular_progress);
        downloadCircularProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mClick == 0) {
                    downloadCircularProgress.setDownloadStatus(DownloadCircularProgress.DOWNLOAD_STATUS_START);
                } else if (mClick == 1) {
                    downloadCircularProgress.setDownloadStatus(DownloadCircularProgress.DOWNLOAD_STATUS_ING);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i <= 100; i++) {
                                downloadCircularProgress.setProgress(i / 100f);
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                } else if (mClick == 2) {
                    downloadCircularProgress.setDownloadStatus(DownloadCircularProgress.DOWNLOAD_STATUS_START);
                }
                mClick++;
                if (mClick > 2) mClick = 0;
            }
        });

//        Observable.range(0, 100).delay(1, TimeUnit.MICROSECONDS).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                mCircularProgress.setProgress(integer / 100f);
//            }
//        });
    }
}
