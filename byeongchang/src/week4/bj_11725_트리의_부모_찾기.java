package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_11725_트리의_부모_찾기 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/bj_11725.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = 2;
        for(int tc=1; tc<=T; tc++) {
            solution(br, st);
        }
        br.close();
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N+1];  // 자식 저장
        for(int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        int[] parent = new int[N+1];  // 부모 저장
        for(int i=1; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            tree[num1].add(num2);
            tree[num2].add(num1);
        }
        //dfs(1,1,tree,parent);
        bfs(1,1,tree,parent);
        for(int i=2;i<=N;i++) sb.append(parent[i]).append("\n");
        System.out.print(sb);
    }
    public static void dfs(int cnt, int prt, ArrayList<Integer>[] tree, int[] parent) {
        parent[cnt] = prt;
        for(int t: tree[cnt]){
            if(parent[t] != 0) continue;
            dfs(t,cnt,tree,parent);
        }
    }
    public static void bfs(int cnt, int prt, ArrayList<Integer>[] tree, int[] parent) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        parent[cnt] = prt;
        q.offer(cnt);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int t: tree[cur]) {
                if(parent[t] != 0) continue;
                parent[t] = cur;
                q.offer(t);
            }
        }
    }
}
