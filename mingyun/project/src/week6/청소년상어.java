package project.src.week6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 청소년상어 {
    static class Fish {
        int num, direct, i, j;
        char[] temp = {'↑', '↖', '←', '↙', '↓', '↘', '→', '↗'};
        Fish(int num, int direct, int i, int j) {
            this.num = num;
            this.direct = direct;
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return num+ "" + temp[direct-1];
        }
    }

    static int[] di = {0, -1, -1,  0,  1,  1,  1,  0, -1};
    static int[] dj = {0,  0, -1, -1, -1,  0,  1,  1,  1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < 4 && 0 <= j && j < 4;
    }

    static boolean inRange(int i, int j, Fish[][] map) {
        if (inRange(i,j)) {
            if (map[i][j] == null) return true;
            return map[i][j].num != -1;
        }
        return false;
    }

    static void moveAll(Fish[] fish, Fish[][] map) {
        for (int i=1;i<=16;i++) {
            if (fish[i]!= null) {
                move(fish[i], map);
            }
        }
    }

    static void move(Fish fish, Fish[][] map) {
        for (int c=0;c<8;c++) {
            int i = fish.i + di[fish.direct];
            int j = fish.j + dj[fish.direct];
            if (inRange(i,j, map)) {
                if (map[i][j] != null) {
                    map[i][j].i = fish.i;
                    map[i][j].j = fish.j;
                }
                map[fish.i][fish.j] = map[i][j];
                map[i][j] = fish;
                fish.i = i; fish.j = j;
                break;
            }
            fish.direct = fish.direct==8?1:fish.direct+1;
        }
    }

    static int result;
    static void sharkDfs(Fish shark, Fish[] fish, Fish[][] map, int sum) {
        Fish tempShark = new Fish(shark.num, shark.direct, shark.i, shark.j);

        for (int mv=0;mv<4;mv++) {
            tempShark.i += di[shark.direct];
            tempShark.j += dj[shark.direct];
            if (!inRange(tempShark.i, tempShark.j)) break;
            if (map[tempShark.i][tempShark.j]==null) continue;
            int tempSum = sum + map[tempShark.i][tempShark.j].num;

            Fish[][] tempMap = new Fish[4][4];
            Fish[] tempFish = new Fish[17];
            for (int i = 1; i <= 16; i++) {
                if (fish[i] != null) {
                    tempFish[i] = new Fish(fish[i].num, fish[i].direct, fish[i].i, fish[i].j);
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] != null) {
                        if (map[i][j].num != -1) {
                            tempMap[i][j] = tempFish[map[i][j].num];
                        }
                        else if (map[i][j].num==-1) {
                            tempMap[i][j] = tempShark;
                        }
                    }
                    else {
                        tempMap[i][j] = null;
                    }
                }
            }
            tempFish[tempMap[tempShark.i][tempShark.j].num] = null;
            tempMap[shark.i][shark.j] = null;
            tempShark.direct = tempMap[tempShark.i][tempShark.j].direct;
            tempMap[tempShark.i][tempShark.j] = tempShark;
            moveAll(tempFish, tempMap);
            sharkDfs(tempShark, tempFish, tempMap, tempSum);
        }

        result = Math.max(result, sum);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week6/res/청소년상어.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Fish[][] map = new Fish[4][4];
        Fish[] fish = new Fish[17];


        for (int i=0;i<4;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<4;j++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                map[i][j] = new Fish(num, direction, i, j);
                fish[num] = map[i][j];
            }
        }
        result = map[0][0].num;
        fish[map[0][0].num] = null;
        map[0][0] = new Fish(-1, map[0][0].direct, 0,0);
        Fish shark = map[0][0];
        moveAll(fish, map);
        sharkDfs(shark, fish, map, result);
        System.out.println(result);
    }
}