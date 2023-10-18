package week10;

import java.io.*;
import java.util.*;
public class Main_구슬탈출_13460{
    static int     N, M, ans;
    static int[]   O;

    // di[d], dj[d]
// 0: 위로  1: 오른쪽   2: 아래로  3: 왼쪽으로
    static int[]   di = {-1, 0, 1, 0};
    static int[]   dj = { 0, 1, 0,-1};
    static char[][] map;

    static boolean getPriority(int[] R, int[] B, int d) {
// 빨간공 우선일 때 true
// 파란공 우선일 때 false
        if(d == 0) return R[0] <= B[0];
        if(d == 1) return R[1] <= B[1];
        if(d == 2) return R[0] > B[0];
        else return R[1] < B[1];
    }

    static void solve(int depth, int[] R, int [] B) {

        if(R[0] == O[0] && B[1] == O[1]) {
            System.out.println("failed");
            return;
        }
// 종료 1 : 열번 이내로 탈출에 성공할 경우
        if(B[0] == O[0] && B[1] == O[1]) {
            ans = depth;
            System.out.println(ans);
            return;
        }
// 종료 2 : 열번을 굴려도 실패할 경우
        if(depth >= 10) {
            ans = -1;
            System.out.println(-1);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int i1, j1, i2, j2;
            boolean isRedFirst= getPriority(R,B,d);
            if(isRedFirst) {
                i1 = R[0];
                j1 = R[1];
                i2 = B[0];
                j2 = B[1];
            }else {
                i1 = B[0];
                j1 = B[1];
                i2 = R[0];
                j2 = R[1];
            }

// 1. 먼저 굴릴 공
            int ni1 = i1 + di[d];
            int nj1 = j1 + dj[d];
            System.out.println(ni1+" , "+nj1+" , "+map[ni1][nj1]);
            while(map[ni1][nj1] == '.') {
                if(map[ni1][nj1]== 'O'||map[ni1][nj1]== '#') break;
                ni1 = ni1 + di[d];
                nj1 = nj1 + dj[d];
            }

            char tmp;

            tmp= map[i1][j1];
            map[i1][j1] = '.';
            map[ni1][nj1] = tmp;

// 2. 다음으로 굴릴 공
            int ni2 = i2 + di[d];
            int nj2 = j2 + dj[d];
            while(map[ni2][nj2] == '.') {
                ni2 = ni2 + di[d];
                nj2 = nj2 + dj[d];
                if(map[ni2][nj2]== 'O') break;
            }

            tmp = map[i2][j2];
            map[i2][j2] = '.';
            map[ni2][nj2] = tmp;

            if(isRedFirst) {
                solve(depth+1, new int[] {ni1,nj1}, new int[] {ni2,nj2});
            }else {
                solve(depth+1, new int[] {ni2,nj2}, new int[] {ni1,nj1});
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] R = {};
        int[] B = {};
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char current = line.charAt(j);
                map[i][j] = current;
                if(current == 'R') R = new int[] {i,j}; // 빨간볼 (성공 볼)
                if(current == 'B') B = new int[] {i,j}; // 파란볼 (파울 볼)
                if(current == 'O') O = new int[] {i,j}; // 구멍
            }
        }
        solve(0, R, B);
    }
}

