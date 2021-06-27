package com.futurell.leetcode.A_21_30;


/**
 * @description: 26. 删除有序数组中的重复项
 * @author: Mr.Li
 * @date: Created in 2021/6/21 7:43
 * @version: 1.0
 * @modified By:
 *
 * 给你一个有序数组 nums，请你"原地"删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在"原地"修改输入数组，并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *  输入：nums = [1,1,2]
 *  输出：2, nums = [1,2]
 *  解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 *  输入：nums = [0,0,1,1,1,2,2,3,3,4]
 *  输出：5, nums = [0,1,2,3,4]
 *  解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 */
public class BF_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        int length1 = removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        int length2 = removeDuplicates(new int[]{1,1});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 删除元素并移动其余元素位置 removeDuplicates
     * 2. 获取元素直接覆盖该数组 removeDuplicates2
     */
    public static int removeDuplicates(int[] nums) {

        // 记录数组长度
        int length = nums.length;

        // 从 i = 1 开始循环
        for (int i = 1; i <= length - 1; ) {
            if (nums[i] == nums[i - 1]) {
                // 循环之后的元素依次向前
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // 数组长度 -1
                length--;
            } else {
                i++;
            }
        }

        return length;
    }

    public static int removeDuplicates2(int[] nums) {
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            // 首位元素直接复制 或 遇到不相等的元素(第一个不相等的元素,如: 0,1,1 取第一个1)
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[length++] = nums[i];
            }
        }
        return length;
    }
}
