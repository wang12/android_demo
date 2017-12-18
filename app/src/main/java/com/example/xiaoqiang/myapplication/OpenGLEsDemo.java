package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xiaoqiang.myapplication.gl.OpenGLRenderer;

/**
 * @Author: [xiaoqiang]
 * @Description: [OpenGLEs 的简单试例]
 * @CreateDate: [2017/12/16]
 * @UpdateDate: [2017/12/16]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class OpenGLEsDemo extends Activity {
    private GLSurfaceView mGLSurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl);
        initView();
    }

    private void initView() {
        mGLSurface = (GLSurfaceView) findViewById(R.id.gl_surfaceView);
        mGLSurface.setEGLContextClientVersion(2);
        mGLSurface.setRenderer(new OpenGLRenderer());
        mGLSurface.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
