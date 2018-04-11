package com.example.xiaoqiang.myapplication.mvp.login;

import com.example.xiaoqiang.myapplication.mvp.BaseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @Author: [xiaoqiang]
 * @Description: [LoginModel]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class LoginModel extends BaseModel {

    public void login(String name, String psw, final Callable<Boolean> callable) {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callable.onSuccess(true);
                    }
                });

    }

    public void logout(final Callable<Boolean> callable) {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callable.onSuccess(false);
                    }
                });
    }
}
