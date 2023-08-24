package homework.M08.a0802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_d2_1954_달팽이숫자 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d2_1954.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int T=1;T<=TC;T++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            int m = n;
            int i=0;
            int j=-1;
            int di = 0;
            int dj = 1;
            int cnt = 1;
            while (m!=0) {
                for (int d=0;d<m;d++) {
                    i += di;
                    j += dj;
                    arr[i][j] = cnt++;
                }
                if (di==0&&dj==1) {
                    di=1;dj=0;m--;
                }
                else if(di==1&&dj==0) {
                    di=0;dj=-1;
                }
                else if(di==0&&dj==-1) {
                    di=-1;dj=0;m--;
                }
                else if(di==-1&&dj==0) {
                    di=0;dj=1;
                }
            }
            sb.append("#").append(T).append("\n");
            for (int[] a:arr) {
                for (int d = 0;d<n;d++) {
                    sb.append(a[d]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
