package com.futurell.leetcode.A_31_40;

/**
 * @Description: 35. 搜索插入位置
 * @Author: lilei58
 * @Date: Created in 2021/7/12 8:29 下午
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *  输入: [1,3,5,6], 5
 *  输出: 2
 *
 * 示例 2:
 *  输入: [1,3,5,6], 2
 *  输出: 1
 *
 * 示例 3:
 *  输入: [1,3,5,6], 7
 *  输出: 4
 *
 * 示例 4:
 *  输入: [1,3,5,6], 0
 *  输出: 0
 */
public class CE_SearchInsertPosition {

    public static void main(String[] args) {
        int index = searchInsert(new int[]{1, 3}, 3);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 二分查找
     */
    public static int searchInsert(int[] nums, int target) {

        int length = nums.length;
        // 排除极端情况
        if (nums[0] >= target) {
            return 0;
        }
        if (nums[length - 1] < target) {
            return length;
        }
        if (nums[length - 1] == target) {
            return length - 1;
        }

        // 二分查找
        int index = binarySearch(nums, target);
        return index;
    }

    /** 第一个小于 target 的数的下标 */
    public static int binarySearch(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            // 这里要注意,如果是等于,那么右边界移动,始终要保持相等的话target在第一个位置
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
