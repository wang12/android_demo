package com.example.xiaoqiang.myapplication.designMode.proxy;

/**
 * @Author: [xiaoqiang]
 * @Description: [GameProxy]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class GameProxy implements IGame {
    private IGame mGame;

    public GameProxy(IGame game) {
        this.mGame = game;
    }

    @Override
    public String play() {
        StringBuilder builder = new StringBuilder();
        builder.append(login());
        builder.append(mGame.play());
        builder.append(logout());
        return builder.toString();
    }

    public String login() {
        return "登录游戏\n";
    }

    public String logout() {
        return "登出游戏";
    }
}
