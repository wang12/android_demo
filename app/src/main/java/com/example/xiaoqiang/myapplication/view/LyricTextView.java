package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.xiaoqiang.myapplication.R;
import com.example.xiaoqiang.myapplication.utils.DisplayUtil;

/**
 * Created by xiaoqiang on 2017/9/28.
 */

public class LyricTextView extends View {
    public final static int LEFT = 0;
    public final static int RIGHT = 1;
    private final static int DEFAULT_COLOR = 0xff000000;
    private final static int CHANGED_COLOR = 0xffff0000;
    private String mText = "";
    private float mTextSize = DisplayUtil.dip2px(getContext(), 16);
    private int mDefaultColor = DEFAULT_COLOR;
    private int mChangeColor = CHANGED_COLOR;
    private int mDirection = LEFT;
    private float mProgress = 0f;
    private Paint mPaint;
    private int mTextHeight;
    private int mTextWidth;

    public LyricTextView(Context context) {
        this(context, null);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.LyricTextView);
            mText = t.getString(R.styleable.LyricTextView_text);
            if (mText == null) {
                mText = "";
            }
            mTextSize = t.getDimension(R.styleable.LyricTextView_text_size, DisplayUtil.dip2px(getContext(), 16));
            mDefaultColor = t.getColor(R.styleable.LyricTextView_default_color, DEFAULT_COLOR);
            mChangeColor = t.getColor(R.styleable.LyricTextView_changed_color, CHANGED_COLOR);
            mDirection = t.getInt(R.styleable.LyricTextView_direction, LEFT);
            mProgress = t.getFloat(R.styleable.LyricTextView_progress, 0);
            t.recycle();
        }
        initPaint();
        measureText();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize); //必须在measureText前设置
    }

    private void measureText() {
        Rect r = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), r);//一个坑：rect.width（或者说rect.right-rect.left）得到的值会比实际字长度小一点点，因此这里使用paint.measureText方法获取宽度
        mTextHeight = r.bottom - r.top;
        mTextWidth = (int) mPaint.measureText(mText, 0, mText.length());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec, true);
        int height = measure(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    private int measure(int measureSpec, boolean isWidth) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                if (isWidth) {
                    size = mTextWidth;
                } else {
                    size = mTextHeight;
                }
                break;
        }
        return isWidth ?
                (size + getPaddingLeft() + getPaddingRight())
                : (size + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawText(canvas, mDirection, mProgress);
    }


    private void drawText(Canvas canvas, int direction, float progress) {

        int startX;
        int endX;
        int realWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        int realHeight = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        int textLeft = getPaddingLeft() + realWidth / 2 - mTextWidth / 2;   //文本在控件中的起始x位置
        int textRight = getPaddingLeft() + realWidth / 2 + mTextWidth / 2;   // 文本在控件中的结束x位置
        int textBottom = getPaddingTop() + realHeight / 2 + mTextHeight / 2;  //文本在控件中的结束y位置
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 1) {
            progress = 1;
        }
        Log.d("wqq","这里的值是："+progress);
        int changedWidth = (int) (mTextWidth * progress);
        if (direction == LEFT) {
            startX = textLeft;
            endX = textLeft + changedWidth;
        } else {
            startX = textRight - changedWidth;
            endX = textRight;
        }

        //画正常的文字内容
        mPaint.setTextSize(mTextSize);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        canvas.save();
        mPaint.setColor(mDefaultColor);
        canvas.drawText(mText, textLeft, textBottom, mPaint);
        canvas.restore();

        //画渐变部分的文字
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        mPaint.setColor(mChangeColor);
        canvas.clipRect(startX, 0, endX, getMeasuredHeight());
        canvas.drawText(mText, textLeft, textBottom, mPaint);
        canvas.restore();
    }

    public void setText(String mText) {
        this.mText = mText;
        invalidate();
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        invalidate();
    }

    public void setDefaultColor(int mDefaultColor) {
        this.mDefaultColor = mDefaultColor;
        invalidate();
    }

    public void setChangeColor(int mChangeColor) {
        this.mChangeColor = mChangeColor;
        invalidate();
    }

    public void setDirection(int mDirection) {
        this.mDirection = mDirection;
        invalidate();
    }

    public void setProgress(float mProgress) {
        if(mProgress <0 || mProgress >1) return;
        this.mProgress = mProgress;
        invalidate();
    }
}
