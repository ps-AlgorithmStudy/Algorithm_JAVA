package SSA;

import java.util.*;
import java.io.*;

public class week6_bj_15683_감시 {

    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static int[][][] camDirect = {
        {{0},{1},{2},{3}},
        {{0, 2},{1, 3}},
        {{0,1},{1,2},{2,3},{3,0}},
        {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
        {{0,1,2,3}}
    };

    static int blank = 0;
    static ArrayList<int[]> camList = new ArrayList<>();
    static int max;

    static void backTracking(int camIdx, int cctv){
        if (camIdx == camList.size()){
            max = (max < cctv) ? cctv : max;
            return ;
        }

        int[] camInfo = camList.get(camIdx);
        for (int i = 0; i < camDirect[camInfo[0]].length; i++) {
            int length = 0;
            for (int j = 0; j < camDirect[camInfo[0]][i].length ; j++) length += monitoring(camIdx, camDirect[camInfo[0]][i][j]);
            backTracking(camIdx + 1, cctv + length);
            for (int j = 0; j < camDirect[camInfo[0]][i].length ; j++) rollBack(camIdx, camDirect[camInfo[0]][i][j]);
        }

    }

    static int monitoring(int camIdx, int direct){
        int[] camInfo = camList.get(camIdx);
        int x = camInfo[1] + dx[direct];
        int y = camInfo[2] + dy[direct];
        int length = 0;

        while (x >= 0 && x < N && y >= 0 && y < M){
            if (arr[x][y] == 6) break;
            if (arr[x][y] == 0) {
                arr[x][y] = camIdx + 10;
                length++;
            }
            x += dx[direct];
            y += dy[direct];
        }
        return length;
    }

    static void rollBack(int camIdx, int direct){
        int[] camInfo = camList.get(camIdx);
        int x = camInfo[1] + dx[direct];
        int y = camInfo[2] + dy[direct];

        while (x >= 0 && x < N && y >= 0 && y < M){
            if (arr[x][y] == 6) break;
            if (arr[x][y] == camIdx + 10) arr[x][y] = 0;
            x += dx[direct];
            y += dy[direct];
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0 && arr[i][j] != 6){
                    camList.add(new int[]{arr[i][j]-1, i, j}); // camNum, x, y;
                }
                if (arr[i][j] == 0) blank++;
            }
        }
        backTracking(0,0);
        System.out.println(blank - max);

    }

}
