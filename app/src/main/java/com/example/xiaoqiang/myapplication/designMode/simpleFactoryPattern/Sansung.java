package com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern;

/**
 * @Author: [xiaoqiang]
 * @Description: [Sansung]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class Sansung extends MobilePhone {
    @Override
    public String makeCall() {
        return "三星手机打电话";
    }
}
