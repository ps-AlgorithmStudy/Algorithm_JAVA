package week3;

import java.io.*;
import java.util.*;

public class Main_7576 {
    static int M,N,H;
    // 6방향
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    static int[][] tmt;
    static Queue<int[]> q = new ArrayDeque<>();
    static int max = 0;
    static int day = 0;
    static boolean check(int[][] tomato) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(tomato[i][j],max);
                if(tomato[i][j]==0) {
                    return true;
                }
            }
        }
        return false;
    }

    static void BFS() {
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0<=nx&&nx<M && 0<=ny&&ny<N  ) {
                    if(tmt[nx][ny] == 0) {
                        q.offer(new int[] {nx,ny});
                        tmt[nx][ny] = tmt[x][y]+1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tmt = new int[M][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                tmt[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        if(!check(tmt)) {
            System.out.println(0);
            return;
        }
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(tmt[x][y] == 1) {
                    q.offer(new int[]{x,y});
                }
            }
        }

        BFS();

        System.out.println(check(tmt) ? -1: max-1);
    }
}
