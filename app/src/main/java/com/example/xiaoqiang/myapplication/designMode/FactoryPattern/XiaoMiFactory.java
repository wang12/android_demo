package com.example.xiaoqiang.myapplication.designMode.FactoryPattern;


import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.XiaoMI;

/**
 * @Author: [xiaoqiang]
 * @Description: [XiaoMiFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class XiaoMiFactory extends MobileFactory {

    @Override
    public MobilePhone createMobilePhone() {
        return new XiaoMI();
    }
}
