package com.example.xiaoqiang.myapplication.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * @Author: [xiaoqiang]
 * @Description: [ThreeLayersButton]
 * @CreateDate: [2018/1/19]
 * @UpdateDate: [2018/1/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ThreeLayersButton extends RelativeLayout {
    private Context mContext;
    private View mBackground;
    private View mOvercover;
    private View mFinalize;
    private int mDefaultColor = 0xFF00F0F4;
    private int mDefaultCoverColor = 0xffffffff;
    private int mDefaultBackground = 0xffffeee0;
    private GradientDrawable mFinalizeDrawable;
    private GradientDrawable mBackgroundDrawable;
    private GradientDrawable mOvercoverDrawable;
    private int mRadius = 30;
    private int mProgress = 0;
    private int mMax = 100;
    private int mWidth;

    public ThreeLayersButton(@NonNull Context context) {
        super(context);
        initView();
    }

    public ThreeLayersButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ThreeLayersButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mContext = getContext();
        mBackground = new View(mContext);
        mOvercover = new View(mContext);
        mFinalize = new View(mContext);
        LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mBackground, mParams);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mOvercover, mParams);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mFinalize, mParams);
        loadView();
    }

    private void loadView() {
        mBackgroundDrawable = new GradientDrawable();
        mBackgroundDrawable.setCornerRadius(dip2px(mRadius));
        mBackgroundDrawable.setColor(mDefaultColor);
        mBackground.setBackground(mBackgroundDrawable);


        mOvercoverDrawable = new GradientDrawable();
        int radius = dip2px(mRadius);
        float[] radiis = new float[]{0,
                0, radius, radius,
                radius, radius, 0,
                0};
        mOvercoverDrawable.setCornerRadii(radiis);
        mOvercoverDrawable.setColor(mDefaultBackground);
        mOvercover.setBackground(mOvercoverDrawable);

        mFinalizeDrawable = new GradientDrawable();
        mFinalizeDrawable.setCornerRadius(dip2px(mRadius));
        mFinalizeDrawable.setStroke(dip2px(2), mDefaultCoverColor);
        mFinalize.setBackground(mFinalizeDrawable);
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        if (mWidth <= 0) {
            mWidth = getWidth();
        }
        post(mProgressChangeRunnable);
    }


    public void setDefaultColor(int mDefaultColor) {
        this.mDefaultColor = mDefaultColor;
    }


    public void setDefaultCoverColor(int mDefaultCoverColor) {
        this.mDefaultCoverColor = mDefaultCoverColor;
    }


    public void setDefaultBackground(int mDefaultBackground) {
        this.mDefaultBackground = mDefaultBackground;
    }


    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public void setMax(int mMax) {
        this.mMax = mMax;
    }

    private Runnable mProgressChangeRunnable = new Runnable() {
        @Override
        public void run() {
            if (mWidth <= 0) {
                getViewTreeObserver().addOnPreDrawListener(
                        new ViewTreeObserver.OnPreDrawListener() {
                            @Override
                            public boolean onPreDraw() {
                                mWidth = getMeasuredWidth();
                                LayoutParams params = (LayoutParams) mOvercover.getLayoutParams();
                                params.leftMargin = (int) (mWidth * (mProgress / (float) mMax));
                                return true;
                            }
                        });
            } else {
                LayoutParams params =
                        (LayoutParams) mOvercover.getLayoutParams();
                params.leftMargin = (int) (mWidth * (mProgress / (float) mMax));
                mOvercover.setLayoutParams(params);
            }
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        post(mProgressChangeRunnable);
    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    static class Builder {
        private ThreeLayersButton mLayerButton;

        public Builder(Context mContext) {
            mLayerButton = new ThreeLayersButton(mContext);
        }

        public Builder setColor(int color) {
            mLayerButton.setDefaultColor(color);
            return this;
        }

        public Builder setFrameColor(int color) {
            mLayerButton.setDefaultCoverColor(color);
            return this;
        }

        public Builder setAmbientColor(int color) {
            mLayerButton.setDefaultBackground(color);
            return this;
        }

        public ThreeLayersButton build() {
            mLayerButton.loadView();
            return mLayerButton;
        }

    }
}
