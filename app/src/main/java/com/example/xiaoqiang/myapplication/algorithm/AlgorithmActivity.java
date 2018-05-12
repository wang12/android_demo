package com.example.xiaoqiang.myapplication.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.xiaoqiang.myapplication.R;

import java.util.Arrays;

/**
 * @Author: [xiaoqiang]
 * @Description: [AlgorithmActivity]
 * @CreateDate: [2018/4/28]
 * @UpdateDate: [2018/4/28]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class AlgorithmActivity extends Activity {

    private TextView mDataView;
    private float[] mDatas  = new float[]{16,7,3,20,17,8};
    private StringBuilder mString = new StringBuilder(100);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        mDataView = findViewById(R.id.tv_data);
        mString.append("原数组："+Arrays.toString(mDatas)+"\n");
        mDataView.setText(mString.toString());

        findViewById(R.id.HeapSort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mString.append("堆排序:"+HeapSort.sort(mDatas));
                mDataView.setText(mString.toString());
            }
        });
    }
}
