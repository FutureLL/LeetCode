package com.futurell.leetcode;

/**
 * @description: 11. 盛最多水的容器
 * @author: Mr.Li
 * @date: Created in 2021/3/30 7:45
 * @version: 1.0
 * @modified By:
 *
 * 给你 n 个非负整数 a1,a2,...,an,每个数代表坐标中的一个点 (i,ai) 。在坐标内画 n 条垂直线,垂直线 i 的两个端点分别为 (i,ai) 和 (i,0) 。
 * 找出其中的两条线,使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 示例 1：
 *  输入：[1,8,6,2,5,4,8,3,7]
 *  输出：49
 *  解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 *  输入：height = [1,1]
 *  输出：1
 *
 * 示例 3：
 *  输入：height = [4,3,2,1,4]
 *  输出：16
 *
 * 示例 4：
 *  输入：height = [1,2,1]
 *  输出：2
 */
public class AA_ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int index = maxArea2(height);
        int i = 0;
    }

    /**
     * 思路:
     *  1. 暴力: 两个for循环,但是超时了 maxArea
     *  2. 双针法 maxArea2
     */
    public static int maxArea(int[] height) {
        // 排除为空或数组长度为1
        if (null == height || height.length == 1) {
            return 0;
        }

        int max = -1;
        for (int i = 0;i < height.length - 1; i++) {
            for (int j = 1; j < height.length; j++) {
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }
        return max;
    }

    public static int maxArea2(int[] height) {

        int max = -1, i = 0, j = height.length - 1;
        while (i < j) {
            max = height[i] < height[j]
                    ? Math.max(max, (j - i) * height[i++])
                    : Math.max(max, (j - i) * height[j--]);
        }
        return max;
    }
}
