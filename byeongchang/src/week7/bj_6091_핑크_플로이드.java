package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_6091_핑크_플로이드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static String solution(BufferedReader br, StringTokenizer st) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=i+1; j<N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                dist[i][j] = tmp;
                dist[j][i] = tmp;
            }
        }
        ArrayList<Integer>[] ans = new ArrayList[N];
        for(int i=0; i<N; i++) ans[i] = new ArrayList<>();
        prim(dist, ans);
        for(int i=0; i<N; i++) {
            sb.append(ans[i].size());
            ans[i].sort(null);
            for(int j=0; j<ans[i].size(); j++) {
                sb.append(" ").append(ans[i].get(j)+1);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public static void prim(int[][] dist, ArrayList<Integer>[] ans) {
        int N = dist.length;
        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);  // start, end, dist
        pq.offer(new int[]{0, 0, 0});  // initial start point
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            int prev = arr[0];
            int cur = arr[1];
            if(visited[cur]) continue;
            if(arr[2]!=0) {
                ans[prev].add(cur);
                ans[cur].add(prev);
            }
            visited[cur] = true;
            for(int i=0; i<N; i++) {
                if(visited[i] || dist[cur][i]==0) continue;
                pq.offer(new int[]{cur, i, dist[cur][i]});
            }
        }
    }

}
