import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/2098
public class Solution_bj_2098_외판원_순회 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        final int INF = Integer.MAX_VALUE / 2;
        int N = Integer.parseInt(br.readLine());
        int maxBit = (1<<N)-1;
        int[][] W = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[N][maxBit];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], -1);
        }

        return tsp(0, 1, maxBit, N, INF, W, dp);
    }

    /**
     * @param x: 현재 위치
     * @param visit: 방문한 도시 집합 비트
     * @param maxBit: 모든 도시를 방문한 집합 비트
     * @param N: 도시의 수
     * @param W: 도시 간의 이동비용
     * @param dp: 이동 저장
     * @return 최소 비용
     * */
    public static int tsp(int x, int visit, int maxBit, int N, final int INF, int[][] W, int[][] dp) {
        /* 모든 도시를 방문했을 때 */
        if(visit == maxBit) {
            /* 현재 도시에서 출발 도시로 돌아갈 수 없을 때 */
            if(W[x][0] == 0) return INF;
            /* 그 이외에는 출발 도시로 이동하는 비용 리턴 */
            return W[x][0];
        }

        /* 이미 방문했을 때 */
        if(dp[x][visit] != -1) return dp[x][visit];
        /* 아니면 방문 처리 */
        dp[x][visit] = INF;

        /* 다음 도시로 이동 */
        for(int i=0; i<N; i++) {
            if(i==x) continue;

            /* i 도시를 방문하지 않았을 때 */
            if(W[x][i]!=0 && (visit&(1<<i))==0) {
                dp[x][visit] = Math.min(dp[x][visit], tsp(i, visit|(1<<i), maxBit, N, INF, W, dp) + W[x][i]);
            }
        }

        return dp[x][visit];
    }
}
