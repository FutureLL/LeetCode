package com.futurell.leetcode.A_01_10;

/**
 * @description: 5. 最长回文子串
 * @author: Mr.Li
 * @date: Created in 2021/3/2 8:15
 * @version: 1.0
 * @modified By:
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 *  输入：s = "babad"
 *  输出："bab"
 *  解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 *  输入：s = "cbbd"
 *  输出："bb"
 *
 * 示例 3：
 *  输入：s = "a"
 *  输出："a"
 *
 * 示例 4：
 *  输入：s = "ac"
 *  输出："a"
 */
public class E_LongestPalindromicSubstring {

    public static void main(String[] args) {
        String str = longestPalindrome("babab");
        int i = 0;
    }

    /**
     *  思路: 中心扩散法
     * 1. 先从左边开始找 aab
     * 2. 再从右边开始找 abb
     * 3. 再从两边开始找 bab
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int left, right, len = 1, maxStart = 0, maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < s.length() && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < s.length() && s.charAt(right) == s.charAt(left)) {
                len += 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    /**
     *  思路: 动态规划
     */
    public static String longestPalindromeTwo(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1, begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 边界条件是: 表达式 [i + 1, j - 1] 不构成区间, 即长度严格小于 2, 即 j - 1 - (i + 1) + 1 < 2, 整理得 j - i < 3。
                    // 自己的理解: 单独判断一个字符串是否是回文字符串的最小单元是,起始到结束也就是 j - i = 0 (eg: a), j - i = 1 (eg: aa), j - i = 2 (eg: aba)
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 判断 i 和 j 位置的元素和他们各自元素之前的两个元素是否构成回文
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
