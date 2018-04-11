package com.example.xiaoqiang.myapplication.log;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: [xiaoqiang]
 * @Description: [支持日志本地化，支持删除设置单个日志包大小，支持最大保留日志包大小，支持捕获异常]
 * @CreateDate: [2018/2/8]
 * @UpdateDate: [2018/2/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class KLog {

    public static final int LOGD = 0;
    public static final int LOGV = 1;
    public static final int LOGI = 2;
    public static final int LOGW = 3;
    public static final int LOGE = 4;
    public static final int LogN = 5;


    private static AtomicBoolean mIsSaveLocal = new AtomicBoolean(true);

    private static AtomicInteger mSaveLocalLevel = new AtomicInteger(LOGD);

    private static AtomicInteger mLogLevel = new AtomicInteger(LOGD);

    private static String mLocalPath;


    public static void setLogSaveLocal(boolean isSave) {
        mIsSaveLocal.set(isSave);
    }

    public static void setLogSaveLocalLevel(int level) {
        mSaveLocalLevel.set(level);
    }

    public static void setLogLevel(int level) {
        mLogLevel.set(level);
    }
    public static void setLogLocalPath(Context context, String path){
        mLocalPath = path;
    }
    public static void setLocalMaxSize(int MB){

    }

    public static void LogD(String tag, String message) {
        if (mLogLevel.get() <= LOGD) {
            Log.d(tag, message);
        }
    }

    public static void LogV(String tag, String message) {
        if(mLogLevel.get() <= LOGV) {
            Log.v(tag, message);
        }
    }

    public static void LogI(String tag, String message) {
        if(mLogLevel.get() <= LOGI) {
            Log.i(tag, message);
        }
    }

    public static void LogW(String tag, String message) {
        if(mLogLevel.get() <= LOGW) {
            Log.w(tag, message);
        }
    }

    public static void LogE(String tag, String message) {
        if(mLogLevel.get() <= LOGE) {
            Log.e(tag, message);
        }
    }

}
