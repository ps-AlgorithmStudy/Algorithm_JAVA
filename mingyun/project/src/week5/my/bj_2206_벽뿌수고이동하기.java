package week5.my;

import java.io.*;
import java.util.*;

public class bj_2206_벽뿌수고이동하기 {
    static class Move {
        int i, j, dept; boolean destroy;
        Move(int a, int b, int c, boolean d) {
            i = a; j = b; dept = c; destroy = d;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_2206.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][m];
        for (int i=0;i<n;i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j=0;j<m;j++) map[i][j] = temp[j]!='0';
        }

        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};

        boolean[][][] v = new boolean[n][m][2];

        ArrayDeque<Move> deque = new ArrayDeque<>(1000);
        deque.addLast(new Move(0,0, 1, false));

        v[0][0][0] = true;

        int res = -1;
        while (!deque.isEmpty()) {
            Move move = deque.removeFirst();
            if (move.i == n-1 && move.j==m-1) {
                res = move.dept;
                break;
            }

            for (int d=0;d<4;d++) {
                int i = move.i + di[d], j = move.j + dj[d];
                if (0<=i && i<n && 0<=j && j<m) {
                    if (!map[i][j] && !v[i][j][move.destroy ? 1 : 0]) {
                        v[i][j][move.destroy ? 1 : 0] = true;
                        deque.addLast(new Move(i,j, move.dept+1, move.destroy));
                    }
                    if (map[i][j] && !move.destroy && !v[i][j][1]) {
                        v[i][j][1] = true;
                        deque.addLast(new Move(i,j, move.dept+1, true));
                    }
                }
            }
        }
        System.out.println(res);
    }
}
