package com.futurell.leetcode.A_01_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 1. 两数之和
 * @Author: lilei58
 * @Date: Created in 2021/2/23 3:01 下午
 *
 * 给定一个整数数组 nums 和一个整数目标值 target,请你在该数组中找出'和为目标值'的那'两个'整数,并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是,数组中同一个元素不能使用两遍。
 *
 * 示例 1：
 *  输入：nums = [2,7,11,15], target = 9
 *  输出：[0,1]
 *  解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 *  输入：nums = [3,2,4], target = 6
 *  输出：[1,2]
 *
 * 示例 3：
 *  输入：nums = [3,3], target = 6
 *  输出：[0,1]
 */
public class A_TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] indexes = twoSum(nums, target);
        int i = 0;
    }

    /**
     *  思路: 哈希映射
     * 1. 这道题本身如果通过暴力遍历的话也是很容易解决的,时间复杂度在 O(n^2).
     * 2. 由于哈希查找的时间复杂度为 O(1),所以可以利用哈希容器 Map 降低时间复杂度.
     * 3. 遍历数组 nums,i 为当前下标,每个值都判断 Map 中是否存在 target - nums[i] 的 key 值.
     * 4. 如果存在则找到了两个值,如果不存在则将当前的 (nums[i],i) 存入 map 中,继续遍历直到找到为止.
     * 5. 如果最终都没有结果则抛出异常.
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0 ; i < nums.length ; i++) {
            int first = target - nums[i];
            if (result.containsKey(first)) {
                return new int[] {result.get(first), i};
            } else {
                result.put(nums[i], i);
            }
        }
        return null;
    }
}