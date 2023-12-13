package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class week16_bj_1525_퍼즐 {
    static int[][] arr = new int[3][3];
    static int[] curPt = new int[2];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1 ,0, 0};
    static Set<Puzzle> set = new HashSet<>();

    static class Puzzle{
        int[][] map;
        int x, y, cnt;

        Puzzle(int[][] arr, int i, int j, int ct){
            map = new int[3][3];
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    map[k][l] = arr[k][l];
                }
            }
            x = i; y = j; cnt = ct;
        }

        @Override
        public int hashCode() {
            return this.x + this.y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Puzzle) {
                Puzzle tmp = (Puzzle)obj;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tmp.map[i][j] != map[i][j]) return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

    static int bfs(){
        ArrayDeque<Puzzle> queue = new ArrayDeque<>();

        Puzzle puzzle = new Puzzle(arr, curPt[0], curPt[1], 0);
        queue.offer(puzzle);
        set.add(puzzle);
        while (!queue.isEmpty()){
            Puzzle cur = queue.poll();
//            for(int[] ar : cur.map){
//                System.out.println(Arrays.toString(ar));
//            }
//            System.out.println();

            if (isCorrect(cur.map)) return cur.cnt;
            for (int i = 0; i < 4; i++) {
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];

                if (tx < 0 || tx >= 3 || ty < 0 || ty >= 3) continue;
//                int[][] tmp = new int[3][3];
//                for (int j = 0; j < 3; j++) {
//                    for (int k = 0; k < 3; k++) {
//                        tmp[j][k] = cur.map[j][k];
//                    }
//                }

                int tmpNum = cur.map[tx][ty];
                cur.map[tx][ty] = cur.map[cur.x][cur.y];
                cur.map[cur.x][cur.y] = tmpNum;

                if (set.contains(new Puzzle(cur.map, tx, ty, 0))) {
//                    System.out.println("isDuplicate");
                    tmpNum = cur.map[tx][ty];
                    cur.map[tx][ty] = cur.map[cur.x][cur.y];
                    cur.map[cur.x][cur.y] = tmpNum;
                    continue;
                }
//                System.out.println(cur.map[tx][ty]);
                Puzzle pz = new Puzzle(cur.map, tx, ty, cur.cnt + 1);
                set.add(pz);
                queue.offer(pz);

                tmpNum = cur.map[tx][ty];
                cur.map[tx][ty] = cur.map[cur.x][cur.y];
                cur.map[cur.x][cur.y] = tmpNum;
            }
        }
        return -1;
    }
    static boolean isCorrect(int[][] correct){
        int cnt = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (correct[i][j] == cnt) cnt = (cnt + 1) % 9;
                else return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0){
                    curPt[0] = i; curPt[1] = j;
                }
            }
        }

        Puzzle pu = new Puzzle(arr, curPt[0], curPt[1], 3);
//        set.add(pu);
//        if (set.contains(new Puzzle(new int[][] {
//                {1, 0, 3},{4, 2, 5},{7, 8, 6}
//        },curPt[0], curPt[1], 0)))
//            System.out.println("arr");
//        else System.out.println("no");
        System.out.println(bfs());

    }
}
