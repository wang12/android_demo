package com.example.xiaoqiang.myapplication.designMode.simpleFactoryPattern;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: [xiaoqiang]
 * @Description: [MobileFactory]
 * @CreateDate: [2018/4/18]
 * @UpdateDate: [2018/4/18]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class MobileFactory {

    public static final String SANSUNG = "sansung";
    public static final String XIAOMI = "xiaomi";
    public static final String MEIZU = "meizu";


    public static MobilePhone createPhone(@MobileType String brand) {
        switch (brand) {
            case SANSUNG:
                return new Sansung();
            case XIAOMI:
                return new XiaoMI();
            case MEIZU:
                return new Meizu();
        }
        return null;
    }


    @StringDef({SANSUNG, XIAOMI, MEIZU})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MobileType {

    }
}
