package com.futurell.leetcode.A_41_50;

/**
 * @Description: 48. 旋转图像
 * @Author: lilei58
 * @Date: Created in 2021/9/7 上午7:21
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在"原地"旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 *  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *  输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 *  输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 *  输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 示例 3：
 *  输入：matrix = [[1]]
 *  输出：[[1]]
 *
 * 示例 4：
 *  输入：matrix = [[1,2],[3,4]]
 *  输出：[[3,1],[4,2]]
 */
public class DH_RotateImage {

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        rotate(arr);

        int i = 0;
    }

    /**
     *  思路:
     * 1. 原地旋转 --- rotate
     *    1  2  3
     *    4  5  6
     *    7  8  9
     */
    public static void rotate(int[][] matrix) {
        // 计算长度
        int n = matrix.length;
        // 以3*3为例: 只需要旋转 1,2 即可
        // 以4*4为例: 只需要旋转 1,2,5,6 即可
        // 横轴
        // 5*5 矩阵 /2 = 2 --> i IN (0,1)
        // 4*4 矩阵 /2 = 2 --> i IN (0,1)
        for (int i = 0; i < n / 2; ++i) {
            // 纵轴
            // 5*5 矩阵 /2 = 3 --> i IN (0,1,2)
            // 4*4 矩阵 /2 = 2 --> i IN (0,1)
            for (int j = 0; j < (n + 1) / 2; ++j) {
                // 以3*3为例:第一次进入
                // 每次顺时针获取对应位置的值
                // temp = 1
                // matrix[n - j - 1][i] = 7
                // matrix[n - i - 1][n - j - 1] = 9
                // matrix[j][n - i - 1] = 3
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
