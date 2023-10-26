package week9.발표;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기2 {
    static class Pipes {
        /**
         * 0 : 가로
         * 1 : 대각선
         * 2 : 세로
         * */
        long[] direction = new long[3];
        @Override
        public String toString() {
            return "가로:" + direction[0] + " 대각:" + direction[1] + " 세로:" + direction[2];
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/파이프옮기기2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Pipes[][] dp = new Pipes[n][n];
        boolean[][] map = new boolean[n][n];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) dp[i][j] = new Pipes();
        }

        dp[0][0].direction[0] = 1;
        dp[0][1].direction[0] = 1;
        for (int i=0;i<n;i++) {
            for (int j=2;j<n;j++) {
                if (map[i][j]) continue;
                if (i>0) {
                    if (!map[i-1][j-1] && !map[i-1][j] && !map[i][j-1]) {
                        dp[i][j].direction[1] = dp[i-1][j-1].direction[0] + dp[i-1][j-1].direction[1] + dp[i-1][j-1].direction[2];
                    }
                    dp[i][j].direction[2] = dp[i-1][j].direction[1] + dp[i-1][j].direction[2];
                }
                dp[i][j].direction[0] = dp[i][j-1].direction[0] + dp[i][j-1].direction[1];
            }
        }
        long result = 0;
        for (long p:dp[n-1][n-1].direction) {
            result += p;
        }

        System.out.println(result);
    }
}
