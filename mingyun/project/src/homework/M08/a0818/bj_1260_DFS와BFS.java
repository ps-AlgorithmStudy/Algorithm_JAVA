package homework.M08.a0818;

import java.util.*;
import java.io.*;

public class bj_1260_DFS와BFS {

    static final StringBuilder sb = new StringBuilder();
    static int N, M, V;
    static boolean[] v;
    static PriorityQueue<Integer>[] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0818/res/input_1260.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //N, M, V
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        //map
        map = new PriorityQueue[N+1];
        for(int i=0;i<=N;i++) map[i] = new PriorityQueue<>();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
            if(!map[start].contains(end)) {
                map[start].add(end);
            }
            if(!map[end].contains(start)) {
                map[end].add(start);
            }
        }

        //dfs
        v = new boolean[N+1];
        dfs(V);
        sb.append("\n");

        //bfs
        v = new boolean[N+1];
        bfs(V);
        br.close();
        System.out.println(sb.toString());
    }

    static void dfs(int s) {
        v[s] = true;

        sb.append(s).append(" ");
        for (int integer : map[s]) {
            if (!v[integer]) {
                dfs(integer);
            }
        }
    }

    static void bfs(int s) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(s);
        v[s] = true;
        while(!queue.isEmpty()) {
            int start = queue.pollFirst();
            sb.append(start).append(" ");
            for (int integer : map[start]) {
                if (!v[integer]) {
                    v[integer] = true;
                    queue.offerLast(integer);
                }
            }
        }
    }

}
