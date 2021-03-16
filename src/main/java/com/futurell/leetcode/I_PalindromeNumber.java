package com.futurell.leetcode;

/**
 * @Description: 9. 回文数
 * @Author: lilei58
 * @Date: Created in 2021/3/12 5:32 下午
 *
 * 给你一个整数 x, 如果 x 是一个回文整数, 返回 true; 否则,返回 false。
 *
 * 示例 1：
 *  输入：x = 121
 *  输出：true
 *
 * 示例 2：
 *  输入：x = -121
 *  输出：false
 *  解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 *  输入：x = 10
 *  输出：false
 *  解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 示例 4：
 *  输入：x = -101
 *  输出：false
 */
public class I_PalindromeNumber {

    public static void main(String[] args) {
        boolean palindrome = isPalindrome(123);
        int i = 0;
    }

    /**
     * 思路: 两端对比法
     * 其他方法
     *  1. 字符串反转法
     *  2. 后半段反转法
     *  3. 数学公式计算两端对比法 isPalindrome3(int x)
     *  4. 倒序计算法 isPalindrome2(int x)
     */
    public static boolean isPalindrome(int x) {
        // 去掉负数
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        // 去掉负数
        if (x < 0) {
            return false;
        }
        int cur = 0, num = x;
        while(num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    public boolean isPalindrome3(int x) {
        // 边界判断
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}
