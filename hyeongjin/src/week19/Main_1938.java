package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_1938 {
    static int N;

    static int[] di = {-1,0,1, 0};
    static int[] dj = { 0,1,0,-1};
    static char[][] map;
    static boolean[][][] v;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int Bcnt = 0;
        int Ecnt = 0;
        boolean Bshape;
        boolean Eshape;
        int[][] BBB = new int[3][2];
        int[][] EEE = new int[3][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char cur = map[i][j];
                if(cur == 'B'){
                    BBB[Bcnt++] = new int[]{i,j};
                }
                if(cur == 'E'){
                    EEE[Ecnt++] = new int[]{i,j};
                }
            }
        }

        Bshape = (BBB[0][0] == BBB[0][1]);
        Eshape = (EEE[0][0] == EEE[0][1]);

        Log Blog = new Log(BBB[1][0], BBB[1][1], 0,Bshape);
        Log Elog = new Log(EEE[1][0], BBB[1][1], 0,Eshape);

        System.out.println(BFS(Blog, Elog));
    }
    static int BFS(Log Blog, Log Elog) {
        System.out.println(Blog.i+","+ Blog.j+","+ Blog.shape);
        System.out.println(Elog.i+","+ Elog.j+","+ Elog.shape);
        ArrayDeque<Log> q = new ArrayDeque<>();
        v = new boolean[2][N][N];

        q.offer(Blog);

        while (!q.isEmpty()) {
            Log cur = q.poll();
            int i = cur.i;
            int j = cur.j;
            int dist = cur.dist;
            boolean s = cur.shape;

            // 도착시 종료
            if (i == Elog.i && j == Elog.j && s == Elog.shape) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                // 움직일 수 있는지 확인
                if (s) { // 1. 가로
                    if (ni < 0 || ni >= N || nj < 1 || nj >= N - 1) continue;
                } else { // 2. 세로
                    if (nj < 0 || nj >= N || ni < 1 || ni >= N - 1) continue;
                }

                // 방문 확인
                if (v[s ? 0 : 1][ni][nj]) continue;
                v[s ? 0:1][ni][nj] = true;

                // 방향을 바꾸지 않고 다음 탐색
                q.offer(new Log(ni,nj,dist+1,s));

                // 방향을 바꾸고 다음 탐색
                if(isRotatable(i,j)){
                    q.offer(new Log(i,j, dist+1,!s));
                }

            }
        }
        return 0;
    }
    static boolean isRotatable(int i, int j){
        for (int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                if(k < 0 || k >= N || l < 0 || l >= N) {
                    return false;
                }
                if(map[k][l] =='1'){
                    return false;
                }
            }
        }
        return true;
    }
    static class Log{
        int i;
        int j;
        int dist;
        boolean shape; // 0: 가로 , 1:세로

        public Log(int i, int j, int dist, boolean shape) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.shape = shape;
        }
    }
}
