package homework.M08.a0807;

import java.util.*;
import java.io.*;
public class Solution_d4_7733_치즈도둑 {
    static boolean[][] v;
    static int[][] mask = {
            {1,0,-1,0},
            {0,1,0,-1}
    };

    static boolean inRange(int x, int y, int n) {
        return (x>=0 && x<n && y>=0 && y<n);
    }
    public static void dfs(int x, int y, int day, int n, int[][] map) {
        v[x][y] = true;
        for (int i=0;i<4;i++) {
            int dx = x + mask[0][i];
            int dy = y + mask[1][i];
            if (inRange(dx,dy,n)) {
                if (!v[dx][dy] && map[dx][dy]>day) {
                    dfs(dx,dy,day,n, map);
                }
            }
        }
    }
    static class Work {
        int x;
        int y;
        Work(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y, int day, int n, int[][] map) {
        Deque<Work> deque = new ArrayDeque<>();
        deque.offerLast(new Work(x,y));
        while (!deque.isEmpty()) {
            Work work = deque.removeFirst();
            v[work.x][work.y] = true;
            for (int i=0;i<4;i++) {
                int dx = work.x + mask[0][i];
                int dy = work.y + mask[1][i];
                if (!inRange(dx,dy,n)) continue;
                if (!v[dx][dy]&&map[dx][dy]>day) {
                    v[dx][dy] = true;
                    deque.offerLast(new Work(dx,dy));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d4_7733.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc=1;tc<=T;tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            int result = 1;
            StringBuilder sb = new StringBuilder();

            for (int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j=0;j<n;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int day=1;day<=100;day++) {
                int cnt = 0;
                v = new boolean[n][n];
                for (int i=0;i<n;i++) {
                    for (int j=0;j<n;j++) {
                        if (map[i][j] > day && !v[i][j]) {
                            cnt++;
                            bfs(i,j,day, n, map);
                        }
                    }
                }
                result = Math.max(result, cnt);
            }
            sb.append("#").append(tc).append(" ").append(result);
            System.out.println(sb);
        }
    }
}