package homework.M08.a0818;

import java.io.*;
import java.util.*;

public class bj_1987_알파벳 {

    static int R, C;
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};
    static boolean inRange(int i, int j) {
        return 0 <= i && i< R && 0 <= j && j < C;
    }
    static int dfs(int i, int j, int cnt, char[][] map, boolean[] hash) {
        hash[map[i][j]-'A'] = true;
        int max = cnt;
        for (int d=0;d<4;d++) {
            int mi = i + di[d], mj = j + dj[d];
            if (inRange(mi, mj) && !hash[map[mi][mj]-'A']) {
                max = Math.max(max, dfs(mi,mj,cnt+1, map, hash));
            }
        }
        hash[map[i][j]-'A'] = false;
        return max;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0818/res/input_1987.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        for (int i=0;i<R;i++) map[i] = br.readLine().toCharArray();

        System.out.println(dfs(0,0, 1, map, new boolean[100]));
    }
}
