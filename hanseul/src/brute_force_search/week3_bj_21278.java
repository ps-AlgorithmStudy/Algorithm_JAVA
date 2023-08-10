package brute_force_search;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class week3_bj_21278 {
    static int N, M;
    static int[][] map;
    static boolean[] v, visited;
    static int[] depth;
    static int min;
    static int minS1, minS2;
    static int[] b = new int[2];

    static class Bfs{
        int store;
        int depth;
        public Bfs(int store, int depth){
            this.store = store;
            this.depth = depth;
        }
    }
    static void comb(int cnt, int start){
        if (cnt == 2){
            // cal 최단 경로
            int tmp = shortPathAll(b[0], b[1]);
            //System.out.println(b[0] + " " + b[1] + " " + tmp);
            if (tmp < min){
                min =  tmp;
                minS1 = b[0];
                minS2 = b[1];
            }
            return ;
        }
        for (int i = start; i < N; i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static int shortPathAll(int s1, int s2){
        visited = new boolean[N];
        int result = 0;
        depth = new int[N];
        for (int i = 0; i < N; i++) {
            depth[i] = N;
        }
        bfs(s1);
        bfs(s2);
        for (int i = 0; i < N; i++) {
            result += depth[i];
        }
        return result;
    }

    static void bfs(int s1){
        Queue<Bfs> queue = new LinkedList<>();
        visited = new boolean[N];

        queue.add(new Bfs(s1, 0));

        while (!queue.isEmpty()){
            Bfs bf = queue.remove();
            //System.out.println(bf.store + " " + bf.depth);
            visited[bf.store] = true;
            if (bf.depth < depth[bf.store]){
                depth[bf.store] = bf.depth;
            }
            else{
                continue;
            }
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                if (map[bf.store][i] == 1){
                    queue.add(new Bfs(i, bf.depth + 1));
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        v = new boolean[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = map[b][a] = 1;
        }
        min = N * N;
        comb(0, 0);

        System.out.println(++minS1 + " " + ++minS2 + " " + min * 2);
    }
}
