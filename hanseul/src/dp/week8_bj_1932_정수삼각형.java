package dp;

import java.util.*;
import java.io.*;

public class week8_bj_1932_정수삼각형 {

    static int[][] triangle;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        triangle = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = triangle[0][0];
        int max = dp[0][0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                int a = dp[i - 1][j];
                int b = 0;
                if (j - 1 >= 0) b = dp[i - 1][j - 1];
                dp[i][j] = Math.max(a, b) + triangle[i][j];
                if (N - 1 == i)
                    max = Math.max(dp[i][j], max);
            }
        }
//        for (int[] arr: dp){
//            System.out.println(Arrays.toString(arr));
//        }
        for (int j = 0;j < N;j++) {
            if (dp[N - 1][j] > max) max = dp[N][j];
        }
        System.out.println(max);
    }

}
