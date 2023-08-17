package week4.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2638_치즈 {
    static int n;
    static int m;

    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    static boolean check(int[][] map) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }

    static boolean work(int[][] map) {
        boolean[][] v = new boolean[n][m];
        dfs(1,1, map, v);
        for (int i=1;i<n-1;i++) {
            for (int j=1;j<m-1;j++) {
                int cnt = 0;
                for (int d=0;d<4;d++) {
                    int mi = i+di[d];
                    int mj = j+dj[d];
                    if (v[mi][mj]) cnt++;
                }
                if (cnt>=2) {
                    map[i][j]=0;
                }
            }
        }
        return check(map);
    }

    static boolean inRange(int i, int j) {
        return 0<=i && i<n && 0<=j && j<m;
    }
    static void dfs(int i, int j, int[][] map, boolean[][] v) {
        for (int d=0;d<4;d++) {
            int mi = i+di[d];
            int mj = j+dj[d];
            if (inRange(mi,mj)&&!v[mi][mj]&&map[mi][mj]==0) {
                v[mi][mj] = true;
                dfs(mi,mj,map,v);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_2638.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        if (!check(map)) {
            do {
                res++;
            } while (!work(map));
        }

        System.out.print(res);
    }
}
