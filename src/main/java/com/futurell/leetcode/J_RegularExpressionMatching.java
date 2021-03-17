package com.futurell.leetcode;

/**
 * @Description: 10. 正则表达式匹配
 * @Author: lilei58
 * @Date: Created in 2021/3/12 5:45 下午
 *
 * 给你一个字符串 s 和一个字符规律 p, 请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *  '.' 匹配任意单个字符
 *  '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配, 是要涵盖 整个 字符串 s的, 而不是部分字符串。
 *
 * 示例 1：
 *  输入：s = "aa" p = "a"
 *  输出：false
 *  解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 *  输入：s = "aa" p = "a*"
 *  输出：true
 *  解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 *  输入：s = "ab" p = ".*"
 *  输出：true
 *  解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4：
 *  输入：s = "aab" p = "c*a*b"
 *  输出：true
 *  解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5：
 *  输入：s = "mississippi" p = "mis*is*p*."
 *  输出：false
 */
public class J_RegularExpressionMatching {

    public static void main(String[] args) {
        boolean match = isMatch("ab", ".*");
        int i = 0;
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    private static boolean isMatch1(char[] s, char[] p){
        int m = s.length, n = p.length;
        // 这里dp矩阵是要大一圈的，用来处理s或者p为空串的情况，矩阵的行标i对应s的i-1，矩阵的列标j对应p的j-1
        boolean[][] dp = new boolean[m + 1][n + 1];
        // s和p都是空串的时候自然true
        dp[0][0] = true;
        // 初始化首列
        for(int i = 1; i <= m; i++){
            dp[i][0] = false;
        }
        // 初始化首行
        for(int j = 1; j <= n; j++){
            if(j == 1 || p[j - 1] != '*') dp[0][j] = false;
            else dp[0][j] = dp[0][j - 2];

        }
        // 通过递推公式开始填充矩阵
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p[j - 1] != '*'){
                    dp[i][j] = (s[i - 1] == p[j - 1] || p[j - 1] == '.') && dp[i - 1][j - 1];
                }
                else{
                    if(s[i - 1] == p[j - 2] || p[j - 2] == '.'){
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                        if(j > 1) dp[i][j] = dp[i][j] || dp[i][j - 2];
                    }
                    else{
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen+1][pLen+1];
        memory[0][0] = true;
        for(int i = 0; i <= sLen; i++) {
            for(int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1) == '*') {
                    memory[i][j] = memory[i][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && memory[i-1][j]);
                }else {
                    memory[i][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && memory[i-1][j-1];
                }
            }
        }
        return memory[sLen][pLen];
    }
}
