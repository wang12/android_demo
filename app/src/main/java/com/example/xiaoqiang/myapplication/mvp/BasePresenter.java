package com.example.xiaoqiang.myapplication.mvp;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * @Author: [xiaoqiang]
 * @Description: [BasePresenter]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class BasePresenter<T extends BaseController> {
    protected WeakReference<BaseController> mReference;
    // MVP 模式下，Context 只能是ApplicationContexts
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context.getApplicationContext();
    }

    public void attachController(BaseController controller) {
        mReference = new WeakReference<BaseController>(controller);
    }

    public void detachController() {
        mReference.clear();
        mReference = null;
    }

    public boolean isControllerAttach() {
        return mReference != null && mReference.get() != null;
    }
}
