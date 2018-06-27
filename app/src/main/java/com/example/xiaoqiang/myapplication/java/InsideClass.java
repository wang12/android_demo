package com.example.xiaoqiang.myapplication.java;


import android.os.Handler;

/**
 * @Author: [xiaoqiang]
 * @Description: [InsideClass]
 * @CreateDate: [2018/5/21]
 * @UpdateDate: [2018/5/21]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class InsideClass {

    private String mName;
    private static String arg;

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                InsideClass.this.mName = name;
                mName = name;
            }
        });

    }
}
