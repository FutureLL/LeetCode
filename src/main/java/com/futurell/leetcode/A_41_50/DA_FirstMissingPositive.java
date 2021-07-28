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
        int value = firstMissingPositive3(new int[]{5, 3, 2, 1, 2, 3});
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

        // 我们将数组中所有小于等于 0 的数修改为 N+1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        // 我们遍历数组中的每一个数 x,它可能已经被打了标记,因此原本对应的数为 ∣x∣,其中 || 为绝对值符号。
        // 如果 |x| ∈ [1,N],那么我们给数组中的第 |x| - 1 个位置的数添加一个负号。
        // 注意如果它已经有负号,不需要重复添加
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 在遍历完成之后,如果数组中的每一个数都是负数,那么答案是 N+1,否则答案是第一个正数的位置加 1。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static int firstMissingPositive3(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // 将 nums[i] 获取到的值存放到下标为 nums[i] - 1 所对应的位置
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                // 交换数据
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // 循环判断对应位置的数据是否为 i + 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
