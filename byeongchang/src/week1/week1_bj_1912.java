package week1;

import java.util.*;
import java.io.*;
// 1912 연속합
public class week1_bj_1912 {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/bj_1912.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        //testCase(br, st);
        solution(br, st);
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];  // 주어진 수열
        int[] dp = new int[N];  //  연속합 저장
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp[0] = arr[0];
        int max = dp[0];  // 전체 최대값 저장
        for(int i = 1; i < N; i++){
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);  // 이전 연속합 + 현재값보다 현재값이 크면 굳이 연속을 이어갈 필요 없음
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
    // 여러 테케 돌릴 때
    public static void testCase(BufferedReader br, StringTokenizer st) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            System.out.print("Case #"+tc+": ");
            solution(br, st);
        }
    }

}
