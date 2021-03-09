package com.futurell.leetcode;

/**
 * @description: 6. Z 字形变换
 * @author: Mr.Li
 * @date: Created in 2021/3/4 8:10
 * @version: 1.0
 * @modified By:
 *
 * 将一个给定字符串 s 根据给定的行数 numRows,以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *  P   A   H   N
 *  A P L S I I G
 *  Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"
 *
 * 示例 1：
 *  输入：s = "PAYPALISHIRING", numRows = 3
 *  输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 *  输入：s = "PAYPALISHIRING", numRows = 4
 *  输出："PINALSIGYAHRPI"
 *  解释：
 *   P     I    N
 *   A   L S  I G
 *   Y A   H R
 *   P     I
 *
 * 示例 3：
 *  输入：s = "A", numRows = 1
 *  输出："A"
 */
public class F_ZigzagConversion {

    public static void main(String[] args) {
        String paypalishiring = convert("PAYPALISHIRING", 3);
        int i = 0;
    }

    public static String convert(String s, int numRows) {
        if(numRows == 1 || s.length() <= numRows) {
            return s;
        }

        String[] rows = new String[numRows];
        for(int i = 0; i < numRows; i++) {
            rows[i] = "";
        }
        // 记录行数,也就是每次要插入在第几行
        int loc = 0;
        // 判断当前行是否可以插入
        boolean down = false;

        for(int i = 0; i < s.length(); i++) {
            rows[loc] += s.charAt(i);
            // 只有当上下两个位置才需要改变方向,也就是改变down的值
            if(loc == 0 || loc == numRows - 1) {
                down = !down;
            }
            loc += down ? 1 : -1;
        }

        String ans = "";
        for(String row : rows) {
            ans += row;
        }
        return ans;
    }
}
