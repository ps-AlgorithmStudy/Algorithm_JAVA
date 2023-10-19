package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 벽돌깨기 {
    static int n, w, h;
    static int min;
    static int[] di = {0,-1,0,1};
    static int[] dj = {-1,0,1,0};

    static class Boom {
        int i, j, value;
        Boom(int a, int b, int c) {
            i = a; j = b; value = c;
        }
    }

    static boolean inRange(int i, int j) {
        return 0<= i && i < w && 0<= j && j < h;
    }

    static int[][] mapCopy(int[][] map) {
        int[][] newMap = new int[w][h];
        for (int i=0;i<w;i++) {
            for (int j=0;j<h;j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
    static void moveMap(int[][] map) {
        for (int i=0;i<w;i++) {
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
            for (int j=0;j<h;j++) {
                if (map[i][j] == 0) arrayDeque.addFirst(0);
                else arrayDeque.addLast(map[i][j]);
            }
            ArrayList<Integer> arrayList = new ArrayList<>(arrayDeque);
            for (int j=0;j<h;j++) {
                map[i][j] = arrayList.get(j);
            }
        }
    }
    static void dfs(int cnt, int[][] map) {
        if (cnt==n) {
            int c = 0;
            for (int i=0;i<h;i++) {
                for (int j=0;j<w;j++) {
                    if (map[j][i] != 0) c++;
                }
            }
            min = Math.min(c, min);
        }
        else {
            ArrayDeque<Boom> arrayDeque = new ArrayDeque<>();
            for (int i=0;i<w;i++) {
                int[][] newMap = mapCopy(map);
                for (int j=0;j<h;j++) {
                    if (newMap[i][j] != 0) {
                        arrayDeque.addLast(new Boom(i, j, newMap[i][j]));
                        newMap[i][j] = 0;
                        break;
                    }
                }
                while (!arrayDeque.isEmpty()) {
                    Boom now = arrayDeque.removeFirst();
                    for (int m=1; m<now.value;m++) {
                        for (int d=0;d<4;d++) {
                            int mi = now.i + di[d] * m;
                            int mj = now.j + dj[d] * m;
                            if (inRange(mi, mj) && newMap[mi][mj] != 0) {
                                arrayDeque.addLast(new Boom(mi, mj, newMap[mi][mj]));
                                newMap[mi][mj] = 0;
                            }
                        }
                    }
                }

                moveMap(newMap);
                dfs(cnt+1, newMap);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/벽돌깨기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); w = Integer.parseInt(st.nextToken()); h = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;

            int[][] map = new int[w][h];
            for (int i=0;i<h;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<w;j++) {
                    map[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0,map);
            System.out.println("#" + TC + " " + min);
        }
    }
}
