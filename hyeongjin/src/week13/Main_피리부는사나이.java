package week13;
import java.io.*;
import java.util.*;

public class Main_피리부는사나이 {
    static int N, M, cnt, result;
    static int[] di = {-1, 0 ,1,  0};
    static int[] dj = { 0, 1, 0, -1};
    static char[][] map;
    static int[][] v;
    // 같은 탐색 내에서 방문처리 된지 확인하기 위해

    // 방향 정하기 
    static int direction(int i, int j){
        char current = map[i][j];
        switch (current) {
            case 'U' : return 0;
            case 'R' : return 1;
            case 'D' : return 2;
            case 'L' : return 3;
            default  : return 4;
        }
    }
    static void DFS(int i, int j){
        // 도달한 지점이 이미 방문한 곳일 경우 
        if(v[i][j] > 0){
            // 1. 현재 탐색에서 방문처리 된 곳일 경우 => 새로 생겨난 사이클
            if(v[i][j] == cnt){
                result++;
            }
            // 2. 이전 탐색에서 방문처리 된 곳일 경우 => 기존 사이클에 추가
            return;
        }
        v[i][j] = cnt;

        int d = direction(i,j);
        int ni = i + di[d];
        int nj = j + dj[d];
        DFS(ni,nj);

    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map =  new char[N][M];
        v = new int[N][M];

        cnt = 0;
        String line;

        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(v[i][j] == 0){
                    cnt++;
                    DFS(i,j);
                }
            }
        }

        System.out.println(result);
    }
}