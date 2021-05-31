package com.futurell.leetcode.A_01_10;

/**
 * @description: 7. 整数反转
 * @author: Mr.Li
 * @date: Created in 2021/3/5 8:19
 * @version: 1.0
 * @modified By:
 *
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 *
 * 示例 1：
 *  输入：x = 123
 *  输出：321
 *
 * 示例 2：
 *  输入：x = -123
 *  输出：-321
 *
 * 示例 3：
 *  输入：x = 120
 *  输出：21
 *
 * 示例 4：
 *  输入：x = 0
 *  输出：0
 */
public class G_ReverseInteger {

    public static void main(String[] args) {
        int reverse = reverse(-123);
        int i = 0;
    }

    /**
     * 思路: 数学的思路,并且注意反转后是否溢出
     */
    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            // 获取最后一位
            int temp = x % 10;
            // 排除过大溢出 2147483647
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            // 排除过小溢出 -2147483648
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            ans = ans * 10 + temp;
            x /= 10;
        }
        return ans;
    }

    public static int reverseTwo(int x) {
        int ans = 0;
        while (x != 0) {
            // 判断是否溢出,经过 * / 操作后的数据和原来的值是否相等如果不相等,那么溢出,不需要管是过大溢出还是过小溢出
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}