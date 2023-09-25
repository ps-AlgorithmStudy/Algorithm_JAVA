package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
public class PuyoPuyo {

    static class Pointer {
        int i, j;
        Pointer(int i, int j) {
            this.i = i; this.j = j;
        }
    }
    static boolean inRange(int i, int j) {
        return 0<=i && i< 12 && 0<=j && j<6;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week8/PuyoPuyo.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean boom = true;
        int result = 0;

        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};

        while (boom) {
//            for (char[] m : map) {
//                System.out.println(Arrays.toString(m));
//            }
//            System.out.println();
            boom = false;
            ArrayList<Pointer> eraseAll = new ArrayList<>();
            for (int i=0;i<12;i++) {
                for (int j=0;j<6;j++) {
                    if (map[i][j] != '.') {
                        ArrayDeque<Pointer> deque = new ArrayDeque<>();
                        ArrayList<Pointer> erase = new ArrayList<>();
                        boolean[][] v = new boolean[12][12];
                        deque.addLast(new Pointer(i,j));

                        char target = map[i][j];
                        while (!deque.isEmpty()) {
                            Pointer now = deque.removeFirst();
                            for (int d=0;d<4;d++) {
                                int mi = now.i + di[d];
                                int mj = now.j + dj[d];
                                if (inRange(mi,mj) && !v[mi][mj] && target == map[mi][mj]) {
                                    Pointer next = new Pointer(mi,mj);
                                    v[mi][mj] = true;
                                    erase.add(next);
                                    deque.addLast(next);
                                }
                            }
                        }

                        if (erase.size() >= 4) {
                            for (Pointer pointer:erase) {
                                map[pointer.i][pointer.j] = '.';
                            }
                            eraseAll.addAll(erase);
                        }
                    }
                }
            }
            if (!eraseAll.isEmpty()) {
                boom = true;
                eraseAll.sort(((o1, o2) -> Integer.compare(o1.i, o2.i)));
                for (Pointer pointer:eraseAll) {
                    int mj = pointer.j;
                    int mi = pointer.i;
                    while (mi > 0) {
                        map[mi][mj] = map[mi-1][mj];
                        map[mi-1][mj] = '.';
                        mi--;
                    }
                }
                result++;
            }
        }
        System.out.println(result);
    }
}
