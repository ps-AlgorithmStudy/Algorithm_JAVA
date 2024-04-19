package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제풀이

1. 각 배열마다 합이 될 수 있는 경우의 수를 계산한다.
    ex ) a 배열에서 2를 만들 수 있는 경우의 수 계산
2. 이후 각 a , b 배열에서 나올 수 있는 합의 경우의 수를 순회하여 결과를 꼐산한다.
    ex ) T = 5
         a 배열에서 합이 3일 경우의 수 : 2
         b 배열에서 합이 2일 경우의 수 : 4
         => 2 * 4 = 8

 */
public class bj_2143_두배열의합 {
    static int T;
    static int n,m;
    static int[] a, b;
    static int[] case_a, case_b;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        case_a = new int[T + 1];
        case_b = new int[T + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            int sum=0;
            for(int j = i; j < n; j++) {
                sum += a[j];
                if (sum < T)
                    case_a[sum]++;
            }
        }
        for(int i = 0; i < m; i++) {
            int sum=0;
            for(int j = i; j < m; j++) {
                sum += b[j];
                if (sum < T)
                    case_b[sum]++;
            }
        }

        int result = 0;
        int l = 1, r = T - 1;
        while (r >= 0){
            result += (case_a[l] * case_b[r]);
            l++; r--;
        }
        System.out.println(result);
    }
}
