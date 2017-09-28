package com.example.xiaoqiang.myapplication.jni;

/**
 * Created by xiaoqiang on 2017/9/27.
 */

public class LibX264 {
    private  native void init();
    private  native byte[] encoded(byte[] data,int width,int height);
    private native void release();

    public LibX264(){
        init();
    }
    public byte[] encodedNV21(byte[] data,int width,int height){
        return encoded(data,width,height);
    }
    public void  destroy(){
        release();
    }
    static {
        System.loadLibrary("native-lib");
    }
}
