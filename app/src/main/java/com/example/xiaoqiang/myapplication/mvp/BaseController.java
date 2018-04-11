package com.example.xiaoqiang.myapplication.mvp;

/**
 * @Author: [xiaoqiang]
 * @Description: [BaseViewController]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public interface BaseController {
    void showLoading();

    void showError(String msg);

    void showToast(String msg);

    void hindLoading();

    void showData(Object t);
}
