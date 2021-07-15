package com.futurell.leetcode.A_31_40;

/**
 * @Description: 37. 解数独
 * @Author: lilei58
 * @Date: Created in 2021/7/15 7:58 下午
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 *  数字 1-9 在每一行只能出现一次。
 *  数字 1-9 在每一列只能出现一次。
 *  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 示例：
 *  输入：
 *       board = [["5","3",".",".","7",".",".",".","."]
 *               ,["6",".",".","1","9","5",".",".","."]
 *               ,[".","9","8",".",".",".",".","6","."]
 *               ,["8",".",".",".","6",".",".",".","3"]
 *               ,["4",".",".","8",".","3",".",".","1"]
 *               ,["7",".",".",".","2",".",".",".","6"]
 *               ,[".","6",".",".",".",".","2","8","."]
 *               ,[".",".",".","4","1","9",".",".","5"]
 *               ,[".",".",".",".","8",".",".","7","9"]]
 *  输出：
 *               [["5","3","4","6","7","8","9","1","2"]
 *               ,["6","7","2","1","9","5","3","4","8"]
 *               ,["1","9","8","3","4","2","5","6","7"]
 *               ,["8","5","9","7","6","1","4","2","3"]
 *               ,["4","2","6","8","5","3","7","9","1"]
 *               ,["7","1","3","9","2","4","8","5","6"]
 *               ,["9","6","1","5","3","7","2","8","4"]
 *               ,["2","8","7","4","1","9","6","3","5"]
 *               ,["3","4","5","2","8","6","1","7","9"]]
 *  解释：输入的数独如上图所示
 */
public class CG_SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                ,{'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                ,{'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                ,{'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                ,{'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                ,{'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                ,{'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                ,{'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                ,{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        solveSudoku(board);
        int i = 0;
    }

    public static void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }

        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '1', k = (i / 3) * 3 + j / 3;
                row[i][num] = col[j][num] = box[k][num] = true;
            }
        }
        solveSudokuHelper(board, 0, row, col, box);
    }

    public static boolean solveSudokuHelper(char[][] board, int n, boolean[][] row, boolean[][] col, boolean[][] box) {
        if (n == 81) {
            return true;
        }
        int i = n / 9, j = n % 9;
        if (board[i][j] != '.') {
            return solveSudokuHelper(board, n + 1, row, col, box);
        }

        int k = (i / 3) * 3 + j / 3;
        for (int num = 0; num < 9; num++) {
            if (row[i][num] || col[j][num] || box[k][num]) {
                continue;
            }
            board[i][j] = (char) (num + '1');
            row[i][num] = col[j][num] = box[k][num] = true;
            if (solveSudokuHelper(board, n + 1, row, col, box)) {
                return true;
            }
            row[i][num] = col[j][num] = box[k][num] = false;
        }
        board[i][j] = '.';
        return false;
    }
}
