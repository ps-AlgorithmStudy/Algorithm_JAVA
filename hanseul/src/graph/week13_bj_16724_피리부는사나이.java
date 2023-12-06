package graph;
import java.util.*;
import java.io.*;

public class week13_bj_16724_피리부는사나이 {

    static int n, m;
    static char[][] arr;
    static boolean[][] v;
    static int dx[] = {0, 1, 0, -1}; // R D L U
    static int dy[] = {1, 0, -1, 0};

    static int calWay(char ch){ // 방향 char 와 방향 idx 를 mapping
        if (ch == 'R') return 0;
        else if (ch == 'D') return 1;
        else if (ch == 'L') return 2;
        else return 3;
    }

    static void dfs(int x, int y){
        v[x][y] = true;

        // 지금 위치 기준으로 다음 방향 탐색
        int way = calWay(arr[x][y]);
        int tx = dx[way] + x;
        int ty = dy[way] + y;
        if (tx >= 0 && tx < n && ty >= 0 && ty < m && !v[tx][ty])
            dfs(tx, ty);

        // 지금 위치 기준으로 4방탐색하여 이전 위치를 탐색함.
        for (int j = 0; j < 4; j++) {
            tx = dx[j] + x;
            ty = dy[j] + y;
            if (tx < 0 || tx >= n || ty < 0 || ty >= m) continue;
            if (v[tx][ty]) continue;
            way = calWay(arr[tx][ty]);
            if (way != j && way % 2 == j % 2) // tx, ty
                dfs(tx, ty);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j]){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
