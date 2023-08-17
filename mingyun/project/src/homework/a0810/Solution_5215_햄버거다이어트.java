package project.src.homework.a0810;



import java.util.*;
import java.io.*;


public class Solution_5215_햄버거다이어트 {
    static int[] taste;
    static int[] cal;
    static int result;
    public static void dfs(int p, int tsum, int csum, int n, int max) {
        result = Math.max(result, tsum);
        for (int i=p+1;i<n;i++) {
            if (csum+cal[i] <= max) {
                dfs(i,tsum+taste[i], csum + cal[i], n, max);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            taste = new int[n];
            cal = new int[n];
            result = 0;
            for (int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                taste[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }
            for (int i=0;i<n;i++) {
                dfs(i,taste[i], cal[i], n,l);
            }
            sb.append("#").append(tc).append(" ").append(result);
            System.out.println(sb);
        }
    }

}

