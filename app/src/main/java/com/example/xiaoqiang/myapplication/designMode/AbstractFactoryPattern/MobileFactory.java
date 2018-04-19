package com.example.xiaoqiang.myapplication.designMode.AbstractFactoryPattern;

import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Meizu;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.MobilePhone;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.Sansung;
import com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern.XiaoMI;

/**
 * @Author: [xiaoqiang]
 * @Description: [MobileFactory]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public abstract class MobileFactory {
    public abstract MobilePhone createXiaoMi();

    public abstract MobilePhone createMeizu();

    public abstract MobilePhone createSansung();
}
