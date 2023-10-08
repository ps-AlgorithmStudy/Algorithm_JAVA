package graph;

import java.io.*;
import java.util.*;

public class week9_bj_14442_벽부수고이동하기2 {

    static int N, M, K;
    static char[][] arr;
    static int[] b;
    static ArrayList<Integer> wallList = new ArrayList<>();

    static void comb(int cnt, int start){
        // 경로 구하기

        // cnt 제한
        if (cnt == K) return ;
        // 경우의 수 구하기
        for (int i = start; i < wallList.size(); i++) {
            arr[wallList.get(i) / M][wallList.get(i) % M] = '0';
            comb(cnt + 1, i + 1);
            arr[wallList.get(i) / M][wallList.get(i) % M] = '1';
        }
    }

    static int calRoute(){
        int result = 0 ;
        ArrayDeque<int []> q = new ArrayDeque<>();

        q.offer(new int[]{0, 0, 0});


        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++, cnt++) {
                arr[i][j] = tmp.charAt(j);
                if (arr[i][j] == '1')
                    wallList.add(cnt);
            }
        }

    }
}

/*
0 1 2 3
4 5 6 7
8 9 10 11

 */