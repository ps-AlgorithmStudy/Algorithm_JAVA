package week9.벽부수고이동하기2;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[] di = { -1, 0, 1,  0};
    static int[] dj = {  0, 1, 0 ,-1};
    static int[][] map;

//    static void printmap(){
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + "  ");
//            }System.out.println();
//        }
//        System.out.println();
//    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 방문 좌표값
        boolean[][][] visited = new boolean[N][M][K+1];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }

        // BFS Start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0,0,K,1});

        // 도착 실패시 출력값을 초기값으로
        int ans  = -1;

        while(!q.isEmpty()){
            int[] ij = q.poll();
            int ii  = ij[0];
            int jj  = ij[1];
            int brk = ij[2]; // 벽 부순 횟수
            int acc = ij[3]; // 이동거리 누적

            // 도착 시
            if(ii == N-1 && jj == M-1){
                ans = acc;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int ni = ii + di[d];
                int nj = jj + dj[d];

                if(ni < 0 || nj < 0 || ni > N-1 || nj > M-1 || visited[ni][nj][brk]) continue;

                if(map[ni][nj] == 0){
                    // 벽이 없을 때
                    visited[ni][nj][brk] = true;
                    q.offer(new int[] {ni, nj, brk, acc+1});
                }else{
                    // 부술 수 있으면 언제 부수든 상관x
                    // 벽이 있을 떄 && 벽을 부술 기회가 있을 때
                    if(brk > 0){
                        visited[ni][nj][brk] = true;
                        q.offer(new int[] {ni,nj,brk-1,acc+1});
                    }
                }
            }
        }
        System.out.println(ans);
    }
}