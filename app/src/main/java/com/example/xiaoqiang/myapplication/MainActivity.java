package com.example.xiaoqiang.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xiaoqiang.myapplication.application.MyApplication;

public class MainActivity extends AppCompatActivity {
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
