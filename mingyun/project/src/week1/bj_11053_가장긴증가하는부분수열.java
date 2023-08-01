package week1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11053_가장긴증가하는부분수열 {

    public static int dpWork(int n, int[] arr) {
        int[] dp = new int[n];
        int result = 1;

        for(int i=0;i<n;i++) {
            dp[i] = 1;
            for (int j=0;j<i;j++) {
                if (arr[j]<arr[i]) {
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                    result = Math.max(result,dp[i]);
                }
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_11053.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dpWork(n,arr));
    }
}
