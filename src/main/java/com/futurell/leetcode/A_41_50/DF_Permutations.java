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

    /**
     *  这里使用 first 的原因:
     * 很容易想到的一个处理手段是我们定义一个标记数组 vis[] 来标记已经填过的数,那么在填第 first 个数的时候我们遍历题目给定的 n 个数,
     *     如果这个数没有被标记过,我们就尝试填入,并将其标记,继续尝试填下一个位置
     * 使用标记数组来处理填过的数是一个很直观的思路,但是可不可以去掉这个标记数组呢？毕竟标记数组也增加了我们算法的空间复杂度
     * 答案是可以的,我们可以将题目给定的 n 个数的数组 nums 划分成左右两个部分,左边的表示已经填过的数,右边表示待填的数,
     *     我们在回溯的时候只要动态维护这个数组即可
     * 具体来说,假设我们已经填到第 first 个位置,那么 nums 数组中 [0,first−1] 是已填过的数的集合,[first,n−1] 是待填的数的集合
     */
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
