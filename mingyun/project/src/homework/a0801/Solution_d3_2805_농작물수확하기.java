package homework.a0801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_d3_2805_농작물수확하기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_2805.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int t=1;t<=TC;t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            for (int i=0;i<n;i++) {
                char[] tempArr = br.readLine().toCharArray();
                for (int j=0;j<n;j++) {
                    arr[i][j] = tempArr[j] - '0';
                }
            }

            int mid = n/2+1;
            int sum = 0;
            for (int i=0;i<mid-1;i++) {
                for (int j=mid-i;j<=mid+i;j++) {
                    sum += arr[i][j-1];
                    sum += arr[n-i-1][j-1];
                }
            }
            for (int i=0;i<n;i++) sum += arr[mid-1][i];
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
