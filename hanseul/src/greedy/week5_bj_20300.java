package greedy;

import java.util.*;
import java.io.*;

public class week5_bj_20300 {
    static int N;
    static long[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        long result = 0;
        Arrays.sort(arr);
        if (N % 2 == 1) {
            result = arr[N - 1];
            N--;
        }
        for (int i = 0; i < N / 2; i++) {
            result = (result < arr[i] + arr[N - i - 1]) ? arr[i] + arr[N - 1- i] : result;
        }
        System.out.println(result);
    }
}
