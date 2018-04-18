package com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern;

/**
 * @Author: [xiaoqiang]
 * @Description: [MobilePhone]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public abstract class MobilePhone {
    public String makeCall(){
        return "打电话";
    }

    public String surfInternet(){
        return "上网";
    }
}
