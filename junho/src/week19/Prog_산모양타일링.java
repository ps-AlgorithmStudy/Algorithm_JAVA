package week19;

public class Prog_산모양타일링 {
    final int MAX_N = 100_000;
    final int MOD = 10_007;
    
    // dp[k][0] : k번째 아래 방향 정삼각형을 덮는 방법이 3번 방법인 경우의 수
    // dp[k][1] :  k번째 아래 방향 정삼각형을 덮는 방법이 3번 방법이 아닌 경우의 수
    int[][] dp; // dp[k] := dp[k][0] + dp[k][1];
    
    public int solution(int n, int[] tops) {        
        dp = new int[MAX_N + 1][2];
        dp[0][0] = 1;
        
        for(int i = 0; i < n; i++) {
            dp[i + 1][0] = (dp[i][0] * (2 + tops[i]) + dp[i][1] * (1 + tops[i])) % MOD;
            dp[i + 1][1] = (dp[i][0] + dp[i][1]) % MOD;
            
        }
        
        return (dp[n][0] + dp[n][1]) % MOD;
    }
}
