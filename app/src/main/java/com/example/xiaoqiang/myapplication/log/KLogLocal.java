package com.example.xiaoqiang.myapplication.log;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.io.File;

/**
 * @Author: [xiaoqiang]
 * @Description: [KLogLocal]
 * @CreateDate: [2018/2/8]
 * @UpdateDate: [2018/2/8]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class KLogLocal {

    private static final int MIN_SD_FREE = 10;//保证SD卡最小有十兆的大小

    public static String getLogLocalPath(Context context) {
        if (getSDFreeSize() < MIN_SD_FREE) {
            return "";
        }

        String localPath;
        if (Environment.getExternalStorageDirectory().equals(android.os.Environment.MEDIA_MOUNTED)) {
            localPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "Android" + File.separator + context.getPackageName()
                    + File.separator + "log";
        } else {
            localPath = context.getCacheDir().getAbsolutePath();
            if (TextUtils.isEmpty(localPath)) {
                localPath = "/data/data/" + context.getPackageName() + "/log/";
            }
        }
        if (!TextUtils.isEmpty(localPath)) {
            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return localPath;
    }


    private static long getSDFreeSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    private void createDataFile(String filePath){

    }

    public void writeLog() {

    }

    public boolean getSDPermission(Context context) {
        int write = ActivityCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (write != PackageManager.PERMISSION_GRANTED &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return false;
        }
        return true;
    }
}
