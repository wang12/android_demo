package com.example.xiaoqiang.myapplication.algorithm;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: [xiaoqiang]
 * @Description: [给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。]
 * @CreateDate: [2018/5/12]
 * @UpdateDate: [2018/5/12]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class TwoSum {

    /**
     * 通过对数组的排序，找到最小数和最大数，对两个数做遍历，如果大于target则向去掉最大数。如果小于target则去掉最小数
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        Arrays.sort(nodes);
        int left = 0;
        int rigth = nums.length - 1;

        while (left < rigth) {
            if (nodes[left].val + nodes[rigth].val == target) {
                result[0] = nodes[left].idx;
                result[1] = nodes[rigth].idx;
                break;
            } else if (nodes[left].val + nodes[rigth].val > target) {
                rigth--;
            } else {
                left++;
            }
        }
        return result;
    }

    /**
     * 最简单的遍历，两个for循环
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        return result;
    }

    /**
     * 把所有的数据放入Hash表中，然后查找 target - a 的值是否存在，存在就返回a 和对应的值
     * 不可以有重复数据
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int temp = target - entry.getKey();
            if (temp > 0 && map.containsKey(temp) && map.get(temp) != entry.getValue()) {
                result[0] = entry.getValue();
                result[1] = map.get(temp);
            }
        }
        return result;
    }

    public static void main(String argc[]) {
        int[] tags = new int[]{2, 7, 11, 15};
        int[] result = twoSum(tags, 9);
        System.out.println(Arrays.toString(result));
        result = twoSum2(tags, 9);
        System.out.println(Arrays.toString(result));
        result = twoSum3(tags, 9);
        System.out.println(Arrays.toString(result));
    }

    static class Node implements Comparable<Node> {

        int val;
        int idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(@NonNull Node o) {
            if(val < o.val) return -1;
            if(val > o.val) return 1;
            return 0;
        }
    }
}

