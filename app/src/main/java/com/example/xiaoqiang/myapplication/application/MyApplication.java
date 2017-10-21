package com.example.xiaoqiang.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.DiskLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by xiaoqiang on 2017/8/24.
 */

public class MyApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    private RefWatcher refWatcher;
    private static Object object = new Object();
    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= LeakCanary.install(this);
        CsvFormatStrategy csvFormatStrategy = CsvFormatStrategy.newBuilder().tag("wqq").build();
//        DiskLogAdapter mAdapter = new DiskLogAdapter();
        Logger.addLogAdapter(new DiskLogAdapter(csvFormatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter());

    }
}
