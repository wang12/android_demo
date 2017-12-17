package com.example.xiaoqiang.myapplication;


import android.app.Activity;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.MutableShort;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGL;


/**
 * @Author: [xiaoqiang]
 * @Description: [EGL ]
 * @CreateDate: [2017/12/17]
 * @UpdateDate: [2017/12/17]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class OpenGLEGLActivity extends Activity {
    private SurfaceView mSurfaceView;
    private GLRenderer mRenderer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl_egl);
        mRenderer = new GLRenderer("GLES");
        mRenderer.start();
        initView();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                mRenderer.render(holder.getSurface(), width, height);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRenderer.release();
    }

    class GLRenderer extends HandlerThread {
        private EGLConfig mEglConfig;
        private EGLContext mEglContext;
        private EGLDisplay mEglDisplay;
        private Handler mHandler;
        private int mProgram;
        private int mPostion;
        private int mColor;

        public GLRenderer(String name) {
            super(name);

        }

        private void createEGL() {
            mEglDisplay = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
            int[] version = new int[2];
            if (!EGL14.eglInitialize(mEglDisplay, version, 0, version, 1)) {
                throw new RuntimeException("EGL error " + EGL14.eglGetError());
            }
            int[] configAttr = new int[]{
                    EGL14.EGL_BUFFER_SIZE, 32,
                    EGL14.EGL_ALPHA_SIZE, 8,
                    EGL14.EGL_RED_SIZE, 8,
                    EGL14.EGL_BLUE_SIZE, 8,
                    EGL14.EGL_GREEN_SIZE, 8,
                    EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                    EGL14.EGL_SURFACE_TYPE, EGL14.EGL_WINDOW_BIT,
                    EGL14.EGL_NONE
            };
            int[] numConfig = new int[1];
            EGLConfig[] eglConfigs = new EGLConfig[1];
            if (!EGL14.eglChooseConfig(mEglDisplay, configAttr, 0, eglConfigs,
                    0, eglConfigs.length, numConfig, 0)) {
                throw new RuntimeException("EGL error " + EGL14.eglGetError());
            }
            mEglConfig = eglConfigs[0];

            int[] contextAttr = new int[]{
                    EGL14.EGL_CONTEXT_CLIENT_VERSION, 2,
                    EGL14.EGL_NONE
            };
            mEglContext = EGL14.eglCreateContext(mEglDisplay, mEglConfig, EGL14.EGL_NO_CONTEXT, contextAttr, 0);
            if (mEglContext == EGL14.EGL_NO_CONTEXT) {
                throw new RuntimeException("EGL error " + EGL14.eglGetError());
            }
        }

        private void distroy() {
            EGL14.eglDestroyContext(mEglDisplay, mEglContext);
            mEglContext = EGL14.EGL_NO_CONTEXT;
            mEglDisplay = EGL14.EGL_NO_DISPLAY;
        }

        @Override
        public synchronized void start() {
            super.start();
            mHandler = new Handler(this.getLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    createEGL();
                }
            });
        }

        public synchronized void release() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    distroy();
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler = null;
                    quit();
                }
            });
        }

        private int createShader(int shaderType, String shaderProgrom) {
            int shader = 0;
            shader = GLES20.glCreateShader(shaderType);
            if (shader != 0) {
                GLES20.glShaderSource(shader, shaderProgrom);
                GLES20.glCompileShader(shader);
                int[] compile = new int[1];
                GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compile, 0);
                if (compile[0] == 0) {
                    Log.e("wqq", "shader 编译失败");
                    shader = 0;
                }
            }
            return shader;
        }

        private int createProgram(String vertices, String fragment) {
            int vShader = createShader(GLES20.GL_VERTEX_SHADER, vertices);
            if (vShader == 0) {
                Log.e("wqq", "vertice shader 创建失败");
                return 0;
            }

            int fShader = createShader(GLES20.GL_FRAGMENT_SHADER, fragment);
            if (vShader == 0) {
                Log.e("wqq", "fragment shader 创建失败");
                return 0;
            }
            int program = GLES20.glCreateProgram();
            if (program != 0) {
                GLES20.glAttachShader(program, vShader);
                GLES20.glAttachShader(program, fShader);
                GLES20.glLinkProgram(program);
                int[] like = new int[1];
                GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, like, 0);
                if (like[0] != GLES20.GL_TRUE) { //链接失败
                    Log.e("wqq", "程序链接失败了");
                    GLES20.glDeleteProgram(program);
                    program = 0;
                }
            }
            return program;
        }

        private FloatBuffer getVertice() {
            float vertices[] = {
                    0.0f, 0.5f,
                    -0.5f, -0.5f,
                    0.5f, -0.5f,
            };
            ByteBuffer bBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
            bBuffer.order(ByteOrder.nativeOrder());
            FloatBuffer fBuffer = bBuffer.asFloatBuffer();
            fBuffer.put(vertices);
            fBuffer.position(0);
            return fBuffer;
        }

        public void render(Surface surface, int width, int height) {
            final int[] eglSurfaceAttaer = new int[]{
                    EGL14.EGL_NONE
            };
            EGLSurface mSurface = EGL14.eglCreateWindowSurface(mEglDisplay, mEglConfig, surface, eglSurfaceAttaer, 0);
            EGL14.eglMakeCurrent(mEglDisplay, mSurface, mSurface, mEglContext);

            mProgram = createProgram(vProgram, fProgram);
            mPostion = GLES20.glGetAttribLocation(mProgram, "mPostion");
            mColor = GLES20.glGetUniformLocation(mProgram, "mColor");
            GLES20.glClearColor(1, 0, 0, 1);

            FloatBuffer vertices = getVertice();

            GLES20.glViewport(0, 0, width, height);
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            GLES20.glUseProgram(mProgram);
            GLES20.glVertexAttribPointer(mPostion, 2, GLES20.GL_FLOAT, false, 0, vertices);
            GLES20.glEnableVertexAttribArray(mPostion);
            GLES20.glUniform4f(mColor, 0, 1, 0, 1);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);

            EGL14.eglSwapBuffers(mEglDisplay, mSurface);

            EGL14.eglDestroySurface(mEglDisplay, mSurface);
        }

        private static final String vProgram
                = "attribute vec2 mPostion; \n" +
                "void main(){ \n" +
                "   gl_Position = vec4(mPostion,0,1); \n" +
                "} \n";
        private static final String fProgram
                = "precision mediump float; \n" +
                "uniform vec4 mColor;\n" +
                "void main(){ \n" +
                "gl_FragColor = mColor;\n" +
                "}\n";
//        // 顶点着色器的脚本
//        private static final String verticesShader
//                = "attribute vec2 vPosition;            \n" // 顶点位置属性vPosition
//                + "void main(){                         \n"
//                + "   gl_Position = vec4(vPosition,0,1);\n" // 确定顶点位置
//                + "}";

        // 片元着色器的脚本
//        private static final String fragmentShader
//                = "precision mediump float;         \n" // 声明float类型的精度为中等(精度越高越耗资源)
//                + "uniform vec4 uColor;             \n" // uniform的属性uColor
//                + "void main(){                     \n"
//                + "   gl_FragColor = uColor;        \n" // 给此片元的填充色
//                + "}";
    }


}
