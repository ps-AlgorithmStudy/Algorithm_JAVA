package Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class week15_bj_네트워크복구 {

    static int N, M;
    static ArrayList<int[]>[] g;
    static int[] path;

    static void dijkstra(int start){
        int[] minEdge = new int[N];
        boolean[] v = new boolean[N];

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        minEdge[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->
        {
            return Integer.compare(o1[1], o2[1]);
        }); // start, distance

        pq.offer(new int[] {start, minEdge[start]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int minVertex = cur[0];
            v[minVertex] = true;

            for(int[] edge : g[minVertex]){
                if (!v[edge[0]] && minEdge[edge[0]] > minEdge[minVertex] + edge[1]){
                    minEdge[edge[0]] = minEdge[minVertex] + edge[1];
//                    System.out/.println(edge[0]+ " " + minEdge[edge[0]]);
                    pq.offer(new int[]{edge[0], minEdge[edge[0]]});
                    path[edge[0]] = minVertex;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N];
        path = new int[N];

        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            g[from].add(new int[] {to, weight});
            g[to].add(new int[] {from, weight});
        }

        dijkstra(0);
        sb.append(N - 1).append("\n");

        HashSet<String> set = new HashSet<String>();
        for (int i = 1; i < N; i++) {
            int end = i;
            while (path[end] != -1){
                set.add(new String((end +1) + " " + (path[end] + 1)));
                end = path[end];
            }
        }

        System.out.print(sb.toString());
        for(String res : set) {
            System.out.println(res);
        }
    }
}
