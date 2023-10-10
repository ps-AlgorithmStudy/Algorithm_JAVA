package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 벽부수고_이동하기 {
    static class Move {
        int i, j, dept; int destroy;
        Move(int a, int b, int c, int d) {
            i = a; j = b; dept = c; destroy = d;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/벽부수고_이동하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][m];
        for (int i=0;i<n;i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j=0;j<m;j++) map[i][j] = temp[j]!='0';
        }

        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};

        boolean[][][] v = new boolean[n][m][k+1];

        ArrayDeque<Move> deque = new ArrayDeque<>();
        deque.add(new Move(0,0, 1, 0));

        v[0][0][0] = true;

        int res = -1;
        while (!deque.isEmpty()) {
            Move move = deque.poll();
            if (move.i == n-1 && move.j==m-1) {
                res = move.dept;
                break;
            }

            for (int d=0;d<4;d++) {
                int i = move.i + di[d], j = move.j + dj[d];
                if (0 > i || i > n-1 || 0 > j || j > m-1) continue;
                if (!map[i][j] && !v[i][j][move.destroy]) {
                    v[i][j][move.destroy] = true;
                    deque.add(new Move(i,j, move.dept+1, move.destroy));
                }
                else if (move.destroy < k && !v[i][j][move.destroy+1]) {
                    v[i][j][move.destroy+1] = true;
                    deque.add(new Move(i,j, move.dept+1, move.destroy+1));
                }
            }
        }
        System.out.println(res);
    }
}