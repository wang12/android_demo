package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.xiaoqiang.myapplication.R;
import com.example.xiaoqiang.myapplication.utils.DisplayUtil;

/**
 * Created by xiaoqiang on 2017/10/21.
 */

public class CircularProgress extends View {
    private Paint mPaint;
    private float mCircleWidth = 3;
    private int mIndeterminateColor = Color.TRANSPARENT;
    private int mArcColor = Color.BLACK;
    private float mProgress;
    private float mStartAngle = -45;
    private RectF mOval;

    public CircularProgress(Context context) {
        super(context);
        initParams(null);
    }

    public CircularProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams(attrs);
    }

    public CircularProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(attrs);
    }

    private void initParams(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CircularProgress);
            mCircleWidth = ta.getDimension(R.styleable.CircularProgress_circle_width, 3);
            mIndeterminateColor = ta.getColor(R.styleable.CircularProgress_indeterminate_color, Color.TRANSPARENT);
            mArcColor = ta.getColor(R.styleable.CircularProgress_arc_color, Color.BLACK);
            mStartAngle = ta.getFloat(R.styleable.CircularProgress_start_angle, -45f);
        }

        mOval = null;
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mOval == null) {
            float left;
            float right;
            float top;
            float bottom;
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            int height = getHeight() - getPaddingTop() - getPaddingBottom();
            if (width > height) {
                left = getPaddingLeft() + (width - height) / 2.0f;
                right = getWidth() - getPaddingRight() - (width - height) / 2.0f;
                top = getPaddingTop();
                bottom = getHeight() - getPaddingBottom();
            } else {
                left = getPaddingLeft();
                right = getWidth() - getPaddingRight();
                top = getPaddingTop() + (height - width) / 2.0f;
                bottom = getHeight() - (height - width) / 2.0f - getPaddingBottom();
            }
            mOval = new RectF(left, top, right, bottom);
        }

        mPaint.setColor(mIndeterminateColor);
        canvas.drawArc(mOval, 360 * mProgress, 360, false, mPaint);

        mPaint.setColor(mArcColor);
        canvas.drawArc(mOval, mStartAngle, 360 * mProgress, false, mPaint);
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        postInvalidate();
    }

    public void setmCircleWidth(int mCircleWidth) {
        this.mCircleWidth = mCircleWidth;
    }

    public void setmIndeterminateColor(int mIndeterminateColor) {
        this.mIndeterminateColor = mIndeterminateColor;
    }

    public void setmArcColor(int mArcColor) {
        this.mArcColor = mArcColor;
    }

    public void setmStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
    }
}
