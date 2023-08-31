package SSA;

import java.util.*;
import java.io.*;

public class week6_bj_19236_청소년상어 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;

    static void dfs(int[][] map, ArrayList<int[]> fishSet, int dinner){
        int tx = fishSet.get(17)[1];
        int ty = fishSet.get(17)[2];
        int d = fishSet.get(17)[3]; // shark direction

        moveFish(map, fishSet);

        for (int i = 0; i < 3; i++) {
            tx += dx[d];
            ty += dy[d];
            if (tx < 0 || tx >= 4 || ty < 0 || ty >= 4) break;
            if (map[tx][ty] == 0) continue;

            int[][] mapCpy = new int[4][];
            ArrayList<int[]> fishSetCpy = new ArrayList<>();

            for (int j = 0; j < 4; j++) mapCpy[j] = map[j].clone(); // map clone
            for (int j = 0; j < fishSet.size(); j++) fishSetCpy.add(fishSet.get(j).clone()); // fishInfo clone

            int fishIdx = mapCpy[tx][ty];
            int[] shark = fishSetCpy.get(17);
            mapCpy[tx][ty] = 17; // 상어 위치 update
            mapCpy[shark[1]][shark[2]] = 0; // 빈자리 0 update
            // shark info update
            shark[1] = tx;
            shark[2] = ty;
            shark[3] = fishSetCpy.get(fishIdx)[3];

            dfs(mapCpy, fishSetCpy, dinner + fishIdx);
        }

        max = (max < dinner) ? dinner : max;
    }

    static void moveFish(int[][] map, ArrayList<int[]> fishSet){
        // 16마리의 fish 탐색
        for (int i = 1; i <= 16 ; i++) {
            int[] fish = fishSet.get(i);
            if (map[fish[1]][fish[2]] != i) continue; // 이미 먹은 물고기를 만났다면
            for (int j = 0; j < 8; j++) {
                int d = (j + fish[3]) % 8;
                int tx = fish[1] + dx[d];
                int ty = fish[2] + dy[d];
                if (tx < 0 || tx >= 4 || ty < 0 || ty >= 4) continue;
                if (map[tx][ty] == 17) continue;

                int fishSwapIdx = map[tx][ty];

                int tmp = map[tx][ty];
                map[tx][ty] = map[fish[1]][fish[2]];
                map[fish[1]][fish[2]] = tmp;

                if (fishSwapIdx != 0) {
                    int[] fishSwap = fishSet.get(fishSwapIdx);
                    fishSwap[1] = fish[1];
                    fishSwap[2] = fish[2];
                }
                fish[1] = tx;
                fish[2] = ty;
                fish[3] = d;
                break;
            }
        }
    }


    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("src/SSA/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> fishInfo = new ArrayList<>();
        int[][] arr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                fishInfo.add(new int[]{fishNum, i, j, d});
                arr[i][j] = fishNum;
            }
        }
        fishInfo.add(new int[]{0,0,0,0}); // dummy
        fishInfo.add(new int[]{17, 0,0,0}); // 상어위치 초기화
        fishInfo.sort((o1, o2)->Integer.compare(o1[0], o2[0]));

        int fishIdx = arr[0][0];
        arr[0][0] = 17;
        fishInfo.get(17)[3] = fishInfo.get(fishIdx)[3];
        dfs(arr, fishInfo,  fishIdx);

        System.out.println(max);
    }
}
