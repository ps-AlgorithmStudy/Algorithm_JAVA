package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2098 {
    static int N ;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new int[N][1<<N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][1] = 0; //시작 도시
        tps(0,1);
        System.out.println(answer);
    }
    public static void tps(int cur, int v){
        // 종료 판별 (모든 도시 방문 1111...11)
        if(v == ((1<<N)-1)){
            // 현재도시 -> 0(시작점)이 0이면 순환이X
            if(map[cur][0]==0) return;
            // 아니라면
            answer = Math.min(answer, dp[cur][v] + map[cur][0]);
            return;
        }
        for(int i = 0; i < N; i++){
           int next = 1<<i; // 다음 도시 탐색
            if((v|next)==v) continue; // 다음 도시가 방문한 도시라면 continue
            if(map[cur][i] == 0) continue; // 연결 되지 않은 경로라면 continue
            if(dp[cur][v] + map[cur][i] < dp[i][v|next]){
                // 누적 비용 + 다음 방문 비용이 << 다음 방문까지 누적비용보다 작은 경우
                dp[i][v|next] = dp[cur][v] + map[cur][i]; // 갱신
                tps(i, v|next);
            }
        }
    }
}
