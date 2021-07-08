package com.futurell.leetcode.A_31_40;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 * @Author: lilei58
 * @Date: Created in 2021/7/8 上午7:44
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 示例 1：
 *  输入：nums = [5,7,7,8,8,10], target = 8
 *  输出：[3,4]
 *
 * 示例 2：
 *  输入：nums = [5,7,7,8,8,10], target = 6
 *  输出：[-1,-1]
 *
 * 示例 3：
 *  输入：nums = [], target = 0
 *  输出：[-1,-1]
 */
public class CD_FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] result = searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 6);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 循环法 --- searchRange
     * 2. 二分法[一次性求出 left right] --- searchRange2
     * 3. 二分法[分别求出 left right] --- searchRange3
     */
    public static int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        // 记录target下标
        List<Integer> targetList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetList.add(i);
            }
        }

        return targetList.size() == 0 ? new int[]{-1, -1} : new int[]{targetList.get(0), targetList.get(targetList.size() - 1)};
    }

    private static int left = Integer.MAX_VALUE;
    private static int right = Integer.MIN_VALUE;
    public static int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        searchIndex(nums, target, 0, nums.length - 1);

        if (left == Integer.MAX_VALUE) {
            left = -1;
        }
        if (right == Integer.MIN_VALUE) {
            right = -1;
        }

        return new int[]{left, right};

    }

    public static void searchIndex(int[] nums, int target, int start, int end) {

        int mid = (start + end) / 2;
        // 返回条件
        if (start > end) {
            return;
        }

        if (nums[mid] == target) {
            // 若比left小，更新left
            if (mid < left) {
                left = mid;
            }
            // 若比right大,更新right
            if (mid > right) {
                right = mid;
            }
        }

        // 在右子数组
        if (nums[mid] <= target) {
            searchIndex(nums, target, mid + 1, end);
        }
        // 在左子数组
        if (nums[mid] >= target) {
            searchIndex(nums, target, start, mid - 1);
        }
        return;
    }

    public static int[] searchRange3(int[] nums, int target) {
        // 左值
        int leftIdx = binarySearch(nums, target - 1);
        // 右值
        int rightIdx = binarySearch(nums, target) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /** 第一个大于 target 的数的下标 */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
