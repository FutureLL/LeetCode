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
        nextPermutation(new int[] {1,3,2});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 倒序查找
     */
    public static void nextPermutation(int[] nums) {

        // 记录
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j >= i; j++) {
                // 找到下一个序列
                if (nums[i] < nums[j + 1]) {
                    nums[i] = nums[i] + nums[j + 1];
                    nums[j + 1] = nums[i] - nums[j + 1];
                    nums[i] = nums[i] - nums[j + 1];
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
        // 不满足条件正序排列
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
