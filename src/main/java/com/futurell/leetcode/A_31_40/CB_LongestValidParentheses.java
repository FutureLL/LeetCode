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
        int length = longestValidParentheses("()(()())");
        int i = 0;
    }

    /**
     *  思路:
     * 1. 动态规划 --- longestValidParentheses()
     * 2. 栈 --- longestValidParentheses2()
     */
    public static int longestValidParentheses(String s) {

        if (s == null || s.length() < 2) {
            return 0;
        }

        // dp[i]：严格以i位置结尾，形成的有效括号子串最长长度是多少
        int[] dp = new int[s.length()];
        // 最终的答案
        int max = 0;

        // 以'('结尾,无效 直接结束此轮循环
        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == ')') {
                // 前面已经形成的有效括号长度
                int preLen = dp[i - 1];
                // 寻找与当前的右括号相匹配的左括号位置：前面有效括号长度再往前一个位置
                int pre = i - 1 - preLen;

                // 如果寻找到左括号：前面有效括号长度再往前一个位置是左括号
                if (pre >= 0 && s.charAt(pre) == '(') {
                    // 可以与当前的右括号闭合，有效长度增加2
                    dp[i] = dp[i - 1] + 2;

                    // 【注意】此时,需要再往前看下,是否还有有效长度,如果有,合并过来
                    // 例如: "()(()())" 当前在计算最后一个位置时,dp[7]已经等于 dp[6]+2 = 4+2
                    // 但需要再往前看一眼,dp[1]还有有效长度,合并过来 dp[7] = 4+2+2
                    // 那是否还需要再往前看？
                    // 不需要了,因为,如果前面还有有效长度,其长度肯定已经合并到dp[2]上了
                    // 因此,每次只需要再往前多看一眼就可以
                    if (pre - 1 >= 0) {
                        dp[i] += dp[pre - 1];
                    }
                }

                // 严格以每个结尾抓一个答案，最终答案必在其中
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }

    public static int longestValidParentheses2(String s) {

        int maxAns = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 插入
                stack.push(i);
            } else {
                // 删除
                stack.pop();
                // 判空防止首字符为')'
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 只要走else那么说明,此时有一组'()'括号成立
                    // peek(): 返回栈顶元素,但不在堆栈中删除它
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }
}
