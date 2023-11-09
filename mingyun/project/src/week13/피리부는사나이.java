package week13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 피리부는사나이 {
    static HashMap<Character, int[]> move = new HashMap<>();
    static boolean[][] visit;
    static char[][] map;
    static int n, m;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    static void dfs(int i, int j) {
        visit[i][j] = true;
        int[] next = move.get(map[i][j]);
        int mi = i + next[0]; int mj = j + next[1];
        if (inRange(mi, mj)&&!visit[mi][mj]) dfs(mi, mj);
        for (int d=0;d<4;d++) {
            mi = i + di[d]; mj = j + dj[d];
            if (inRange(mi, mj)) {
                next = move.get(map[mi][mj]);
                int ti = mi + next[0]; int tj = mj + next[1];
                if (ti == i && tj == j && !visit[mi][mj]) dfs(mi, mj);
            }
        }
    }
    public static void main(String[] args) throws  Exception {
        move.put('D',new int[] {1,0}); move.put('U',new int[] {-1, 0});
        move.put('L',new int[] {0,-1}); move.put('R',new int[] {0, 1});

        System.setIn(new FileInputStream("mingyun/project/src/week13/피리부는사나이"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        map = new char[n][m]; visit = new boolean[n][m];
        for (int i=0;i<n;i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (!visit[i][j]) {
                    result++;
                    dfs(i,j);
                }
            }
        }

        System.out.println(result);
    }
}
