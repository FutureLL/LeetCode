package com.futurell.leetcode.A_41_50;

/**
 * @Description: 50. Pow(x, n)
 * @Author: lilei58
 * @Date: Created in 2021/9/8 上午8:46
 * 实现 pow(x, n)，即计算 x 的 n 次幂函数。
 *
 * 示例 1：
 *  输入：x = 2.00000, n = 10
 *  输出：1024.00000
 *
 * 示例 2：
 *  输入：x = 2.10000, n = 3
 *  输出：9.26100
 *
 * 示例 3：
 *  输入：x = 2.00000, n = -2
 *  输出：0.25000
 *  解释：2^-2 = 1/2^2 = 1/4 = 0.25
 */
public class DJ_PowxN {

    public static void main(String[] args) {
        double v = myPow2(2.10000, 10);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 依次乘(超时) --- myPow
     * 2. 快速幂+递归 --- myPow2
     */
    public static double myPow(double x, int n) {

        double d = 1.0;

        for (int i = 1; i <= Math.abs(n); i++) {
            d *= x;
        }

        return n > 0 ? d : 1 / d;
    }

    public static double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
