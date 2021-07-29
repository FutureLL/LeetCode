package com.futurell.leetcode.A_41_50;

/**
 * @Description: 42. 接雨水
 * @Author: lilei58
 * @Date: Created in 2021/7/28 8:48 下午
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 *  输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *  输出：6
 *  解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 *  输入：height = [4,2,0,3,2,5]
 *  输出：9
 */
public class DB_TrappingRainWater {

    public static void main(String[] args) {

        int trap = trap3(new int[]{4, 2, 0, 3, 2, 5});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 暴力解法 --- trap
     * 2. 动态规划 --- trap2
     * 3. 双指针 --- trap3
     */
    public static int trap(int[] height) {

        // 记录最大值
        int ans = 0;

        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            // 向左遍历,找到最大值
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }

            // 向右遍历,找到最大值
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            // 当前列能放多少水,取决于左右两边的最大值中的小值,在减去该列本身占有的格子数量
            // 例如: 1,0,2 当i=1时,左侧最大值为1,右侧最大值为2,两个数的小值为1,减i=1本身的值,则该位置可放雨水为1
            ans += Math.min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }

    public static int trap2(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }
        // 记录最大值
        int ans = 0;
        int size = height.length;
        // 存储左右两侧的最大值
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];
        leftMax[0] = height[0];
        // 向左遍历,找到最大值
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[size - 1] = height[size - 1];
        // 向右遍历,找到最大值
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        // 当前列能放多少水,取决于左右两边的最大值中的小值,在减去该列本身占有的格子数量
        // 例如: 1,0,2 当i=1时,左侧最大值为1,右侧最大值为2,两个数的小值为1,减i=1本身的值,则该位置可放雨水为1
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public static int trap3(int[] height) {

        // 记录最大值
        int ans = 0;
        // 从左往右处理的当前下标
        int left = 0;
        // 从右往左处理的当前下标
        int right = height.length - 1;
        // 左边的最大值,它是从左往右遍历找到的
        int leftMax = 0;
        // 右边的最大值,它是从右往左遍历找到的
        int rightMax = 0;
        // 双指针一左一右
        while (left < right) {
            // 接水量取决于小短板
            if (height[left] < height[right]) {
                // 短板>=左边最大值无法接水
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                }
                // 可以接水
                else {
                    // 计算当前下标可接水量
                    ans += (leftMax - height[left]);
                }
                left ++;
            } else {
                // 短板>=左边最大值无法接水
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                }
                // 可以接水
                else {
                    // 计算当前下标可接水量
                    ans += (rightMax - height[right]);
                }
                right --;
            }
        }
        return ans;
    }

}
