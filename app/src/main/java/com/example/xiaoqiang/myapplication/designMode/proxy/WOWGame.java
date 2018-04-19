package com.example.xiaoqiang.myapplication.designMode.proxy;

/**
 * @Author: [xiaoqiang]
 * @Description: [WOWGame]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class WOWGame implements IGame {
    @Override
    public String play() {
        return "我要玩WOW游戏\n";
    }
}
