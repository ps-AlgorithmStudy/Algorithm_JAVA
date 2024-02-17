package week23;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] array = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0], r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            if (type == 1) {
                degree = -degree;
            }
            array[r1][c1] += degree;
            array[r1][c2 + 1] -= degree;
            array[r2 + 1][c1] -= degree;
            array[r2 + 1][c2 + 1] += degree;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                array[i][j] += array[i][j - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] += array[i - 1][j];
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += array[i][j];
                if (board[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}