package homework.M08.a0808;

import java.io.*;
import java.util.*;

public class Solution_d4_1868_파핑파핑지뢰찾기 {

    static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};
    static int n;

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static void dfs(int pi,int pj, int[][] map, boolean[][] v) {
        v[pi][pj] = true;
        if (map[pi][pj]==0) {
            for (int d=0;d<8;d++) {
                int mi = pi + di[d];
                int mj = pj + dj[d];
                if (inRange(mi,mj) && !v[mi][mj]) dfs(mi,mj,map,v);
            }
        }
    }

    public static void mapping(int[][] map, int n) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                int cnt = 0;
                if (map[i][j]!=-1) {
                    for (int d=0;d<8;d++) {
                        int mi = i + di[d];
                        int mj = j + dj[d];
                        if (inRange(mi,mj)&&map[mi][mj]==-1) cnt++;
                    }
                    map[i][j] = cnt;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_d4_1868.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int T=1;T<=TC;T++) {
            n = Integer.parseInt(br.readLine());
            boolean[][] v = new boolean[n][n];
            int[][] map = new int[n][n];
            for (int i=0;i<n;i++) {
                char[] tempArr = br.readLine().toCharArray();
                for (int j=0;j<n;j++) {
                    if (tempArr[j]=='.') map[i][j] = 0;
                    if (tempArr[j]=='*') {
                        map[i][j] = -1;
                        v[i][j] = true;
                    }
                }
            }
            mapping(map,n);

            int result = 0;
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (map[i][j]==0 && !v[i][j]) {
                        result++;
                        dfs(i,j,map,v);
                    }
                }
            }

            for (int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    if (!v[i][j]) result++;
                }
            }
            sb.append("#").append(T).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
