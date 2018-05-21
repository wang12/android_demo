package com.example.xiaoqiang.myapplication.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: [xiaoqiang]
 * @Description: [PrintString]
 * @CreateDate: [2018/5/13]
 * @UpdateDate: [2018/5/13]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class PrintString {


    public static void main(String[] args) {

        String str = "abcabcbb";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring(str));

        str = "bbbbb";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring(str));

        str = "pwwkew";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring(str));

        str = "abcabcbb";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring2(str));

        str = "bbbbb";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring2(str));

        str = "pwwkew";
        System.out.println("不重复的字符长度:" + lengthOfLongestSubstring2(str));

    }


    private static String lengthOfLongestSubstring(String str) {
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<Character>();
        String maxString = null;
        int max = 0;

        while (start < str.length() && end < str.length()) {
            if (set.contains(str.charAt(end))) {
                set.remove(str.charAt(start));
                start++;
            } else {
                set.add(str.charAt(end++));
                if (end - start > max) {
                    max = end - start;
                    maxString = str.substring(start, end);
                }
            }
        }
        return maxString;
    }

    private static String lengthOfLongestSubstring2(String str) {
        int start = 0;
        int end = 0;
        Map<Character, Integer> map = new HashMap<>();
        String maxString = null;
        int max = 0;

        while (end < str.length()) {
            if (map.containsKey(str.charAt(end))) {
                start = Math.max(map.get(str.charAt(end)), start);
            }
            map.put(str.charAt(end), ++end);
            if (end - start  >= max) {
                max = end - start;
                maxString = str.substring(start, end);
            }
        }
        return maxString;
    }
}
