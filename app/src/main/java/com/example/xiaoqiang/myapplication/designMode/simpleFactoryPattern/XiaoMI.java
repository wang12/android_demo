package com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern;

/**
 * @Author: [xiaoqiang]
 * @Description: [XiaoMI]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class XiaoMI extends MobilePhone {
    @Override
    public String makeCall() {
        return "小米手机"+super.makeCall();
    }
}

