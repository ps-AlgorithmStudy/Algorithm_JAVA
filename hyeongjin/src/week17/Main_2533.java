package week17;

import java.util.*;
import java.io.*;

public class Main_2533 {
    static int N;
    static boolean[] visited;
    static List<List<Integer>> map;
    static int[][] dp;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new ArrayList<>();
        visited = new boolean[N+1];
        dp = new int[N+1][2];

        for (int i = 0; i <= N ; i++) {
            map.add(new ArrayList<>());
        }
        // 0 : 해당 노드가 얼리어답터일 때
        // 1 : 해당 노드가 얼리어답터가 아닐 때
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }

        dp(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    static void dp(int current){
        visited[current] = true;
        dp[current][0] = 0;
        dp[current][1] = 1;

        for(int next : map.get(current)){
            if(!visited[next]){
                dp(next);
                // 1. 현재 노드가 얼리어답터가 아닐 경우 (0)
                dp[current][0] += dp[next][1]; // 인접한 자식 노드가 얼리어답터

                // 2. 현재 노드가 얼리어답터인 경우 (1)
                dp[current][1] += Math.min(dp[next][0], dp[next][1]); // 인접한 자식 노드가 얼리어답터가 아니어도 됨
            }
        }

    }
}
