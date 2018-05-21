package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [IntegerReverse]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class IntegerReverse {

    public static void main(String[] args){
        int x = 12345;
        System.out.println("反转后的数据是:"+reverse(x));
    }

    public static int reverse(int x){
        boolean isF = x <0;
        x = Math.abs(x);
        StringBuilder builder = new StringBuilder(String.valueOf(x));
        builder = builder.reverse();
        try {
            x = Integer.parseInt(builder.toString());
            x = isF?-x:x;
        }catch (Exception e){
            x = -1;
        }
        return x;
    }
}
