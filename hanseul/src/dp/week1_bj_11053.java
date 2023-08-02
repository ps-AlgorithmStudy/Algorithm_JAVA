package dp;

import java.io.*;
import java.util.*;

public class week1_bj_11053 {

    static int N;
    static int arr[];
    static int dp[];
    static int max;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "");

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        dp = new int[N];

        for (int i=0;i<N;i++){
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;
        }
        max = 1;
        for (int i= 0; i < N ;i++)
        {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--)
            {
                if (arr[i] > arr[j])
                    dp[i] = (dp[j] + 1 > dp[i]) ? dp[j] + 1 : dp[i];
            }
            max = (max > dp[i]) ? max : dp[i];
        }
        System.out.println(max);
    }
}
