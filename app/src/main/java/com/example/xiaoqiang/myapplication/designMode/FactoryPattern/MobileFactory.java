package com.example.xiaoqiang.myapplication.designMode.FactoryPattern;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;

/**
 * @Author: [xiaoqiang]
 * @Description: [MobileFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public abstract class MobileFactory {

    public abstract MobilePhone createMobilePhone();
}
