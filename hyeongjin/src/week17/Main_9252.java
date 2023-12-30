package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9252{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int N = a.length;
        int M = b.length;

        int[][] dp = new int[N+1][M+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i+1][j+1] = a[i]==b[j]?
                        dp[i][j]+1 :Math.max(dp[i+1][j],dp[i][j+1]);
            }
        }
        System.out.println(dp[N][M]); // answer1

        while(N >= 1 && M >= 1){
            if(dp[N][M] == dp[N-1][M]){
                N--;
            }else if(dp[N][M] == dp[N][M-1]){
                M--;
            }else{
                sb.insert(0, b[M-1]);
                N--;
                M--;
            }
        }
        System.out.println(sb);
    }
}