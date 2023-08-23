package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_15681_트리와_쿼리 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/bj_15681.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] treeTemp = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            treeTemp[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            treeTemp[num1].add(num2);
            treeTemp[num2].add(num1);
        }
        makeTree(R, treeTemp);
        int[] size = new int[N+1];
        checkChild(5,treeTemp,size);
        for(int i=0; i<Q; i++) {
            int q = Integer.parseInt(br.readLine());
            if(q == R) {
                sb.append(N).append("\n");
                continue;
            }
            sb.append(size[q]).append("\n");

        }
        br.close();
        System.out.print(sb);
    }

    public static void checkChild(int start, ArrayList<Integer>[] tree, int[] size) {
        size[start] = 1;
        for(int t: tree[start]){
            checkChild(t,tree,size);
            size[start] += size[t];
        }
    }

    public static void makeTree(int R, ArrayList<Integer>[] tree) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[tree.length];
        visited[R] = true;
        for(int t: tree[R]) {
            q.offer(t);
            tree[t].remove((Integer)R);
            visited[t] = true;
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int t: tree[cur]) {
                if(visited[t]) continue;
                q.offer(t);
                for(int i=0; i< visited.length;i++){
                    if(visited[i]) tree[t].remove((Integer)i);
                }
                visited[t] = true;
            }
        }
    }

}
