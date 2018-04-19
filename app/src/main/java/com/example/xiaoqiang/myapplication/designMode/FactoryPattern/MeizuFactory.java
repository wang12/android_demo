package com.example.xiaoqiang.myapplication.designMode.FactoryPattern;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Meizu;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;

/**
 * @Author: [xiaoqiang]
 * @Description: [MeizuFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class MeizuFactory extends MobileFactory {
    @Override
    public MobilePhone createMobilePhone() {
        return new Meizu();
    }
}
