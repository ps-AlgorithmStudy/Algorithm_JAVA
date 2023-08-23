package week5.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_10026_적록색약 {
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};
    static int n;
    static boolean inRange(int i, int j) {
        return 0<=i && i<n && 0<=j && j<n;
    }

    static void dfs(int i, int j, char[] p,char[][] map, boolean[][] v) {
        v[i][j] = true;
        for (int d=0;d<4;d++) {
            int mi = i+di[d], mj = j+dj[d];
            for (char c:p) {
                if (inRange(mi,mj) && map[mi][mj]==c && !v[mi][mj]) {
                    dfs(mi,mj,p,map,v);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];


        for (int i=0;i<n;i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt1 = 0;
        int cnt2 = 0;
        boolean[][] v1 = new boolean[n][n];
        boolean[][] v2 = new boolean[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (!v1[i][j]) {
                    cnt1++;
                    dfs(i,j,new char[]{map[i][j]},map,v1);
                }
                if (!v2[i][j]) {
                    cnt2++;
                    if ("RG".contains(String.valueOf(map[i][j]))) {
                        dfs(i,j,new char[]{'R','G'},map,v2);
                    }
                    else {
                        dfs(i,j,new char[]{'B'},map,v2);
                    }
                }
            }
        }
        System.out.println(cnt1 + " " + cnt2);
    }
}