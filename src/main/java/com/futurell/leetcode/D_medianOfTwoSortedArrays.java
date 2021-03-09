package com.futurell.leetcode;

/**
 * @Description: 4. 寻找两个正序数组的中位数
 * @Author: lilei58
 * @Date: Created in 2021/3/1 7:05 上午
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的'中位数'。
 *
 * 示例 1：
 *  输入：nums1 = [1,3], nums2 = [2]
 *  输出：2.00000
 *  解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 *  输入：nums1 = [1,2], nums2 = [3,4]
 *  输出：2.50000
 *  解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 *  输入：nums1 = [0,0], nums2 = [0,0]
 *  输出：0.00000
 *
 * 示例 4：
 *  输入：nums1 = [], nums2 = [1]
 *  输出：1.00000
 *
 * 示例 5：
 *  输入：nums1 = [2], nums2 = []
 *  输出：2.00000
 */
public class D_medianOfTwoSortedArrays {

    public static void main(String[] args) {
//        double medianSortedArrays = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        double medianSortedArraysMy = findMedianSortedArraysMy(new int[]{3}, new int[]{3});
        int i = 0;
    }

    /**
     *  思路: 遍历数组,找到中位数
     *  1. 定义 i, j 分别用来记录 nums1, nums2 的下标
     *  2. iValue, jValue 用来记录中位数, iValue 记录左边的值, jValue 记录右边的值
     */
    public static double findMedianSortedArraysMy(int[] nums1, int[] nums2) {

        if (nums1.length == 0 && nums2.length == 0) {
            return 0.00000D;
        }

        // 计算两个数组的长度,找到中位数位置
        int median = (nums1.length + nums2.length) / 2;

        // i 记录 nums1 下标,j 记录 nums2 下标
        int i = 0, j = 0;
        // iValue 记录左边的值,jValue 记录右边的值
        int iValue = -1, jValue;
        while (true) {
            jValue = iValue;
            // j >= nums2.length 防止 [nums1:{3,4},nums2:{}] 这种情况,也就是 nums2 数组为空
            if (i < nums1.length && (j >= nums2.length || nums1[i] <= nums2[j])) {
                iValue = nums1[i++];
            } else {
                iValue = nums2[j++];
            }

            if (i + j - 1 == median) {
                // 奇数
                if ((nums1.length + nums2.length) % 2 == 1) {
                    return iValue;
                }
                // 偶数
                else {
                    return (iValue + jValue) / 2.0;
                }
            }
        }
    }
}
