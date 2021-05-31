package com.futurell.leetcode.A_01_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 8. 字符串转换整数 (atoi)
 * @Author: lilei58
 * @Date: Created in 2021/3/8 3:57 下午
 *
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *  函数 myAtoi(string s) 的算法如下：
 *   1. 读入字符串并丢弃无用的前导空格
 *   2. 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 *   3. 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 *   4. 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 *   5. 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 *   6. 返回整数作为最终结果。
 *
 * 示例 1：
 *  输入：s = "42"
 *  输出：42
 *  解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 *   第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *   第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *   第 3 步："42"（读入 "42"）
 *     解析得到整数 42 。
 *     由于 "42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 42 。
 *
 * 示例 2：
 *  输入：s = "   -42"
 *  输出：-42
 *  解释：
 *   第 1 步："   -42"（读入前导空格，但忽视掉）
 *   第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 *   第 3 步："   -42"（读入 "42"）
 *     解析得到整数 -42 。
 *     由于 "-42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 -42 。
 *
 * 示例 3：
 *  输入：s = "4193 with words"
 *  输出：4193
 *  解释：
 *   第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 *   第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *   第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 *     解析得到整数 4193 。
 *     由于 "4193" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 4193 。
 *
 * 示例 4：
 *  输入：s = "words and 987"
 *  输出：0
 *  解释：
 *   第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
 *   第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *   第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
 *     解析得到整数 0 ，因为没有读入任何数字。
 *     由于 0 在范围 [-2^31, 2^31 - 1] 内，最终结果为 0 。
 *
 * 示例 5：
 *  输入：s = "-91283472332"
 *  输出：-2147483648
 *  解释：
 *   第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
 *   第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
 *   第 3 步："-91283472332"（读入 "91283472332"）
 *     解析得到整数 -91283472332 。
 *     由于 -91283472332 小于范围 [-2^31, 2^31 - 1] 的下界，最终结果被截断为 -231 = -2147483648 。
 *
 * 00000-42a1234 ===> 0
 */
public class H_StringToIntegerAtoi {

    public static void main(String[] args) {
        int index = myAtoi2("2147483647");
        int i = 0;
    }

    public static int myAtoi2(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        int sign = 1, base = 0, i = 0, n = str.length();
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = (str.charAt(i++) == '+') ? 1 : -1;
        }
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }

    /**
     *  思路: 状态机
     * 1. 循环字符串,根据循环得到的字符类型,判断其处于什么状态,只有当前字符为数字时才计算
     */
    public static int myAtoi(String str) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < str.length(); i++) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        //                          ' '  |  '+'/'-'  |  number  |  order
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}