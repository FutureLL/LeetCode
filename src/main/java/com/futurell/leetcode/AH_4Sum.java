package com.futurell.leetcode;

import com.sun.deploy.net.proxy.StaticProxyManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 18. 四数之和
 * @author: Mr.Li
 * @date: Created in 2021/4/28 8:23
 * @version: 1.0
 * @modified By:
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target,判断 nums 中是否存在四个元素 a,b,c和d,使得 a+b+c+d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 *
 * 示例 1：
 *  输入：nums = [1,0,-1,0,-2,2], target = 0
 *  输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 *  输入：nums = [], target = 0
 *  输出：[]
 *
 *
 */
public class AH_4Sum {

    public static void main(String[] args) {
        int i1 = ~4;
        System.out.println(i1);

        // List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        int i = 0;
    }

    /**
     *  思路: 双指针 + 两层for循环
     * 1. 在前面做过的三数之和中增加一层for循环即可
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> resultReturn = new ArrayList<>();

        // 增序排列
        Arrays.sort(nums);

        // 将第一个固定
        for (int i = 0; i < nums.length - 3; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 当前四位当前组合最大值
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 当前四位当前组合最小值
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 当前三位数组合最大值
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 当前三位数组合最小值
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }

                // 定义两个指针
                int L = j + 1, R = nums.length - 1;
                while (L < R) {
                    // 计算四数之和
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {

                        List<Integer> list = new ArrayList<>();
                        list.addAll(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        if (!resultReturn.contains(list)) {
                            resultReturn.add(list);
                        }
                        L ++;
                        R --;
                    } else if (sum < target) {
                        L ++;
                    } else {
                        R --;
                    }
                }
            }
        }

        return resultReturn;
    }
}
