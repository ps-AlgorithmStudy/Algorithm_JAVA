package dp;

public class week20_pg_2024kakao_산모양타일링 {
    class Solution {
        int[] dp;
        public int solution(int n, int[] tops) {
            dp = new int[2*n + 1];

            dp[0] = 1; // 초기화 경우의 수 지정
            dp[1] = 2;
            if (tops[0] == 1) dp[1] = 3; // 두번째 삼각형 위에 삼각형이 추가됐을 때
            for(int i = 2;i < 2*n + 1; i++){
                dp[i] = (dp[i - 1] + dp[i - 2])% 10007;

                if (i % 2 == 1 && tops[i / 2] == 1) // 삼각형이 추가로 위에 존재할때
                    dp[i] = (dp[i] + dp[i - 1])% 10007;
            }

            return  dp[2 * n] % 10007;
        }
    }
}
