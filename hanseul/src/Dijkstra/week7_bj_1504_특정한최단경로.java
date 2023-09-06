package Dijkstra;

import java.io.*;
import java.util.*;

public class week7_bj_1504_특정한최단경로 {

    static int N;
    static int[][] arr;
    static int[][] dp;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int dfs(int x, int y, int weight){
        if (dp[x][y] == 0)
            dp[x][y] = weight;
        if (dp[x][y] > weight)
            dp[x][y] = weight;
        else return 0;


    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int tc = 1;
        while (N != 0){
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            sb.append("Problem ").append(tc++).append(": ").append(arr[N-1][N-1]).append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }

}
