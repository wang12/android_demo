package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [StringToInteger]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class StringToInteger {

    public static void main(String[] args){

    }

    public static int myAtoi(String str){
        if (str.charAt(0) == '-') {
            return -myAtoi2(str.substring(1,str.length()));
        }else if(str.charAt(0) < '0' || str.charAt(0)>'9'){
            return 0;
        }else{
            return myAtoi2(str);
        }
    }

    public static int myAtoi2(String str){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) >= '0' || str.charAt(i) <= '9'){
                builder.append(str.charAt(i));
            }else{
                break;
            }
        }
        int i = 0;
        try {
            i = Integer.parseInt(builder.toString());
        }catch (Exception e){
            i = Integer.MIN_VALUE;
        }
        return i;
    }
}
