package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2211
public class Solution_bj_2211_네트워크_복구 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.print(solution(br, st));
        br.close();
    }
    public static String solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] network = new int[N+1][N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            network[A][B] = C;
            network[B][A] = C;
        }

        /* circuit[컴퓨터 번호] = 연결해야 하는 컴퓨터 번호 */
        int[] circuit = djikstra(1, network, N);

        StringBuilder sb = new StringBuilder();
        sb.append(N-1).append("\n");
        for(int i=2; i<=N; i++) {
            sb.append(i).append(" ").append(circuit[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * 슈퍼컴퓨터로부터 각 컴퓨터까지의 최단 거리를 구하기 위한 다익스크라
     * @param start 슈퍼컴퓨터 번호
     * @param network 네트워크
     * @param N 컴퓨터의 수
     * @return 최단 거리를 위해 연결해야 하는 인접 컴퓨터 번호 저장 배열
     */
    public static int[] djikstra(int start, int[][] network, int N) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);  // 정점, 가중치
        int[] circuit = new int[N+1];  // 연결해야 하는 컴퓨터 번호 저장
        int[] dist = new int[N+1];    // 거리 저장, MAX_VALUE로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new int[] {start, dist[start]});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int w = cur[1];
            if(w > dist[v]) continue;  // 이미 최단 거리가 구해진 경우
            for(int i=1; i<=N; i++) {
                if(network[v][i] == 0 || dist[i] <= w + network[v][i]) continue;
                dist[i] = w + network[v][i];  // 최단 거리 갱신
                pq.offer(new int[] {i, dist[i]});
                circuit[i] = v;  // 연결해야 하는 컴퓨터 번호 갱신
            }
        }
        return circuit;
    }

}
