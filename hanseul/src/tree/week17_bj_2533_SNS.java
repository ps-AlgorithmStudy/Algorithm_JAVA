package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week17_bj_2533_SNS {

    static int N;
    static boolean[] v;
    static ArrayList<Integer>[] g;
    static int[][] dp;

    static void dfs(int n) {
        v[n] = true;
        dp[n][0] = 0;
        dp[n][1] = 1;

        for(int c : g[n]) {
            if(!v[c]) {
                dfs(c);
                dp[n][0] += dp[c][1];
                dp[n][1] += Math.min(dp[c][0], dp[c][1]);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];
        v = new boolean[N + 1];
        g = new ArrayList[N + 1];

        for (int i = 0; i < N+1; i++)
            g[i] = new ArrayList<>();


        for (int i = 1; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
