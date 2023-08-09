package week3;

import java.io.*;
import java.util.*;

public class Main_7569 {
    static int M,N,H;
    // 6방향
    static int[] dx = {-1, 0, 1,  0, 0, 0};
    static int[] dy = { 0, 1, 0, -1, 0, 0};
    static int[] dz = { 0, 0, 0,  0,-1, 1};
    static int[][][] tmt;
    static Queue<int[]> q = new ArrayDeque<>();
    static int max = 0;
    static int day = 0;
    static boolean check(int[][][] tomato) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int z = 0; z < H; z++) {
                    max = Math.max(tomato[i][j][z],max);
                    if(tomato[i][j][z]==0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void BFS() {
        while(!q.isEmpty()) {
            int[] xyz = q.poll();
            int x = xyz[0];
            int y = xyz[1];
            int z = xyz[2];
            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];
                if(0<=nx&&nx<M && 0<=ny&&ny<N && 0<=nz&&nz<H ) {
                    if(tmt[nx][ny][nz] == 0) {
                        q.offer(new int[] {nx,ny,nz});
                        tmt[nx][ny][nz] = tmt[x][y][z]+1;
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
        H = Integer.parseInt(st.nextToken());

        tmt = new int[M][N][H];
        boolean[][][] v = new boolean[M][N][H];
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; x++) {
                    tmt[x][y][z] = Integer.parseInt(st.nextToken());
                }
            }
        }
        if(!check(tmt)) {
            System.out.println(0);
            return;
        }
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if(tmt[x][y][z] == 1) {
                        q.offer(new int[]{x,y,z});
                    }
                }
            }
        }
        BFS();

        System.out.println(check(tmt) ? -1: max-1);
    }
}

