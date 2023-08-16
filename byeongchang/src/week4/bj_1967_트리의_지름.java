package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_1967_트리의_지름 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/bj_1967.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] tree = new ArrayList[N+1];   //  0: node num, 1: weight
        for(int i=0; i<=N; i++) tree[i] = new ArrayList<>();
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[parent].add(new int[]{child, weight});
            tree[child].add(new int[]{parent, weight});
        }
        int[] dist = bfs(1, tree, N);
        //System.out.println(Arrays.toString(bfs(8,tree,N)));
        int max = 0;
        int idx = 0;
        for(int i=0; i<=N; i++) {
            if(max < dist[i]){
                max = dist[i];
                idx = i;
            }
        }
        dist = bfs(idx, tree, N);
        //System.out.println(Arrays.toString(dist));
        max = 0;
        for(int i=0; i<=N; i++) {
            if(max < dist[i]){
                max = dist[i];
            }
        }
        System.out.println(max);
        br.close();
    }
    public static int[] bfs(int start, ArrayList<int[]>[] tree, int N) {
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        while(!q.isEmpty()){
           int cur = q.poll();
           for(int[] node : tree[cur]) {
               if(!visited[node[0]]) {
                   visited[node[0]] = true;
                   dist[node[0]] = dist[cur] + node[1];
                   q.offer(node[0]);
               }
           }
        }
        return dist;
    }

}
