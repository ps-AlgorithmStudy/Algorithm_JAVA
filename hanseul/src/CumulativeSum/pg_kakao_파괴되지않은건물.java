package CumulativeSum;

public class pg_kakao_파괴되지않은건물 {

    class Solution {

        int[][] arr;
        int N, M;
        void exec(){
            for (int x = 1; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    arr[x][y] += arr[x - 1][y];
                }
            }
            for (int y = 1; y < M; y++) {
                for (int x = 0; x < N; x++) {
                    arr[x][y] += arr[x][y - 1];
                }
            }

        }
        public int solution(int[][] board, int[][] skill) {
            N = board.length;
            M = board[0].length;

            arr = new int[N + 1][M + 1];

            for(int[] s : skill){
                int x1 = s[1], y1 = s[2];
                int x2 = s[3], y2 = s[4];
                int degree = s[5];
                if (s[0] == 1) degree *= -1;

                arr[x1][y1] += degree;
                arr[x1][y2 + 1] += (degree * -1);
                arr[x2 + 1][y1] += (degree * -1);
                arr[x2 + 1][y2 + 1] += degree;
            }

            exec();
            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] + arr[i][j] > 0) answer++;
                }
            }
            return answer;
        }
    }
}
