package week1;

import java.io.*;
import java.util.*;

public class bj_20542_받아쓰기 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_20542.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        int [][] dp = new int[n+1][m+1];

        for (int i=0;i<=n;i++) {
            dp[i][0] = i;
        }
        for (int i=0;i<=m;i++) {
            dp[0][i] = i;
        }

        char c1;
        char c2;

        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {
                dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;

                c1 = s1[i-1];
                c2 = s2[j-1];
                if (c1 == c2 ||
                    (c1 == 'v' && c2 == 'w') ||
                    (c1 == 'i' && c2 == 'j') ||
                    (c1 == 'i' && c2 == 'l')) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
