package com.example.xiaoqiang.myapplication.mvp;

/**
 * @Author: [xiaoqiang]
 * @Description: [BaseModel]
 * @CreateDate: [2018/4/11]
 * @UpdateDate: [2018/4/11]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class BaseModel {



    public interface Callable<T> {
        void onSuccess(T t);

        void onFailure(int code, String msg);
    }
}
