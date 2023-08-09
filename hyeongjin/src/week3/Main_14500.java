package week3;

import java.io.*;
import java.util.*;

public class Main_14500 {
    static int M,N;
    static int ans;
    static     int[][] tet;
    static boolean[][] v;
    static int[] di = {0,  1,  0, -1},
            dj = {1,  0, -1,  0};

    static void genBlock(int i, int j,int cnt,int sum) {
        if(cnt==4) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if(0<=ni && ni<M && 0<=nj && nj<N ) {

                if(!v[ni][nj]) {

                    if(cnt==2) { // ㅗ블록
                        v[ni][nj] = true;
                        genBlock(i,j,cnt+1,sum+tet[ni][nj]);
                        v[ni][nj] = false;
                    }
                    v[ni][nj] = true;
                    genBlock(ni,nj,cnt+1,sum+tet[ni][nj]);
                    v[ni][nj] = false;
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

        tet = new int[M][N];
        v = new boolean[M][N];
        for (int x = 0; x < M; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++){
                tet[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++){
                v[x][y] = true;
                genBlock(x,y,1,tet[x][y]);
                v[x][y] = false;
            }
        }
        System.out.println(ans);

    }
}