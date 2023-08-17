package week5.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_9465_스티커 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_9465.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int TC=0; TC<T;TC++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n+2];
            int[][] dp = new int[2][n+2];

            for (int i=0;i<2;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<n;j++) {
                    arr[i][j+2] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i=2;i<=n+1;i++) {
                dp[0][i] = Math.max(dp[0][i-2], Math.max(dp[1][i-1], dp[1][i-2])) + arr[0][i];
                dp[1][i] = Math.max(dp[1][i-2], Math.max(dp[0][i-1], dp[0][i-2])) + arr[1][i];
            }
            System.out.println(Math.max(dp[0][n+1], dp[1][n+1]));
        }
    }
}
