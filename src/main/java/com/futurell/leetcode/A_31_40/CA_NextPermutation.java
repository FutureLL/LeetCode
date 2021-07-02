package com.futurell.leetcode.A_31_40;

import java.util.Arrays;

/**
 * @Description: 31. 下一个排列
 * @Author: lilei58
 * @Date: Created in 2021/7/1 9:31 上午
 * 实现获取"下一个排列"的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 *  输入：nums = [1,2,3]
 *  输出：[1,3,2]
 *
 * 示例 2：
 *  输入：nums = [3,2,1]
 *  输出：[1,2,3]
 *
 * 示例 3：
 *  输入：nums = [1,1,5]
 *  输出：[1,5,1]
 *
 * 示例 4：
 *  输入：nums = [1]
 *  输出：[1]
 */
public class CA_NextPermutation {

    public static void main(String[] args) {
        nextPermutation(new int[] {4,5,2,6,3,1});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 两遍扫描
     *  这道题的难点式如何找到这两个数,例如: [4,5,2,6,3,1]
     *  可以找到左边较小的数为 2,右边较大的数为 3
     */
    public static void nextPermutation(int[] nums) {

        int i = nums.length - 2;
        // 找到左边较小的数
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            // 找到右边较大的数字
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 交换两个下标对应位置的值
            swap(nums, i, j);
        }
        // 可以交换则说明 [i+1, n] 区间为降序,直接反转该区间使其变为升序
        // 因为变换之后不能满足,下一个更大的排列,因此所以需要反转字符串
        // 使用该代码也可以: Arrays.sort(nums, i + 1, nums.length);
        reverse(nums, i + 1);
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
