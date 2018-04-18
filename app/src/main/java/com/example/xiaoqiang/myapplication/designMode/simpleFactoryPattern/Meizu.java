package com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern;

/**
 * @Author: [xiaoqiang]
 * @Description: [Meizu]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class Meizu extends MobilePhone {
    @Override
    public String makeCall() {
        return "魅族手机打电话";
    }
}
