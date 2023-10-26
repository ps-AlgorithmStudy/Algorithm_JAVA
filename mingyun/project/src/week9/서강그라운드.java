package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 서강그라운드 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/서강그라운드.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        int maxValue = Integer.MAX_VALUE / 2 - 1000;

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                arr[i][j] = maxValue;
                if (i==j) arr[i][j] = 0;
            }
        }

        int[] value = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int d=0;d<r;d++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            int k = Integer.parseInt(st.nextToken());
            arr[i][j] = k; arr[j][i] = k;
        }

        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    arr[i][j] = Integer.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int result = 0;
        for (int[] a:arr) {
            int sum = 0;
            for (int i=0;i<n;i++) {
                if (a[i] <= m) {
                    sum += value[i];
                }
            }
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }
}
