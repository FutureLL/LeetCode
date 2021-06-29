package com.futurell.leetcode.A_21_30;

/**
 * @Description: 29. 两数相除
 * @Author: lilei58
 * @Date: Created in 2021/6/28 3:00 下午
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * 示例 1:
 *  输入: dividend = 10, divisor = 3
 *  输出: 3
 *  解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 * 示例 2:
 *  输入: dividend = 7, divisor = -3
 *  输出: -2
 *  解释: 7/-3 = truncate(-2.33333..) = -2
 */
public class BI_DivideTwoIntegers {

    public static void main(String[] args) {
        int divide = divide(27, 3);
        int i = 0;
    }

    public static int divide(int dividend, int divisor) {

        // 特殊情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 判断是否需要正负号
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        // int -> long
        long dividendL = dividend;
        long divisorL = divisor;
        // 这里不能直接写成 long dividendL=Math.abs(dividend)
        // 因为 Integer.MIN_VALUE 直接取绝对值会越界,先转成 long 再取绝对值
        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);

        // 记录商
        int value = 0;
        while (divisorL <= dividendL) {
            long temp = divisorL;
            // 记录次数
            long times = 1;
            // tmp << 1 相当于 tmp*2,tmp*2 如果大于被除数了,就应该跳出去
            // 比如 6*2=12 已经大于 10 了,就应该跳出内层的 while 循环了
            // 使用内循环可以解决除数与被除数差距过大的情况
            // 比如: 27/3, 3*2=6 -> 6*2=12 -> 12*2=24 -> 24*2不满足条件不进入 while 循环
            // 很大程度上提升了效率
            while (temp << 1 <= dividendL) {
                temp = temp << 1;
                times = times << 1;
            }
            value += times;
            dividendL -= temp;
        }
        return isNegative ? -value : value;
    }
}
