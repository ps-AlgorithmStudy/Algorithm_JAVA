package week5.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_4963_섬의갯수 {
    static int[] di = {0,1,0,-1,1,-1,1,-1};
    static int[] dj = {1,0,-1,0,1,1,-1,-1};
    static int n, m;
    static boolean inRange(int i, int j) {
        return 0<=i && i<m && 0<=j && j<n;
    }

    static void dfs(int i, int j, boolean[][] map, boolean[][] v) {
        v[i][j] = true;
        for (int d=0;d<8;d++) {
            int mi = i+di[d], mj = j+dj[d];
            if (inRange(mi,mj) && map[mi][mj] && !v[mi][mj]) {
                dfs(mi,mj,map,v);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_4963.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
            if (n==0 && m ==0) break;

            boolean[][] map = new boolean[m][n];
            boolean[][] v = new boolean[m][n];

            for (int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<n;j++)
                    map[i][j] = Integer.parseInt(st.nextToken()) != 0;
            }

            int cnt = 0;
            for (int i=0;i<m;i++) {
                for (int j=0;j<n;j++) {
                    if (map[i][j] && !v[i][j]) {
                        cnt++;
                        dfs(i,j,map,v);
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}