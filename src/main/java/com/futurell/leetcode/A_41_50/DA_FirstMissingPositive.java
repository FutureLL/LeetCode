package com.futurell.leetcode.A_41_50;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 41. 缺失的第一个正数
 * @Author: lilei58
 * @Date: Created in 2021/7/26 4:27 下午
 * 给你一个未排序的整数数组 nums，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 示例 1：
 *  输入：nums = [1,2,0]
 *  输出：3
 *
 * 示例 2：
 *  输入：nums = [3,4,-1,1]
 *  输出：2
 *
 * 示例 3：
 *  输入：nums = [7,8,9,11,12]
 *  输出：1
 */
public class DA_FirstMissingPositive {

    public static void main(String[] args) {
        int value = firstMissingPositive2(new int[]{7,8,9,11,12});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 取巧办法,效率低
     * 2. 哈希表 --- firstMissingPositive2
     * 3. 置换 --- firstMissingPositive3
     */
    public static int firstMissingPositive(int[] nums) {
        // 生序排列
        Arrays.sort(nums);

        // 记录最小正整数值
        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ans){
                ans++;
            }
        }
        return ans;
    }

    public static int firstMissingPositive2(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static int firstMissingPositive3(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
