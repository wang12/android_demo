package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.example.xiaoqiang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [xiaoqiang]
 * @Description: [ColorSeekbar]
 * @CreateDate: [2017/12/27]
 * @UpdateDate: [2017/12/27]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ColorSeekbar extends android.support.v7.widget.AppCompatSeekBar {
    private List<ColorScope> mColorList;
    private Paint mLintPaint;
    private RectF mLintRect;
    private int mHeight;
    private int mWidth;
    private int mMaxHeight;
    private int mMinHeight;
    private int mBackground;
    private RectF mBackRect;

    public ColorSeekbar(Context context) {
        this(context, null);
    }

    public ColorSeekbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLintPaint = new Paint();
        mLintPaint.setAntiAlias(true); //消除锯齿
        mLintPaint.setStrokeWidth(1f);
        mLintPaint.setStyle(Paint.Style.FILL);
        loadAttribute(attrs);
    }


    private final void loadAttribute(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorSeekbar);
            mBackground = typedArray.getColor(R.styleable.ColorSeekbar_ColorBackground, 0x99000000);
            mMaxHeight = (int) typedArray.getDimension(R.styleable.ColorSeekbar_ColorMaxHeight, mMaxHeight);
            mMinHeight = (int) typedArray.getDimension(R.styleable.ColorSeekbar_ColorMinHeight, mMinHeight);
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mLintPaint.setColor(mBackground);
        canvas.drawRoundRect(mBackRect, mHeight / 2, mHeight / 2, mLintPaint);
        drawColorLint(canvas);
        super.onDraw(canvas);
    }

    public synchronized void setProgressOffset(int color) {
        setProgressOffset(color, 1);
    }

    private synchronized void setProgressOffset(int color, int offset) {
        int progress = getProgress();
        if (progress + offset <= getMax()) {
            if (mColorList == null) {
                mColorList = new ArrayList<ColorScope>();
                mColorList.add(new ColorScope(color, getProgress(), getProgress()));
            }
            ColorScope colorScope = mColorList.get(mColorList.size() - 1);
            if (colorScope.mColor != color || colorScope.mEndProgress != progress) {
                colorScope = new ColorScope(color, getProgress(), getProgress());
                mColorList.add(colorScope);
            }
            progress = progress + offset;
            colorScope.mEndProgress = progress;
            setProgress(progress);
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

//        mLintRect = new RectF(getPaddingLeft(), getPaddingTop(),
//                getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());

        mBackRect = new RectF(getPaddingLeft(), getHeight() / 2 - mHeight / 2,
                getWidth() - getPaddingRight(), getHeight() / 2 + mHeight / 2);
        mHeight = Math.max(mMinHeight, Math.min(mMaxHeight, getHeight()));

        mLintRect = new RectF(getPaddingLeft(), getHeight() / 2 - mHeight / 2,
                getWidth() - getPaddingRight(), getHeight() / 2 + mHeight / 2);
        mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private synchronized void drawColorLint(Canvas canvas) {
        if (mColorList != null) {
            for (ColorScope scope : mColorList) {
                mLintRect.left = progressToPx(scope.mStartProgress) + getPaddingLeft();
                mLintRect.right = progressToPx(scope.mEndProgress) + getPaddingLeft();
                mLintPaint.setColor(scope.mColor);
                canvas.drawRoundRect(mLintRect, mHeight / 2, mHeight / 2, mLintPaint);
            }
        }
    }

    public class ColorScope {
        public ColorScope(int color, int startProgress, int endProgress) {
            this.mColor = color;
            this.mStartProgress = startProgress;
            this.mEndProgress = endProgress;
        }

        public int mColor;
        public int mStartProgress;
        public int mEndProgress;
    }

    private float progressToPx(int progress) {
        float left = (float) progress / getMax() * mWidth + getPaddingLeft();
        if (left < getPaddingLeft()) {
            return getPaddingLeft();
        } else if (left > (mWidth + getPaddingLeft())) {
            return mWidth + getPaddingLeft();
        }
        return left;
    }

}
