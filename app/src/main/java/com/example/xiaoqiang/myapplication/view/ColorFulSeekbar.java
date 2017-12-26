package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.xiaoqiang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [xiaoqiang]
 * @Description: [一个多彩的Seekbar的绘制]
 * @CreateDate: [2017/12/22]
 * @UpdateDate: [2017/12/22]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 * 1. 先作出一个最简单的Seekbar。按照默认的Seekbar的功能
 */

public class ColorFulSeekbar extends View {
    private static final String TAG = ColorFulSeekbar.class.getName();
    private Drawable mThumbDrawable;
    private int mBackground;
    private Context mContext;
    private Paint mLintPaint;
    boolean mIsUserSeekable = true;
    private int mThumbHeight;
    private int mThumbWidth;
    private Point mStartPoint;
    private Point mEndPoint;

    int mMinWidth;
    int mMaxWidth;
    int mMinHeight;
    int mMaxHeight;
    private boolean mIsTouch;
    private float mTouchX;
    private float mInitTouchX;
    private int mMax;
    private int mProgress;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;
    private List<ColorScope> mColorList;

    public ColorFulSeekbar(Context context) {
        this(context, null);
    }

    public ColorFulSeekbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFulSeekbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initParams();
        loadAttribute(attrs);
        init();
        Log.e(TAG, "ColorFulSeekbar: ");
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        mOnSeekBarChangeListener = l;
    }

    private void initParams() {
        mMinWidth = 24;
        mMaxWidth = 48;
        mMinHeight = 24;
        mMaxHeight = 48;
        mMax = 100;
        mProgress = 0;
    }

    private final void loadAttribute(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorFulSeekbar);
            Drawable mThumb = typedArray.getDrawable(R.styleable.ColorFulSeekbar_cThumbTint);
            if (mThumb != null) {
                mThumbDrawable = mThumb;
            }
            mBackground = typedArray.getColor(R.styleable.ColorFulSeekbar_cBackground, 0x99000000);
            mMaxHeight = (int) typedArray.getDimension(R.styleable.ColorFulSeekbar_cMaxHeight, mMaxHeight);
            mMinHeight = (int) typedArray.getDimension(R.styleable.ColorFulSeekbar_cMinHeight, mMinHeight);
            mMaxWidth = (int) typedArray.getDimension(R.styleable.ColorFulSeekbar_cMaxWidth, mMaxWidth);
            mMinWidth = (int) typedArray.getDimension(R.styleable.ColorFulSeekbar_cMinWidth, mMinWidth);
            mMax = (int) typedArray.getInteger(R.styleable.ColorFulSeekbar_cMax, mMax);
            mProgress = (int) typedArray.getInteger(R.styleable.ColorFulSeekbar_cProgress, mProgress);
            typedArray.recycle();
        }
    }

    private final void init() {
        mLintPaint = new Paint();
        mLintPaint.setColor(mBackground);
        mLintPaint.setAntiAlias(true); //消除锯齿
        mLintPaint.setStyle(Paint.Style.STROKE);
        mLintPaint.setStrokeWidth(mMaxHeight);

        if (mThumbDrawable == null) {
            mThumbDrawable = new ShapeDrawable(new OvalShape());
            ((ShapeDrawable) mThumbDrawable).getPaint().setColor(0xffffffff);
            ((ShapeDrawable) mThumbDrawable).setIntrinsicWidth(dip2px(20));
            ((ShapeDrawable) mThumbDrawable).setIntrinsicHeight(dip2px(20));
        }
    }

    public int getProgress() {
        return mProgress;
    }

    public synchronized void setProgress(int color, int progress) {
        if (progress > 0 && progress < mMax) {
            if (mColorList == null) {
                mColorList = new ArrayList<ColorScope>();
                mColorList.add(new ColorScope(color, mProgress));
            }
            ColorScope colorScope = mColorList.get(mColorList.size() - 1);
            if (colorScope.mColor != color) {
                colorScope = new ColorScope(color, mProgress);
                mColorList.add(colorScope);
            }
            colorScope.mEndProgress = progress;
            moveThumb(progress);
            invalidate();
        }
    }

    public void setProgress(int progress) {
        if (progress > 0 && progress < mMax) {
            moveThumb(progress);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLintPaint.setColor(mBackground);
        canvas.drawLine(mStartPoint.x,
                mStartPoint.y,
                mEndPoint.x,
                mEndPoint.y,
                mLintPaint);
        drawColorLint(canvas);
        drawThumb(canvas);
    }

    private synchronized void drawColorLint(Canvas canvas) {
        if (mColorList != null) {
            for (ColorScope scope : mColorList) {
                float x = mStartPoint.x + progressToPx(scope.mStartProgress);
                float x2 = mStartPoint.x + progressToPx(scope.mEndProgress);
                Log.e(TAG, "drawColorLint,color: " + scope.mColor + " ,start px:" + x + ",end px:" + x2);
                mLintPaint.setColor(scope.mColor);
                canvas.drawLine(x,
                        mStartPoint.y,
                        x2,
                        mEndPoint.y,
                        mLintPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int thumbHeight = mThumbDrawable == null ? 0 : mThumbDrawable.getIntrinsicHeight();
        if (mThumbDrawable != null) {
            mThumbWidth = Math.max(mMinWidth, Math.min(mMaxWidth, mThumbDrawable.getIntrinsicWidth()));
            mThumbHeight = Math.max(mMinHeight, Math.min(mMaxHeight, mThumbDrawable.getIntrinsicHeight()));
            mThumbHeight = Math.max(thumbHeight, mThumbHeight);
        }
        onInitDraw();

        setMeasuredDimension(resolveSizeAndState(mThumbWidth + getPaddingLeft() + getPaddingRight(),
                widthMeasureSpec, 0),
                resolveSizeAndState(mThumbHeight + getPaddingTop() + getPaddingBottom(),
                        heightMeasureSpec, 0));
    }

    /**
     * Draw the thumb.
     */
    void drawThumb(Canvas canvas) {
        if (mThumbDrawable != null) {
            final int saveCount = canvas.save();
            canvas.translate(getPaddingLeft(), getPaddingTop());
            mThumbDrawable.draw(canvas);
            canvas.restoreToCount(saveCount);
            mProgress = pxToProgress(mThumbDrawable.getBounds().left - getPaddingLeft());
            onProgressRefresh(mIsTouch, mProgress);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsUserSeekable || !isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断是否在区间范围内
                //记录当前X范围
                if (isScopeOf(event)) {
                    mIsTouch = true;
                    mTouchX = event.getX();
                    mInitTouchX = mThumbDrawable.getBounds().left;
                    onStartTrackingTouch();
                } else {
                    mIsTouch = false;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                //记录X改变
                if (mIsTouch) {
                    moveThumb(event);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mIsTouch) {
                    onStopTrackingTouch();
                }
                mIsTouch = false;
                //手指离开
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
                if (mIsTouch) {
                    onStopTrackingTouch();
                }
                mIsTouch = false;
                invalidate(); // see above explanation
                break;
        }
        return true;
    }

    private int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private boolean isScopeOf(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x > mStartPoint.x && x < mEndPoint.x && y > 0 &&
                y < mThumbHeight) {
            return true;
        }
        return false;
    }

    private void moveThumb(MotionEvent event) {
        int left = (int) (event.getX() - mTouchX + mInitTouchX);
        int right = left + mThumbWidth;
        if (right > (getWidth() - getPaddingRight()) || left < getPaddingLeft()) {
            return;
        } else {
            mThumbDrawable.setBounds(left, getPaddingTop(), right,
                    getPaddingTop() + mThumbHeight);
        }
    }

    private void moveThumb(int progress) {
        int left = (int) (progressToPx(progress) + getPaddingLeft());
        int right = left + mThumbWidth;
        if (right > (getWidth() - getPaddingRight()) || left < getPaddingLeft()) {
            return;
        } else {
            mThumbDrawable.setBounds(left, getPaddingTop(), right,
                    getPaddingTop() + mThumbHeight);
        }
    }

    void onStartTrackingTouch() {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    void onStopTrackingTouch() {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    void onProgressRefresh(boolean fromUser, int progress) {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onProgressChanged(this, progress, fromUser);
        }
    }

    public interface OnSeekBarChangeListener {
        void onProgressChanged(ColorFulSeekbar seekBar, int progress, boolean fromUser);

        void onStartTrackingTouch(ColorFulSeekbar seekBar);

        void onStopTrackingTouch(ColorFulSeekbar seekBar);
    }

    private int pxToProgress(float px) {
        int progress = (int) ((px - mThumbWidth / 2) / (mEndPoint.x - mStartPoint.x) * mMax + 0.5f);
        if (progress > mMax) {
            return mMax;
        } else if (progress < 0) {
            return 0;
        }
        return progress;
    }

    private float progressToPx(int progress) {
        float left = (float) progress / mMax * (mEndPoint.x - mStartPoint.x);
        left += mThumbWidth / 2;
        if (left < mThumbWidth / 2) {
            return mThumbWidth / 2;
        } else if (left > (mEndPoint.x - mStartPoint.x)) {
            return (mEndPoint.x - mStartPoint.x + mThumbWidth / 2);
        }
        return left;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        onInitDraw();
    }

    private void onInitDraw(){
        mStartPoint = new Point(getPaddingLeft() + mThumbWidth / 2, mThumbHeight / 2 + getPaddingTop());
        mEndPoint = new Point(getWidth() - getPaddingRight() - mThumbWidth / 2, mThumbHeight / 2 + getPaddingTop());

        Rect mThumbRect = new Rect(getPaddingLeft(),
                getPaddingTop(),
                mThumbWidth + getPaddingLeft(),
                getPaddingTop() + mThumbHeight);
        mThumbDrawable.setBounds(mThumbRect);

        moveThumb(mProgress);
    }
    public class ColorScope {
        public ColorScope(int color, int startProgress) {
            this.mColor = color;
            this.mStartProgress = startProgress;
        }

        public int mColor;
        public int mStartProgress;
        public int mEndProgress;
    }
}

