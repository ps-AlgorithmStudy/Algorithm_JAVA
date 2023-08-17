package week2;

import java.util.*;
import java.io.*;

public class q26215 {
    static int N;
    static final int MAX_VAL = 1440;
    public static void main(String[] args) throws Exception {

        int sum = 0;
        int max = 0;
        int ans = 0;
        int res = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int house = Integer.parseInt(st.nextToken());
            sum += house;
            max = Math.max(max,house);
        }

        // 최고값을 뺀 나머지
        res = sum - max;

        // 기본 정답 -> 절반(올림)
        ans = res / 2;
        if(res%2==1) ans += 1;

        // 나머지 값이 rest보다 크다 = 총합이 최대값의 두배보다 작다 => 최대값만큼 시행하면 끝난다
        if (max>res) ans = max;
        if(ans>MAX_VAL) System.out.println(-1);
        else System.out.println(ans);
    }
}
