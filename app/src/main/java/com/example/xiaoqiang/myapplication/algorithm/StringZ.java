package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [StringZ]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class StringZ {

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        System.out.println(convert(str, 3));
        System.out.println(convert(str, 4));

    }

    public static String convert(String s, int numRows) {
        StringBuilder[] z = new StringBuilder[numRows];
        int line = 0;
        for (int i = 0; i < numRows; i ++) z[i] = new StringBuilder();

        boolean direction = false;
        for (int i = 0; i < s.length(); i++) {
            z[line].append(s.charAt(i));
            if(line == 0) direction = true;
            if(line == numRows-1 ) direction = false;
            line = direction?++line:--line;
        }
        StringBuilder builder = new StringBuilder();

        for (StringBuilder str : z) {
            builder.append(str);
        }
        return builder.toString();
    }
}
