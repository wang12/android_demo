package com.example.xiaoqiang.myapplication.gl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Author: [xiaoqiang]
 * @Description: [OpenGLRenderer]
 * @CreateDate: [2017/12/16]
 * @UpdateDate: [2017/12/16]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = OpenGLRenderer.class.getName();
    private int mProgram;
    private int mPostion;
    private int mColor;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mProgram = createProgram(mVerticesShader, mFragmentShader);
        mPostion = GLES20.glGetAttribLocation(mProgram, "vPosition");
        mColor = GLES20.glGetUniformLocation(mProgram, "uColor");
        GLES20.glClearColor(1.0f, 0, 0, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        FloatBuffer mVertex = getVertex();
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(mProgram);
        GLES20.glVertexAttribPointer(mPostion, 2, GLES20.GL_FLOAT, false, 0, mVertex);
        GLES20.glEnableVertexAttribArray(mPostion);
        GLES20.glUniform4f(mColor, 0f, 0f, 1f, 1f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);

    }

    private int loadShader(int shaderMode, String srcCode) {
        int shader = GLES20.glCreateShader(shaderMode);
        if (shader != 0) {
            GLES20.glShaderSource(shader, srcCode);
            GLES20.glCompileShader(shader);
            int[] compile = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compile, 0);
            if (compile[0] == 0) {
                Log.e(TAG, "Could not link program: ");
                Log.e(TAG, GLES20.glGetProgramInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    private int createProgram(String vertexSrc, String fragmentSrc) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSrc);
        if (vertexShader == 0) return 0;
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSrc);
        if (fragmentShader == 0) return 0;
        int program = GLES20.glCreateProgram();
        if (program != 0) {
            GLES20.glAttachShader(program, vertexShader);
            GLES20.glAttachShader(program, fragmentShader);
            GLES20.glLinkProgram(program);
            int[] like = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, like, 0);
            if (like[0] == 0) {
                Log.e(TAG, "link gl program error");
                Log.e(TAG, GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    private FloatBuffer getVertex() {
        float vertices[] = {
                0.0f, 0.5f,
                -0.5f, -0.5f,
                0.5f, -0.5f,
        };
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(vertices);
        floatBuffer.position(0);
        return floatBuffer;
    }

    // 顶点着色器的脚本
    private static final String mVerticesShader
            = "attribute vec2 vPosition;            \n" // 顶点位置属性vPosition
            + "void main(){                         \n"
            + "   gl_Position = vec4(vPosition,0,1);\n" // 确定顶点位置
            + "}";

    // 片元着色器的脚本
    private static final String mFragmentShader
            = "precision mediump float;         \n" // 声明float类型的精度为中等(精度越高越耗资源)
            + "uniform vec4 uColor;             \n" // uniform的属性uColor
            + "void main(){                     \n"
            + "   gl_FragColor = uColor;        \n" // 给此片元的填充色
            + "}";
}
