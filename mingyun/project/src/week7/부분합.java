package week7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week7/res/부분합.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int sum =0;
        int min = Integer.MAX_VALUE;

        while (0 <= left && right <= n) {
            if (sum >= m) {
                min = Math.min(min, right - left);
            }
            if (sum < m) sum += arr[right++];
            else sum -= arr[left++];
        }

        if (min== Integer.MAX_VALUE) {
            System.out.println(0);
        }
        else {
            System.out.println(min);
        }
    }
}