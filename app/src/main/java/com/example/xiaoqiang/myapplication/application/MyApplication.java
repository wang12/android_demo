package com.example.xiaoqiang.myapplication.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

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

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.e("wqq","onActivityCreated:"+activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e("wqq","onActivityStarted:"+activity);

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e("wqq","onActivityResumed:"+activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e("wqq","onActivityPaused:"+activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e("wqq","onActivityStopped:"+activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.e("wqq","onActivitySaveInstanceState:"+activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e("wqq","onActivityDestroyed:"+activity);
            }
        });

    }
}
