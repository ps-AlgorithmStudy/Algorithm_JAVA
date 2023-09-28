package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치즈 {
    static int n,m;
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    static boolean inRange(int i, int j) {
        return 0<= i && i< n && 0<=j && j<m;
    }

    static void dfs(boolean[][] map, boolean[][] visit, int i, int j) {
        visit[i][j] = true;
        for (int d=0;d<4;d++) {
            int mi=i + di[d]; int mj=j + dj[d];
            if (inRange(mi,mj) && !visit[mi][mj] && !map[mi][mj]) {
                visit[mi][mj] = true;
                dfs(map, visit, mi, mj);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week8/치즈.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][m];
        int cnt = 0;

        for (int i = 0; i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken())==1;
                if (map[i][j]) cnt ++;
            }
        }

        int resultLoop = 0;
        int resultCnt = 0;

        while (cnt > 0) {
            resultCnt = cnt;
            boolean[][] visit = new boolean[n][m];
            dfs(map, visit, 0, 0);
            for (int i=0;i<n;i++) {
                for (int j=0;j<m;j++) {
                    if (map[i][j]) {
                        for (int d=0;d<4;d++) {
                            int mi=i + di[d]; int mj=j + dj[d];
                            if (inRange(mi,mj) && visit[mi][mj]) {
                                cnt--;
                                map[i][j] = false;
                                break;
                            }
                        }
                    }
                }
            }
            resultLoop++;
        }

        System.out.println(resultLoop);
        System.out.println(resultCnt);
    }
}
