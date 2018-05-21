package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [TwoArrayCom]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class TwoArrayCom {

    public static void main(String[] args){
        int[] arr1 = new int[]{1,2,3};
        int[] arr2 = new int[]{2};
        System.out.println("中位数:"+ findMedianSortedArrays(arr1,arr2));
    }

    private static double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int size1 = arr1.length;
        int size2 = arr2.length;
        if((size1 + size2)%2 == 0){
            return (findMedianSortedArrays(arr1,arr2,(size1 + size2)/2) + findMedianSortedArrays(arr1,arr2,(size1 + size2)/2 +1))/2;
        }else {
           return findMedianSortedArrays(arr1,arr2,(size1 + size2)/2 + 1);
        }
    }
    private static double findMedianSortedArrays(int[] arr1, int[] arr2,int key) {
        int p =0,q=0;
        for (int i =0;i<key-1;i++){
            if(p >= arr1.length && q < arr2.length){
                q++;
            }else if(p< arr1.length && q>= arr2.length){
                p++;
            }else if(arr1[p] < arr2[q]){
                p++;
            }else {
                q++;
            }
        }
        if(p >= arr1.length){
            return arr2[q];
        }
        if(q >= arr2.length){
            return arr1[p];
        }
        return Math.min(arr1[p],arr2[q]);
    }

}
