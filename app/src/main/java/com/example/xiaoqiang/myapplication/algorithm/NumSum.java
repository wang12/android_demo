package com.example.xiaoqiang.myapplication.algorithm;

/**
 * @Author: [xiaoqiang]
 * @Description: [给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。]
 * @CreateDate: [2018/5/12]
 * @UpdateDate: [2018/5/12]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class NumSum {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 考虑进位就行
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = new ListNode(0);
        int carry = 0;
        ListNode current = sumNode;
        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            carry = sum >= 10 ? 1 : 0;
            current.next = new ListNode(sum % 10);
            current = current.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return sumNode.next;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);
        l2.next.next.next = new ListNode(4);

        ListNode sum = addTwoNumbers(l1, l2);
        while (sum != null) {
            System.out.print(sum.val);
            sum = sum.next;
        }
    }
}
