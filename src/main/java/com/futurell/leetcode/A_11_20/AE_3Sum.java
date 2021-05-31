package com.futurell.leetcode.A_11_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 15. 三数之和
 * @author: Mr.Li
 * @date: Created in 2021/4/13 7:37
 * @version: 1.0
 * @modified By:
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 示例 1：
 *  输入：nums = [-1,0,1,2,-1,-4]
 *  输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 *  输入：nums = []
 *  输出：[]
 *
 * 示例 3：
 *  输入：nums = [0]
 *  输出：[]
 */
public class AE_3Sum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        List<List<Integer>> lists = threeSum(nums);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 双指针法 + 循环
     *    这种方式就不会造成数据的遗漏
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 定义返回对象
        List<List<Integer>> list = new ArrayList<>();

        if(nums == null || nums.length < 3) {
            return list;
        }
        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字大于0,则三数之和一定大于0,所以结束循环
            if(nums[i] > 0) {
                break;
            }
            // 去重
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 去重
                    while (L < R && nums[L] == nums[L + 1]) {
                        L ++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R --;
                    }
                    L ++;
                    R --;
                }
                else if (sum < 0) {
                    L ++;
                }
                else if (sum > 0) {
                    R --;
                }
            }
        }
        return list;
    }

    /**
     *  问题:
     * 1. 当 i++ 或 j-- 的时候会漏掉一部分数据
     */
    public static List<List<Integer>> threeSumMy(int[] nums) {
        // 定义返回对象
        List<List<Integer>> list = new ArrayList<>();
        // 将入参变成List集合
        List<Integer> temp = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());

        for (int i = 0, j = temp.size() - 1; i < j;) {
            int z = i + 1;
            if (z == j) {
                break;
            }
            for (; z < j; z ++) {
                // 单个有效值集合
                List<Integer> tempList = new ArrayList<>();
                int sum = temp.get(i) + temp.get(j) + temp.get(z);
                if (sum == 0) {
                    tempList.add(temp.get(i));
                    tempList.add(temp.get(j));
                    tempList.add(temp.get(z));
                    list.add(tempList);
                    // 去重
                    while (i < j && temp.get(i).equals(temp.get(i + 1))) {
                        i ++;
                    }
                    while (i <  j && temp.get(j).equals(temp.get(j - 1))) {
                        j --;
                    }
                    i ++;
                    j --;
                } else if (sum > 0) {
                    j --;
                } else if (sum < 0) {
                    i ++;
                }
            }
        }
        return list;
    }
}
