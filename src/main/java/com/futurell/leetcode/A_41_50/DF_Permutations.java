package com.futurell.leetcode.A_41_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 46. 全排列
 * @Author: lilei58
 * @Date: Created in 2021/8/24 上午8:36
 * 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。你可以"按任意顺序"返回答案。
 *
 * 示例 1：
 *  输入：nums = [1,2,3]
 *  输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 *  输入：nums = [0,1]
 *  输出：[[0,1],[1,0]]
 *
 * 示例 3：
 *  输入：nums = [1]
 *  输出：[[1]]
 */
public class DF_Permutations {

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3, 4});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 回溯 --- permute
     * 举例:
     *    1, 2, 3, 4
     *    1, 2, 4, 3
     *    1, 3, 2, 4
     *    1, 3, 4, 2
     *    1, 4, 3, 2
     *    1, 4, 2, 3
     *    2, 1, 3, 4
     *    2, 1, 4, 3
     *    ......
     */
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        // 回溯方法
        backtrack(nums.length, Arrays.stream(nums).boxed().collect(Collectors.toList()), res, 0);

        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }

        for (int i = first; i < n; i++) {
            // 动态维护数组 -- 交换
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作 -- 交换
            Collections.swap(output, first, i);
        }
    }
}
