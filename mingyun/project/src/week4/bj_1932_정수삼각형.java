package project.src.week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_1932_정수삼각형 {

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_1932.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n+1];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<=i;j++)
                arr[i][j+1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n+1];
        dp[0][1] = arr[0][1];
        for (int i=1;i<n;i++) {
            for (int j=1;j<=i+1;j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }
        int res = 0;
        for (int i=1;i<=n;i++) {
            res = Math.max(dp[n-1][i], res);
        }

        System.out.println(res);
    }

}
