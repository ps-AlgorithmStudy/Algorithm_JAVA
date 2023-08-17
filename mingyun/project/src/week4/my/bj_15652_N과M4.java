package project.src.week4.my;

import java.io.*;
import java.util.*;

public class bj_15652_N과M4 {

    static int n;
    static int m;
    public static void dfs(int p,int cnt, int[] arr) {
        if (cnt == m) {
            for (int a:arr) System.out.printf("%d ", a);
            System.out.println();
        }
        else {
            for (int i=p;i<=n;i++) {
                arr[cnt] = i;
                dfs(i,cnt+1, arr);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_15652.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dfs(1, 0, new int[m]);
    }

}
