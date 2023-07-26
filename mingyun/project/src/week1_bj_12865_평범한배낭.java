import com.sun.org.apache.xpath.internal.operations.String;

import java.io.*;
import java.util.*;

public class week1_bj_12865_평범한배낭 {
    public static void main(java.lang.String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_12865.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][k+1];
        int[] w = new int[n+1];
        int[] v = new int[n+1];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            w[i+1] = Integer.parseInt(st.nextToken());
            v[i+1] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                if (j-w[i] >=0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
