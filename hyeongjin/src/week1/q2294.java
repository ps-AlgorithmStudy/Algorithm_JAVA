import java.io.*;
import java.util.*;
public class q2294 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        int[] coin = new int[n+1];
        int[] dp = new int[k+1];
        dp[0] = 0;

        // 최대의 경우의 수 (10000) +1 로 배열 초기화
        for(int i = 1;i<k+1;i++) dp[i] = 10001;

        // n개 종류의 동전 입력
        for(int i = 1; i<=n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        // 동전 종류 만큼
        for(int i = 1;i<=n;i++){
            // k원에 도달할 때 까지
            for(int j = coin[i];j<=k;j++){
                dp[j] = Math.min(dp[j],dp[j-coin[i]]+1);
            }
        }
        if (dp[k] == 10001) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}
