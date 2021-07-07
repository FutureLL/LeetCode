package com.futurell.leetcode.A_31_40;

/**
 * @Description: 33. 搜索旋转排序数组
 * @Author: lilei58
 * @Date: Created in 2021/7/7 下午9:59
 *
 * 整数数组 nums 按升序排列，数组中的值互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了'旋转'，
 *   使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 *   例如，[0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2]。
 * 给你'旋转后'的数组 nums 和一个整数 target，如果 nums 中存在这个目标值 target，则返回它的下标，否则返回 -1。
 *
 * 示例 1：
 *  输入：nums = [4,5,6,7,0,1,2], target = 0
 *  输出：4
 *
 * 示例 2：
 *  输入：nums = [4,5,6,7,0,1,2], target = 3
 *  输出：-1
 *
 * 示例 3：
 *  输入：nums = [1], target = 0
 *  输出：-1
 */
public class CC_SearchInRotatedSortedArray {

    public static void main(String[] args) {

        int search = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 二分法
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 如果 nums[left] <= nums[mid] 成立,那么表示 [left, mid] 是有序的
            if (nums[left] <= nums[mid]) {
                /**
                 * nums[left] <= target 且 target < nums[mid],也就是 target 在 [left, mid] 范围内,则 right = mid - 1;
                 * 不满足则 left = mid + 1;
                 */
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // [mid - 1 , right] 范围是有序的
            else {
                /**
                 * 判断值是否在 [mid - 1 , right] 中
                 * 否则,在 [left, mid] 中
                 */
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
