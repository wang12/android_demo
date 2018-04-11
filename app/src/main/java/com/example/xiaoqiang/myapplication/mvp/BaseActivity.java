package com.example.xiaoqiang.myapplication.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @Author: [xiaoqiang]
 * @Description: [BaseActivity]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public abstract class BaseActivity<T extends BasePresenter> extends Activity implements BaseController {

    private ProgressDialog mProgressDialog;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mPresenter = getPresenter();
        mPresenter.attachController(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachController();
    }

    protected abstract T getPresenter();

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hindLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
