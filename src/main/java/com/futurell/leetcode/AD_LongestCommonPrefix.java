package com.futurell.leetcode;

/**
 * @description: 14. 最长公共前缀
 * @author: Mr.Li
 * @date: Created in 2021/4/7 7:55
 * @version: 1.0
 * @modified By:
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀,返回空字符串 ""。
 *
 * 示例 1：
 *  输入：strs = ["flower","flow","flight"]
 *  输出："fl"
 *
 * 示例 2：
 *  输入：strs = ["dog","racecar","car"]
 *  输出：""
 *  解释：输入不存在公共前缀。
 */
public class AD_LongestCommonPrefix {

    public static void main(String[] args) {

        String[] strs = {"flower","flow","flight"};

        String prefix = longestCommonPrefix(strs);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 纵向比较法 longestCommonPrefix()
     * 2. 二分查找 longestCommonPrefix2()
     */
    public static String longestCommonPrefix(String[] strs) {

        String returnResult = "";
        if (strs.length == 0) {
            return returnResult;
        }

        String result = strs[0];
        for (int i = result.length(); i > -1; i--) {

            for (int j = 0; j < strs.length; j ++) {
                // 不满足前缀匹配
                if (!strs[j].startsWith(result)) {
                    break;
                }
                if (j == strs.length - 1) {
                    return result;
                }
            }
            result = result.substring(0, i);
        }
        return returnResult;
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public static boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
