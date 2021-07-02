package com.futurell.leetcode.A_31_40;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description: 32. 最长有效括号
 * @Author: lilei58
 * @Date: Created in 2021/7/2 10:41 上午
 * 给你一个只包含'('和')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 *  输入：s = "(()"
 *  输出：2
 *  解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 *  输入：s = ")()())"
 *  输出：4
 *  解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 *  输入：s = ""
 *  输出：0
 */
public class CB_LongestValidParentheses {

    public static void main(String[] args) {
        int length = longestValidParentheses("()(()");
        int i = 0;
    }

    /**
     *  思路:
     * 1. 动态规划 --- longestValidParentheses()
     * 2. 栈 --- longestValidParentheses2()
     * 3. 不需要额外的空间 --- longestValidParentheses3()
     */
    public static int longestValidParentheses(String s) {

        int maxAns = 0;

        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
        }
        return maxAns;
    }

    public static int longestValidParentheses2(String s) {

        int maxAns = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }

    public static int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
