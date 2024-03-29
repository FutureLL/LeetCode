package com.futurell.leetcode.A_41_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 47. 全排列 II
 * @Author: lilei58
 * @Date: Created in 2021/9/2 上午8:28
 * 给定一个可包含重复数字的序列 nums，"按任意顺序"返回所有不重复的全排列。
 *
 * 示例 1：
 *  输入：nums = [1,1,2]
 *  输出：
 *   [[1,1,2],
 *   [1,2,1],
 *   [2,1,1]]
 *
 * 示例 2：
 *  输入：nums = [1,2,3]
 *  输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class DG_PermutationsII {

    public static void main(String[] args) {
        List<List<Integer>> permute = permuteUnique(new int[]{1, 1, 2});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 搜索回溯
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        // 存储返回结果
        List<List<Integer>> res = new ArrayList<>();
        // 存储单个结果
        List<Integer> output = new ArrayList<>();
        // 判断重复
        boolean[] vis = new boolean[nums.length];
        // 排序
        Arrays.sort(nums);

        // 回溯方法
        backtrack(output, res, 0, nums, vis);

        return res;
    }

    public static void backtrack(List<Integer> output, List<List<Integer>> res, int first, int[] nums, boolean[] vis) {
        // 满足条件
        if (first == nums.length) {
            res.add(new ArrayList<>(output));
            return;
        }

        // 这种方式就不能使用 first 来代替 vis[],因为涉及到数组多个位置的判断,因此不能用一个 first 表示
        for (int i = 0; i < nums.length; i++) {
            /**
             * vis[i]:
             *   判断对应位置是否被使用,如果被使用,则为true,||之后的判断不执行,如果未被使用,则为false,执行||之后的判断
             * i > 0:
             *   防止 i - 1 为空指针异常
             * nums[i] == nums[i - 1]:
             *   判断相邻两个值是否相等
             * vis[i - 1]:
             *   如果相邻两个值相等,那么前一个值需要没有被占用过
             */
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            output.add(nums[i]);
            vis[i] = true;
            backtrack(output, res, first + 1, nums, vis);
            vis[i] = false;
            output.remove(first);
        }
    }
}
