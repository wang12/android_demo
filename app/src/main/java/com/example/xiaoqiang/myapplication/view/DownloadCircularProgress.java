package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xiaoqiang.myapplication.R;

/**
 * Created by xiaoqiang on 2017/10/29.
 */

public class DownloadCircularProgress extends CircularProgress {
    public final static int DOWNLOAD_STATUS_INIT = 0;
    public final static int DOWNLOAD_STATUS_START = 1;
    public final static int DOWNLOAD_STATUS_ING = 2;
    public final static int DOWNLOAD_STATUS_END = 3;
    private int mBackColor;
    private Paint mBackPaint;
    private int mDownloadStatus = DOWNLOAD_STATUS_INIT;

    public DownloadCircularProgress(Context context) {
        super(context);
    }

    public DownloadCircularProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DownloadCircularProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initParams(AttributeSet attrs) {
        super.initParams(attrs);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DownloadCircularProgress);
            mBackColor = ta.getColor(R.styleable.DownloadCircularProgress_backgroundColor, -1);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        computerSize();
        if (mDownloadStatus == DOWNLOAD_STATUS_INIT) {
            if (mBackPaint == null) {
                mBackPaint = new Paint();
                mBackPaint.setStrokeWidth(mCircleWidth);
                mBackPaint.setColor(mBackColor);
                mBackPaint.setAntiAlias(true); //消除锯齿
                mBackPaint.setStyle(Paint.Style.FILL);
            }
            float radius = mOval.width() > mOval.height() ? mOval.height() / 2 : mOval.width() / 2;
            canvas.drawCircle(mOval.centerX(), mOval.centerY(), radius, mBackPaint);
            drawBitmap(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    public void setDownloadStatus(int status) {
        mDownloadStatus = status;
        invalidate();
    }
}
