package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [链表的反转]
 * @CreateDate: [2018/5/10]
 * @UpdateDate: [2018/5/10]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class LinkInvert {
    // 1. 定义链表
    static class Node {
        Node(String content) {
            mContent = content;
        }

        String mContent;
        Node mNext;
    }


    public static void main(String argc[]) {
        // 2. 链表存入需要两个对象，一个记录链表头，一个做临时变量，保存最后一个链表的值
        Node mFirst = new Node("1");
        Node mTemp = new Node("2");
        mFirst.mNext = mTemp;

        for (int i = 3; i < 10; i++) {
            mTemp.mNext = new Node(String.valueOf(i));
            mTemp = mTemp.mNext;
        }
        mTemp = mFirst;
        // 链表的输出需要用do while
        do {
            System.out.print(mTemp.mContent);
            mTemp = mTemp.mNext;
        } while (mTemp != null);

        if (mFirst == null || mFirst.mNext == null) return;

        mTemp = null;  // 记录新的链表
        while(mFirst != null){
            Node t = mFirst.mNext; //记录下一个元素
            mFirst.mNext = mTemp;  //倒转
            mTemp = mFirst; // 记录下新的链表
            mFirst = t; // 让旧链表的头移动一下
        }
        mFirst = mTemp;
        System.out.println("");

        do {
            System.out.print(mTemp.mContent);
            mTemp = mTemp.mNext;
        } while (mTemp != null);

        mFirst = invert(mFirst);
        System.out.println("");
        do {
            System.out.print(mFirst.mContent);
            mFirst = mFirst.mNext;
        } while (mFirst != null);
    }

    private static Node invert(Node node){
        if(node == null || node.mNext == null)
            return node;
        Node temp = invert(node.mNext);
        node.mNext.mNext = node;
        node.mNext = null;
        return temp;
    }

}
