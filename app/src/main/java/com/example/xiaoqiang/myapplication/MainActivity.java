package com.example.xiaoqiang.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.open_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BarActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.open_lyric_text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradientTextActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.open_gradien_text_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradientTitleBarActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxJava2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_circular_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CircularProgressActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_opengl_es).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenGLEsDemo.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_opengl_egl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenGLEGLActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_opengl_egl_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenGLEGLSurfaceActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_seek_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeekbarActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_three_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreeButtonActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_log_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KLogActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_mvp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MVPActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
