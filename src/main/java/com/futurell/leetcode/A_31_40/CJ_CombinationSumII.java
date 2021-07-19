package com.futurell.leetcode.A_31_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 40. 组合总和 II
 * @Author: lilei58
 * @Date: Created in 2021/7/19 下午9:03
 * 给定一个数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 示例 1:
 *  输入: candidates = [10,1,2,7,6,1,5], target = 8,
 *  输出:
 *  [
 *   [1,1,6],
 *   [1,2,5],
 *   [1,7],
 *   [2,6]
 *  ]
 *
 * 示例 2:
 *  输入: candidates = [2,5,2,1,2], target = 5,
 *  输出:
 *  [
 *   [1,2,2],
 *   [5]
 *  ]
 */
public class CJ_CombinationSumII {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        int i = 0;
    }

    /**
     *  思路: 回溯 + 去重 + 剪枝
     * 1. 这个的重点是去重
     * 2. 增加了一个 boolean[] 判断某个位置是否被使用
     * 3. 当前位置与上一个位置的值相同,且上一个位置的值没有被使用,则直接跳过本次循环
     * 4. 每个元素不能再无限使用,而是只能用一次,因此每次递归的下标不能再从当前小标开始,而是 idx + 1 开始
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        // 存储所有结果
        List<List<Integer>> ans = new ArrayList<>();
        // 存储单次结果
        List<Integer> combine = new ArrayList<>();
        // 判断当前位置有无被使用
        boolean[] isUse = new boolean[candidates.length];
        // 回溯法
        dfs(candidates, target, ans, combine, 0, isUse);
        return ans;
    }

    private static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx, boolean[] isUse) {

        // 获取到一组结果
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        // 循环遍历
        for (int i = idx; i < candidates.length; i++) {
            // 当前位置与上一个位置的值相同,且上一个位置的值没有被使用,则直接跳过本次循环
            if (i > 0 && candidates[i] == candidates[i - 1] && !isUse[i - 1]) {
                continue;
            }
            // 超出 target 值,直接返回
            if (target - candidates[i] < 0) {
                break;
            }
            combine.add(candidates[i]);
            isUse[i] = true;
            dfs(candidates, target - candidates[i], ans, combine, i + 1, isUse);
            // 回溯,移除路径 combine 最后一个元素
            combine.remove(combine.size() - 1);
            isUse[i] = false;
        }
    }
}
