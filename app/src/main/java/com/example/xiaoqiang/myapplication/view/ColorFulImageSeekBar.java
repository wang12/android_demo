package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @Author: [xiaoqiang]
 * @Description: [ColorFulImageSeekBar]
 * @CreateDate: [2018/1/5]
 * @UpdateDate: [2018/1/5]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ColorFulImageSeekBar extends ColorFulSeekbar {
    private Bitmap[] mBackBitmaps;
    private Rect mSrc;
    private Rect mDst;
    private int mBitmapWidth;
    private Paint mBitmapPaint;

    public ColorFulImageSeekBar(Context context) {
        super(context);
        init();
    }

    public ColorFulImageSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorFulImageSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmapPaint = new Paint();
    }

    @Override
    protected synchronized void drawBack(Canvas canvas) {
        if (mBackBitmaps == null || mBackBitmaps.length <= 0 || mDst == null || mSrc == null)
            return;
        for (int i = 0; i < mBackBitmaps.length; i++) {
            mDst.left = i * mBitmapWidth + mStartPoint.x;
            mDst.right = mDst.left + mBitmapWidth;
            mDst.right = (mDst.right > mEndPoint.x)?mEndPoint.x:mDst.right;
            canvas.drawBitmap(mBackBitmaps[i], mSrc, mDst, mBitmapPaint);
        }
    }
    @Override
    protected synchronized void drawColorLint(Canvas canvas) {
        if (mColorList != null) {
            for (ColorScope scope : mColorList) {
                mLintRect.left = progressToPx(scope.mStartProgress) + getPaddingLeft();
                mLintRect.right = progressToPx(scope.mEndProgress) + getPaddingLeft();
                mLintPaint.setColor(scope.mColor);
                canvas.drawRect(mLintRect,mLintPaint);
            }
        }
    }


    @Override
    protected void onInitDraw() {
        super.onInitDraw();
        if (mBackBitmaps == null || mBackBitmaps.length <= 0 || mEndPoint == null || mStartPoint == null)
            return;

        mBitmapWidth = (mEndPoint.x - mStartPoint.x) / mBackBitmaps.length;
        if (mBitmapWidth > 0 && mMaxHeight > 0) {
            mSrc = new Rect(0, 0, (mBackBitmaps[0].getHeight() / mMaxHeight) * mBitmapWidth,
                    mBackBitmaps[0].getHeight());
            int mHeight = getHeight() - getPaddingTop() - getPaddingBottom();
            int top = getPaddingTop() + (mHeight - mMaxHeight) / 2;
            mDst = new Rect(0, top, mBitmapWidth, top + mMaxHeight);
        }
    }

    public void setBackBitmap(Bitmap[] bitmap) {
        if (bitmap == null || bitmap.length <= 0) return;
        mBackBitmaps = bitmap;
    }

}

