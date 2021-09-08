package com.futurell.leetcode.A_41_50;

import java.math.BigDecimal;

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
        double v = myPow(2.10000, 3);
        int i = 0;
    }

    public static double myPow(double x, int n) {

        boolean tag = true;
        if (n < 0) {
            tag = false;
        }

        double d = 1.00000D;

        for (int i = 1; i <= Math.abs(n); i++) {
            d *= x;
        }

        BigDecimal b = new BigDecimal(d);
        d = b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();

        return tag ? d : 1 / d;

    }
}
