package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 키순서 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/키순서.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());

        int maxValue = 9;
        int[][] arr = new int[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                arr[i][j] = maxValue;
                if (i==j) arr[i][j] = 0;
            }
        }

        for (int d=0;d<m;d++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            arr[i][j] = 1;
        }

        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int result = 0;

        for (int i=0;i<n;i++) {
            int cnt = 0;
            for (int j=0;j<n;j++) {
                if (arr[i][j] != maxValue && arr[i][j] != 0) cnt++;
                if (arr[j][i] != maxValue && arr[j][i] != 0) cnt++;
            }
            if (cnt == n-1) result++;
        }

        System.out.println(result);
    }
}
