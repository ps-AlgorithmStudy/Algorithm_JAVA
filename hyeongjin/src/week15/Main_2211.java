package week15;
import java.io.*;
import java.util.*;
public class Main_2211 {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][n+1];
        for(int i = 0 ; i <= n ; i++) Arrays.fill(map[i],INF);

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(cost,map[a][b]);
            map[b][a] = Math.min(cost,map[b][a]);
        }

        // 시작
        List<int[]> result = new ArrayList<>();
        int[] path = new int[n+1]; // 컴퓨터 번호 저장 배열
        int[] dist = new int[n+1]; // 거리 저장 배열
        Arrays.fill(dist, INF);     // 거리 초기화
        boolean[] visited = new boolean[n+1];
        dist[1] = 0; //시작점

        for(int i = 0 ; i < n ; i++){
            int minIdx = 0;
            int minVal = INF;
            for(int j = 1; j <= n ; j++){ // 다음 최단 경로를 탐색
                if(!visited[j] && minVal > dist[j]){ // 방문 가능 & 방문 시 최단거리일 경우

                    minVal = dist[j];
                    minIdx = j;
                }
            }
            if(minVal == INF) break; // 방문할 정점이 없을 때 종료
            visited[minIdx] = true;  // 방문처리

            if(minIdx != 1){
                result.add(new int[] {path[minIdx],minIdx});
            }

            for(int j = 1; j <= n ; j++){
                if(!visited[j] && dist[j] > minVal + map[minIdx][j]){
                    dist[j] = minVal + map[minIdx][j]; // idx : j 까지 최단거리 갱신
                    path[j] = minIdx;                  // 바로 전의 정점 기록
                }
            }
        }

        System.out.println(result.size());
        for(int[] edge : result){
            System.out.printf("%d %d\n",edge[0],edge[1]);
        }
    }

}