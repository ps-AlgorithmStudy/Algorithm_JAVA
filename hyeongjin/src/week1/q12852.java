import java.util.*;

public class q12852 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        // dp배열, 방문 배열
        int[] dp = new int[input+2];
        int[] visited = new int[input+2];

        // target : 1
        dp[1] = 0;
        int res;
        String ans = "";


        //dp[2] 부터
        for (int i = 2; i < input+1; i++) {

            // 이전 dp는 최소 dp[i-1] +1
            dp[i] = dp[i-1]+1;
            visited[i] = i-1;

            // 최솟값을 배열에 넣기
            if(i%3==0 && dp[i/3]+1<dp[i]) {
                dp[i] = Math.min(dp[i/3]+1, dp[i]);
                visited[i] = i/3;
            }if(i%2==0&& dp[i/2]+1<dp[i]) {
                dp[i] = dp[i/2]+1;
                visited[i] = i/2;
            }
        }

        res = dp[input];
        System.out.println(res);
        while(input > 0){
            ans += input + " ";
            input = visited[input];
        }
        System.out.println(ans);
    }
}