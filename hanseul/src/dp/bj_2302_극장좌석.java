package dp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class bj_2302_극장좌석 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] vips = new int[M + 1];
        for (int i = 0; i < M; i++) {
            vips[i] = Integer.parseInt(br.readLine());
        }

        int seat = 1, vip = 0; // seat : 연속적인 일반 좌석의 수 , vip : vips 배열의 index
        int result = 1;
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            if (vips[vip] == i){ // vip 좌석이라면
                dp[i] = dp[i - 1]; // 이전에 있는 좌석 정보를 그대로 가져온다 ( 이 부분에서 vip 좌석이 연달아 있었을 경우 처리에 오류가 생겼음.)
                seat = 1;
//                System.out.println(vip);
                if (vip >= 0 && vips[vip - 1] != i - 1) // 이전에 있는 좌석이 vip 가 아니라면
                    result *= dp[i - 1];
                else dp[i] = 1; // 이전 좌석이 vip 좌석이라면
                vip++; // vips 배열 index 증가
                continue;
            }

            // vip 좌석이 아니라면
            if (seat == 1) dp[i] = 1;
            else if (seat == 2) dp[i] = 2;
            else dp[i] = dp[i - 1] + dp[i - 2];
            seat++;
        }

        // vip 좌석이 없거나, 마지막 좌석이 vip 좌석이 아니였다면
        if (M == 0 || vips[M - 1] != N) result *= dp[N];
        System.out.println(result);
    }
}
