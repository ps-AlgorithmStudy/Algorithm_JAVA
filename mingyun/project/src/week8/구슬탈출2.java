package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 구슬탈출2 {
    static int n, m;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static List<Comparator<Ball>> comparators = new ArrayList<>();

    static  class Ball {
        boolean color, out; int i,j;
        Ball(boolean a, int b, int c) {
            color = a; i = b; j = c;
        }
        Ball(Ball other) {
            this.color = other.color;
            this.i = other.i;
            this.j = other.j;
            this.out = other.out;
        }
    }

    static int result = Integer.MAX_VALUE;
    static void dfs(char[][] map, List<Ball> ballList, int cnt) {
        if (cnt >= result || cnt >= 10) return;
        f:for (int d=0;d<4;d++) {
            List<Ball> newBallList = new ArrayList<>();
            for (Ball ball : ballList) {
                newBallList.add(new Ball(ball));
            }
            newBallList.sort(comparators.get(d));
            boolean isMove = false;
            while (true) {
                boolean[][] v = new boolean[n][m];
                boolean flag = false;
                for (Ball ball:newBallList) {
                    int mi = ball.i + di[d];
                    int mj = ball.j + dj[d];
                    if (map[mi][mj] == '.' && !v[mi][mj] && !ball.out) {
                        ball.i = mi; ball.j = mj;
                        flag = true; isMove = true;
                    } else if (map[mi][mj] == 'O') {
                        ball.i = mi; ball.j = mj;
                        ball.out = true;
                    }
                    if (!ball.out) v[ball.i][ball.j] = true;
                }
                if (!flag) break;
            }
            if (!(newBallList.get(0).out && newBallList.get(1).out)) {
                for (Ball ball:newBallList) {
                    if (ball.out) {
                        if (ball.color) {
                            result = Math.min(result, cnt + 1);
                        }
                        continue f;
                    }
                }
                if (isMove) dfs(map,newBallList, cnt+1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week8/구슬탈출.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<Ball> ballList = new ArrayList<>();
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (map[i][j] == 'R') {
                    ballList.add(new Ball(true,i,j));
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    ballList.add(new Ball(false,i,j));
                    map[i][j] = '.';
                }
            }
        }

        comparators.add(Comparator.comparingInt((Ball o) -> o.i));
        comparators.add((o1, o2) -> -Integer.compare(o1.i, o2.i));
        comparators.add(Comparator.comparingInt((Ball o) -> o.j));
        comparators.add((o1, o2) -> -Integer.compare(o1.j, o2.j));

        dfs(map, ballList, 0);
        if (result != Integer.MAX_VALUE)System.out.println(result);
        else System.out.println(-1);
    }
}
