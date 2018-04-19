package com.example.xiaoqiang.myapplication.designMode.AbstractFactoryPattern;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Meizu;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Sansung;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.XiaoMI;

/**
 * @Author: [xiaoqiang]
 * @Description: [AbstractFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class AbstractFactory extends MobileFactory {
    @Override
    public MobilePhone createXiaoMi() {
        return new XiaoMI();
    }

    @Override
    public MobilePhone createMeizu() {
        return new Meizu();
    }

    @Override
    public MobilePhone createSansung() {
        return new Sansung();
    }
}
