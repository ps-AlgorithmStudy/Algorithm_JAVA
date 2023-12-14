package week16;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;

public class 퍼즐 {
    static class Puzzle {

        int dept;
        int[][] map;
        int[] zero;

        public Puzzle(int[][] map, int[] zero, int dept) {
            this.map = new int[3][3];
            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    this.map[i][j] = map[i][j];
                }
            }
            this.zero = new int[2];
            this.zero[0] = zero[0]; this.zero[1] = zero[1];
            this.dept = dept;
        }
    }

    static String toHashString(int[][] map) {
        StringBuilder result = new StringBuilder();
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                result.append(map[i][j]);
            }
        }
        return result.toString();
    }

    static boolean validate(int[][] map) {
        int last = 0;
        for (int i = 0; true; i++) {
            for (int j=0;j<3;j++) {
                if (i==2 && j==2) {
                    return true;
                }
                if (map[i][j] <= last)
                    return false;
                else
                    last = map[i][j];
            }
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i<3 && 0 <= j && j < 3;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week16/퍼즐"));
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[3][3];
        HashSet<String> visit = new HashSet<>();
        int[] zero = new int[2];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) zero = new int[] {i, j};
            }
        }
        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};

        visit.add(toHashString(map));
        Queue<Puzzle> queue = new ArrayDeque<>();
        queue.add(new Puzzle(map, zero, 0));
        while (!queue.isEmpty()) {
            Puzzle now = queue.poll();
            if (validate(now.map)) {
                System.out.println(now.dept);
                System.exit(0);
            }
            for (int i=0;i<4;i++) {
                zero = now.zero;
                int mi = zero[0] + di[i];
                int mj = zero[1] + dj[i];
                if (inRange(mi, mj)) {
                    Puzzle next = new Puzzle(now.map, new int[]{mi, mj}, now.dept + 1);
                    int temp = next.map[zero[0]][zero[1]];
                    next.map[zero[0]][zero[1]] = next.map[mi][mj];
                    next.map[mi][mj] = temp;
                    if (visit.add(toHashString(next.map))) {
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
