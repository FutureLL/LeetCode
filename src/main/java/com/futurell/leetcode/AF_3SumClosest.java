package com.futurell.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

/**
 * @description: 16. 最接近的三数之和
 * @author: Mr.Li
 * @date: Created in 2021/4/14 7:58
 * @version: 1.0
 * @modified By:
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数,使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 *  输入：nums = [-1,2,1,-4], target = 1
 *  输出：2
 *  解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
 */
public class AF_3SumClosest {

    public static void main(String[] args) {
        int[] nums = {0,2,1,-3};

        int result = threeSumClosest(nums, 1);
        int i = 0;
    }

    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int result = nums[0] + nums[1] + nums[2];

        for(int z = 0;z < nums.length; z++) {
            for (int i = z + 1, j = nums.length - 1; i < j;) {
                int sum = nums[i] + nums[j] + nums[z];
                if(Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }

                if (sum > target) {
                    j --;
                } else if (sum < target){
                    i ++;
                } else {
                    return result;
                }
            }
        }
        return result;
    }
}