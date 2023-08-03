package week1;

import java.util.*;
import java.io.*;
// 25215번 타이핑
public class week1_bj_25215 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arrChar = br.readLine().toCharArray();
        int[][] dp = new int[arrChar.length][2];  //  마름모 활성화, 비활성화
        if(Character.isUpperCase(arrChar[0])){  // 첫번째 dp 초기화
            dp[0][0] = 2;
            dp[0][1] = 2;
        }
        else {
            dp[0][0] = 1;
            dp[0][1] = 2;
        }
        for(int i=1;i<arrChar.length;i++){
            if(Character.isUpperCase(arrChar[i])) {
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + 2;  // i번째 끝나고 마름모 비활성화 상태인 경우의 최소값
                dp[i][1] = Math.min(dp[i-1][0] + 2, dp[i-1][1] + 1);  // i번째 끝나고 마름모 활성화 상태인 경우의 최소값
            }
            else {
                dp[i][0] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 2);
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + 2;
            }
        }

        System.out.println(Math.min(dp[arrChar.length-1][0], dp[arrChar.length-1][1]));
    }
}
