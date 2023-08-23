package homework.M08.a0810;

import java.util.*;
import java.io.*;

public class Solution_4012_요리사 {

    static int N;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;
    public static void dfs(int n, int[] v, int c) {
        if (c== N/2) {
            int[] first = new int[N/2];
            int[] second = new int[N/2];
            int c1 = 0;
            int c2 = 0;
            int fsum = 0;
            int ssum = 0;
            for (int i=0;i<N;i++) {
                if (v[i] == 1)
                    first[c1++] = i;
                else
                    second[c2++] = i;
            }
            for (int i=0;i<N/2;i++) {
                for (int j=i+1;j<N/2;j++) {
                    fsum += arr[first[i]][first[j]] + arr[first[j]][first[i]];
                    ssum += arr[second[i]][second[j]] + arr[second[j]][second[i]];
                }
            }
            min = Math.min(min, Math.abs(ssum-fsum));
        }
        else {
            for (int i=n+1;i<N;i++) {
                v[i] = 1;
                dfs(i, v, c+1);
                v[i] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0810/res/input_4012.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int TC=1;TC<=T;TC++) {
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            for (int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i=0;i<N/2;i++) {
                int[] v = new int[N];
                v[i] = 1;
                dfs(i, v, 1);
            }
            sb.append("#").append(TC).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}
