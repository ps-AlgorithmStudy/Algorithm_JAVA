import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2302극장좌석 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[41];
        int[] vips= new int[M];
        int result = 1;

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        // dp
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 고정석
        for (int i = 0; i < M; i++) {
            vips[i]= Integer.parseInt(br.readLine());
        }

        // 고정석 기준으로 끊어서 나온 덩어리끼리 곱
        int lastVip =0;
        for (int vip: vips) {
            result *= dp[vip-lastVip-1];
            lastVip = vip;
        }

        // 마지막 덩어리 처리
        result *= dp[N-lastVip];
        System.out.println(result);
    }
}
