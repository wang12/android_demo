package com.example.xiaoqiang.myapplication.view;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: [xiaoqiang]
 * @Description: [ColorLintDrawable]
 * @CreateDate: [2018/1/5]
 * @UpdateDate: [2018/1/5]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ColorLintDrawable extends Drawable {
    protected Paint mLintPaint;
    protected List<ColorRect> mColorList;

    public ColorLintDrawable() {
        mLintPaint = new Paint();
        mLintPaint.setAntiAlias(true); //消除锯齿
        mLintPaint.setStrokeWidth(1f);
        mLintPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mColorList != null) {
            for (ColorRect scope : mColorList) {
                mLintPaint.setColor(scope.mColor);
                canvas.drawRect(scope.mRectF, mLintPaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mLintPaint.setAlpha(alpha);
    }

    public void clearColor() {
        if (mColorList != null)
            mColorList.clear();
    }

    public void addColor(int color, float start, float end) {
        if (mColorList == null) {
            mColorList = new ArrayList<ColorRect>();
        }
        ColorRect colorRect = null;
        if (mColorList.size() > 0) {
            colorRect = mColorList.get(mColorList.size() - 1);
            if (colorRect.mColor == color && colorRect.mRectF.left == start) {
                colorRect.mRectF.right = end;
            } else {
                colorRect = new ColorRect();
                colorRect.mColor = color;
                colorRect.mRectF = new RectF(start, getBounds().top, end, getBounds().bottom);
                mColorList.add(colorRect);
            }
        } else {
            colorRect = new ColorRect();
            colorRect.mColor = color;
            colorRect.mRectF = new RectF(start, 0, end, getIntrinsicHeight());
            mColorList.add(colorRect);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mLintPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    class ColorRect {
        int mColor;
        RectF mRectF;
    }
}
