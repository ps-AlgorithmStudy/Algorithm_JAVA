package homework.M08.a0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 대충하는거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int d = 0; d < 2; d++) {
            for (int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] += Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int[] a:arr) {
            for (int b:a) {
                System.out.printf("%d ", b);
            }
            System.out.println();
        }
    }
}