package week7;

import java.util.*;
import java.io.*;

public class bj_1238_파티 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 학생 수
        int M = Integer.parseInt(st.nextToken());  // 도로 수
        int X = Integer.parseInt(st.nextToken())-1;  // 마을 번호
        int[][] town = new int[N][N];   // 되돌아가는 경로
        int[][] townReverse = new int[N][N];  // 마을 찾아가는 경로
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int time = Integer.parseInt(st.nextToken());
            town[from][to] = time;
            townReverse[to][from] = time;
        }
        int[] dist = new int[N];  //
        int[] distReverse = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(distReverse, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1],o2[1]));  // 도착점, 시간

        pq.clear();
        dijkstra(X,town,dist,new boolean[N],pq);
        pq.clear();
        dijkstra(X,townReverse,distReverse,new boolean[N],pq);

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, dist[i]+distReverse[i]);
        }
        return max;
    }
    public static void dijkstra(int start, int[][] town, int[] dist, boolean[] visited, PriorityQueue<int[]> pq) {
        visited[start] = true;
        dist[start] = 0;
        pq.add(new int[]{start, dist[start]});
        while(!pq.isEmpty()) {
            int cur = pq.poll()[0];
            visited[cur] = true;  // 방문 처리
            for(int i=0; i<town.length; i++) {
                if(visited[i]) continue; // 이미 방문했으면 넘어가기
                if(town[cur][i]==0) continue;  // 연결되어 있지 않은 도시
                if(dist[i]<dist[cur]+town[cur][i]) continue;  // 기존 거리가 더 짧은 경우
                dist[i] = dist[cur]+town[cur][i];
                pq.add(new int[]{i,dist[i]});
            }

        }
    }
}
