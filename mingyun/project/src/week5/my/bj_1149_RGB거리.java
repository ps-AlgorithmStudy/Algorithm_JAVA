package week5.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class bj_1149_RGB거리 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_1149.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dp[i][0] = Integer.parseInt(st.nextToken());dp[i][1] = Integer.parseInt(st.nextToken());dp[i][2] = Integer.parseInt(st.nextToken());
            if (i!=0) {
                dp[i][0] += Math.min(dp[i-1][1], dp[i-1][2]);
                dp[i][1] += Math.min(dp[i-1][0], dp[i-1][2]);
                dp[i][2] += Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }
        System.out.println(Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2])));
    }
}