package homework.M08.a0830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 외판원순회2 {
    static int[][] map;
    static int n;
    static long res = Long.MAX_VALUE;

    public static void dfs(int p, long sum, int[] v, int cnt, int start) {
        if (cnt==n) {
            if (map[p][start]!=0) {
                res = Math.min(res, sum + map[p][start]);
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if (v[i]==0 && map[p][i] != 0) {
                    v[i] = 1;
                    dfs(i, sum + map[p][i], v, cnt+1, start);
                    v[i] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0830/res/외판원순회.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0;i<n;i++) {
            int[] v = new int[n];
            v[i] = 1;
            dfs(i,0, v, 1, i);
        }
        System.out.println(res);
    }
}