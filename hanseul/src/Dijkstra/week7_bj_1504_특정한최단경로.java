package Dijkstra;

import java.io.*;
import java.util.*;

public class week7_bj_1504_특정한최단경로 {

    static int N, E;
    static int v1, v2;
    static ArrayList<int[]>[] g;


    static int dijkstra(int start, int end){
        if (start == end) return 0;
        int[] minEdge = new int[N];
        boolean[] v = new boolean[N];
        int result = 0;

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[start] = 0;
        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!v[j] && min > minEdge[j]){
                    minVertex = j;
                    min = minEdge[j];
                }
            }

            if (minVertex == -1) return -1;
            v[minVertex] = true;
            result += min;
            if (minVertex == end) break;

            for(int[] edge : g[minVertex]){
                if (!v[edge[0]] && minEdge[edge[0]] > min + edge[1]){
                    minEdge[edge[0]] = min + edge[1];
                }
            }
        }
        if (minEdge[end] == Integer.MAX_VALUE) return -1;
        return minEdge[end];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        g = new ArrayList[N];

        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            g[from].add(new int[] {to, weight});
            g[to].add(new int[] {from, weight});
        }

        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        int min = Integer.MAX_VALUE;

        int[] d1 = new int[3];
        int[] d2 = new int[3];

        d1[0] = dijkstra(0, v1);
        d1[1] = dijkstra(v1, v2);
        d1[2] = dijkstra(v2, N - 1);

        d2[0] = dijkstra(0, v2);
        d2[1] = dijkstra(v2, v1);
        d2[2] = dijkstra(v1, N - 1);


        if (!(d1[0] == -1 ||d1[1] == -1 ||d1[2] == -1))
            min = (min < d1[0] + d1[1] + d1[2]) ? min : d1[0] + d1[1] + d1[2];
        if (!(d2[0] == -1 ||d2[1] == -1 ||d2[2] == -1))
            min = (min < d2[0] + d2[1] + d2[2]) ? min : d2[0] + d2[1] + d2[2];

        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }
//    (1 -> v1 -> v2 -> N)
}