package graph;
import java.util.*;
import java.io.*;
public class week14_bj_2098_외판원순회 {
    static int N;
    static int[][] g;
    static int[][] dp;
    static int INF = 16_000_000;
    static int tsp(int now, int v){
        if(v == (1 << N) - 1){
            if(g[now][0] != 0) return g[now][0];
            else {
                dp[now][v] = INF;
                return INF;
            }
        }

        if(dp[now][v] != -1){
            return dp[now][v];
        }

        for(int i = 0; i < N; i++){
            int next = v |(1 << i);

            if(g[now][i] != 0 && (v & (1 << i)) == 0){
                if (dp[now][v] == -1) // 초기화
                    dp[now][v] = tsp(i,next) + g[now][i];
                else
                    dp[now][v] = Math.min(dp[now][v], tsp(i,next) + g[now][i]);
            }
        }

        if (dp[now][v] == -1) return INF;
        return dp[now][v];

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        g = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }
        System.out.println(tsp(0, 1));

    }
}
