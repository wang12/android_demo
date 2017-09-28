package com.example.xiaoqiang.myapplication.application;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.DiskLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by xiaoqiang on 2017/8/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CsvFormatStrategy csvFormatStrategy = CsvFormatStrategy.newBuilder().tag("wqq").build();
//        DiskLogAdapter mAdapter = new DiskLogAdapter();
        Logger.addLogAdapter(new DiskLogAdapter(csvFormatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
