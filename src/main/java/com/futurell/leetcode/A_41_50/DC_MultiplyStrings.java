package com.futurell.leetcode.A_41_50;

/**
 * @Description: 43. 字符串相乘
 * @Author: lilei58
 * @Date: Created in 2021/8/2 上午8:33
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *  输入: num1 = "2", num2 = "3"
 *  输出: "6"
 *
 * 示例 2:
 *  输入: num1 = "123", num2 = "456"
 *  输出: "56088"
 */
public class DC_MultiplyStrings {

    public static void main(String[] args) {
        String multiply = multiply("123", "456");
        int i = 0;
    }

    public static String multiply(String num1, String num2) {
        // 特殊情况判断,0乘以任何数都为0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 存储结果
        int[] ansArr = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                ansArr[i + j + 1] += num1.charAt(i) - '0' * num2.charAt(j) - '0';
            }
        }
        for (int i = num1.length() + num2.length() - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }

        // 判断是否需要进位
        int index = ansArr[0] == 0 ? 1 : 0;

        // 组装返回对象
        StringBuffer ans = new StringBuffer();
        while (index < num1.length() + num2.length()) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
}
