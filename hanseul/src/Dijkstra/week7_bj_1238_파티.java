package Dijkstra;

import java.io.*;
import java.util.*;

public class week7_bj_1238_파티 {

    static int N, M, X;
    static int[][] g;
    static int[] minEdge;
    static boolean[] v;

    static int dijkstra(int go, int back){
        int result = 0;
        minEdge = new int[N];
        v = new boolean[N];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[go] = 0;

        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!v[j] && min > minEdge[j]){
                    minVertex = j;
                    min = minEdge[j];
                }
            }
            v[minVertex] = true;
            result += min;
            if (minVertex == back) break;

            for (int j = 0; j < N; j++) {
                if (!v[j] && g[minVertex][j] != 0 && minEdge[j] > g[minVertex][j]){
                    minEdge[j] = g[minVertex][j];
                }
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        g = new int[N][N];
        minEdge = new int[N];
        v = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            g[from][to] = weight;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (i == X - 1) continue;
            int tmp = dijkstra(X - 1, i) + dijkstra(i, X - 1);
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }
}
