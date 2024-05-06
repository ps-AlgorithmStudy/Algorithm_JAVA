package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_1644_소수의연속합 {
    static int N;
    static int[] sum;
    static int[] a = new int[2];
    static int result = 0;
    static int idx = 1;

    static void comb(int start, int cnt){
        if (cnt == 2){
            if (sum[a[1]] < N) return;

            if (sum[a[1]] - sum[a[0]] == N) {
//                System.out.println(sum[a[1]] + " " + sum[a[0]]);
                result++;
            }
            return;
        }

        for (int i = start; i < idx; i++) {
            a[cnt] = i;
            comb(i + 1, cnt + 1);
        }

    }

    static boolean isPrime(int num){
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        int i = 3;
        while (i <= Math.sqrt(num)){
            if (num % i == 0) return false;
            i += 2;
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sum = new int[(N / 2 + 2)];

        for (int i = 2; i <= N; i++) {
            if (isPrime(i)){
                sum[idx] = sum[idx - 1] + i;
                idx++;
            }
        }
        comb(0, 0);
        System.out.println(result);
    }
}
