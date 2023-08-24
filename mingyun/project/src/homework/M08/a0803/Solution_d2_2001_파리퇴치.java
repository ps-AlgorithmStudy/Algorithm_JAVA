package homework.M08.a0803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d2_2001_파리퇴치 {

    public static int cal(int si, int sj, int m, int[][] arr){
        int sum = 0;
        for (int i=si;i<si+m;i++) {
            for (int j=sj;j<sj+m;j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/week3/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int T=1;T<=TC;T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];

            for (int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<n;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            for (int i=0;i<=n-m;i++) {
                for (int j=0;j<=n-m;j++) {
                    max = Math.max(max, cal(i,j,m,arr));
                }
            }
            sb.append("#").append(T).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
