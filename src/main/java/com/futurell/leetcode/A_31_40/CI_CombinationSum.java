package com.futurell.leetcode.A_31_40;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 39. 组合总和
 * @Author: lilei58
 * @Date: Created in 2021/7/19 上午7:51
 * 给定一个无重复元素的数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 示例 1：
 *  输入：candidates = [2,3,6,7], target = 7,
 *  所求解集为：
 *  [
 *    [7],
 *    [2,2,3]
 *  ]
 *
 * 示例 2：
 *  输入：candidates = [2,3,5], target = 8,
 *  所求解集为：
 *  [
 *    [2,2,2,2],
 *    [2,3,3],
 *    [3,5]
 *  ]
 */
public class CI_CombinationSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{
                2, 3, 6, 7
        }, 7);
        int i = 0;
    }

    /**
     *  思路: 搜索回溯
     * 1.
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 存储所有结果
        List<List<Integer>> ans = new ArrayList<>();
        // 存储单次结果
        List<Integer> combine = new ArrayList<>();
        // 回溯法
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        // 下标越界,直接返回即可
        if (idx == candidates.length) {
            return;
        }
        // 获取到一组结果
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            // 回溯,移除路径 combine 最后一个元素
            combine.remove(combine.size() - 1);
        }
    }
}
