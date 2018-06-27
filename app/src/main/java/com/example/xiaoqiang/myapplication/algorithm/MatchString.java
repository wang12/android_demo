package com.example.xiaoqiang.myapplication.algorithm;

import android.text.TextUtils;

/**
 * @Author: [xiaoqiang]
 * @Description: [MatchString]
 * @CreateDate: [2018/5/21]
 * @UpdateDate: [2018/5/21]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class MatchString {


    public static void main(String[] args) {

    }

    public boolean isMatch(String s, String p) {
        if (TextUtils.isEmpty(s) && TextUtils.isEmpty(p)) return true;
        if (TextUtils.isEmpty(s)) return false;
        if (TextUtils.isEmpty(p)) return false;
        boolean[][] match = new boolean[s.length()][];
        for (int i = 0, j = 0; i < s.length(); i++) {
//            match[i][j] =
        }

        return false;
    }


}
