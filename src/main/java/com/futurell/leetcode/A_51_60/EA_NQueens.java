package com.futurell.leetcode.A_51_60;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 51. N 皇后
 * @Author: lilei58
 * @Date: Created in 2021/12/24 上午7:34
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 *  输入：n = 4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 *  输入：n = 1
 *  输出：[["Q"]]
 */
public class EA_NQueens {

    /** 存放数据 */
    private static List<List<String>> queenList = new ArrayList<>();

    public static void main(String[] args) {

        List<List<String>> lists = solveNQueens(4);
        int i = 0;
    }

    public static List<List<String>> solveNQueens(int n) {

        // 表示列,该列有元素则赋值为true
        int[] col = new int[n];
        // 表示偏左的斜线,斜线上有元素赋值为true
        int[] left = new int[2 * n - 1];
        // 表示偏右的斜线,斜线上有元素赋值为true
        int[] right = new int [2 * n - 1];
        // 存储每行皇后的位置,下标表示行坐标,所存的元素表示列坐标
        int[] Q = new int [n];
        // 存放数据
        List<List<String>> queenList = new ArrayList<>();

        // 处理
        nextQueen(0, n, col, left, right, Q);

        return queenList;
    }

    public static void nextQueen(int i, int n, int[] col, int[] left, int[] right, int[] Q) {
        // 列的坐标
        for (int j = 0; j < n; j++) {
            // 判断是否可以放置皇后
            if (col[j] == 0 && left[i + j] == 0 && right[n + i - j] == 0) {
                // 将当前行可放的皇后位置赋给Q[i]数组,i为当前行
                Q[i] = j;
                // 这一列,左斜线以及右斜线不能放置皇后
                col[j] = left[i + j] = right[n + i - j] = 1;
                // 表示行号以及皇后数
                if (i < n) {
                    // 进入下一行递归
                    nextQueen(i + 1, n, col, left, right, Q);
                }
                // 表示 i==N 时,N个皇后放置完成
                else {
                    // 输出
                    printQueen(i, Q);
                }
                //清0,进入输出后需要清0，这一行没找到回溯需要清0；
                col[j] = left[i + j] = right[n + i - j] = 0;
            }
        }
    }

    private static void printQueen(int n, int[] Q) {
        StringBuffer sb = new StringBuffer();
        List<String> oneQueen = new ArrayList<>();
        // 输出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == Q[i]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            // 记录每一行数据
            oneQueen.add(sb.toString());
        }
        // 记录一组皇后
        queenList.add(oneQueen);
    }
}
