package com.example.xiaoqiang.myapplication.designMode.FactoryPattern;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Sansung;

/**
 * @Author: [xiaoqiang]
 * @Description: [SansungFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class SansungFactory extends MobileFactory {
    @Override
    public MobilePhone createMobilePhone() {
        return new Sansung();
    }
}
