package com.example.xiaoqiang.myapplication.algorithm;

import java.util.Arrays;

/**
 * @Author: [xiaoqiang]
 * @Description: [Quick]
 * @CreateDate: [2018/5/22]
 * @UpdateDate: [2018/5/22]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class Quick {

    public static void sortQuick(int[] array,int start,int end){
        int i = start;
        int j = end;
        int target = array[start];
        while (i<j){
            while (i<j && array[j] >= target) j--;
            if(i<j){
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
            }
            while (i<j && array[i] <= target) i++;
            if(i<j){
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                j--;
            }
        }
        if(i-1 > start) sortQuick(array,start,i-1);
        if(j+1 < end) sortQuick(array,j+1,end);
    }

    public static void main(String[] args){
        int[] array = new int[]{6,6,2,2,7,3,8,8,9};
        sortQuick(array,0,array.length-1);
        System.out.print(Arrays.toString(array));
    }
}
