package greedy;

import java.io.*;
import java.util.*;

public class week5_bj_2212 {
    static int N, K;
    static int[] arr;
    static Integer[] dist;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N];
        dist = new Integer[N - 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 1; i < N; i++) {
            dist[i - 1] = arr[i] - arr[i - 1];
        }
        Arrays.sort(dist, Comparator.reverseOrder());

        // partition 의 기준점이 되는 dist index
        int kFirstIdx = 0;
        int kLastIdx = (N>K)? K - 2: N-2;
        // 센서의 범위 체크
        int start = 0, end = 0;
        int result = 0;
        for (int i = 1; i < N; i++) {
            if (kLastIdx < 0) break; //  K = 1 일 경우 예외처리
            int distance = arr[i] - arr[i - 1];
            if (dist[kLastIdx] <= distance && distance <= dist[kFirstIdx]){
                result += arr[end] - arr[start];
                if (dist[kLastIdx] == distance) kLastIdx--; // 경계에 있는 dist 의 값이 여러개일 경우를 처리하기 위해서
                else if (dist[kFirstIdx] == distance) kFirstIdx++;
                start = end = i;
            }
            else{
                end++;
            }

        }
        result += arr[N-1] - arr[start];

        System.out.println(result);
    }
}
