package com.example.xiaoqiang.myapplication.mvp.login;

import android.content.Context;

import com.example.xiaoqiang.myapplication.R;
import com.example.xiaoqiang.myapplication.mvp.BaseModel;
import com.example.xiaoqiang.myapplication.mvp.BasePresenter;

/**
 * @Author: [xiaoqiang]
 * @Description: [LoginPresenter]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class LoginPresenter extends BasePresenter<LoginController> {

    private LoginModel mLogin;

    public LoginPresenter(Context context) {
        super(context);
        mLogin = new LoginModel();
    }

    public void login(String name, String psw) {
        if (isControllerAttach()) {
            mReference.get().showLoading();
            mReference.get().showToast(mContext.getResources().getString(R.string.login));
        }
        mLogin.login(name, psw, new BaseModel.Callable<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (isControllerAttach()) {
                    mReference.get().hindLoading();
                    mReference.get().showData(aBoolean);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (isControllerAttach()) {
                    mReference.get().hindLoading();
                    mReference.get().showError(msg);
                }
            }
        });

    }

    public void logout() {
        if (isControllerAttach()) {
            mReference.get().showLoading();
            mReference.get().showToast(mContext.getResources().getString(R.string.logout));
        }
        mLogin.logout(new BaseModel.Callable<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (isControllerAttach()) {
                    mReference.get().hindLoading();
                    mReference.get().showData(aBoolean);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (isControllerAttach()) {
                    mReference.get().hindLoading();
                    mReference.get().showError(msg);
                }
            }
        });
    }
}
