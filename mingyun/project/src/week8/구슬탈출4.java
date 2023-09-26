package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 구슬탈출4 {
    static int n, m;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static class Ball {
        boolean color, out;
        int i,j;
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

    static class State {
        List<Ball> balls;
        int cnt;

        State(List<Ball> balls, int cnt) {
            this.balls = balls;
            this.cnt = cnt;
        }
    }

    public static int bfs(char[][] map, List<Ball> ballList) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(ballList, 0));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            for (int d = 0; d < 4; d++) {
                List<Ball> nextBalls = new ArrayList<>();
                for (Ball ball : current.balls) {
                    nextBalls.add(new Ball(ball));
                }

                boolean isMove = false;
                while (true) {
                    boolean flag = false;
                    for (Ball ball : nextBalls) {
                        int mi = ball.i + di[d];
                        int mj = ball.j + dj[d];
                        if (map[mi][mj] == '.' && !ball.out) {
                            ball.i = mi; ball.j = mj;
                            flag = true;
                            isMove = true;
                        } else if (map[mi][mj] == 'O') {
                            ball.out = true;
                            if (ball.color) return current.cnt + 1;
                        }
                    }
                    if (!flag) break;
                }
                if (isMove) {
                    queue.offer(new State(nextBalls, current.cnt + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week8/구슬탈출.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<Ball> ballList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'R') {
                    ballList.add(new Ball(true, i, j));
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    ballList.add(new Ball(false, i, j));
                    map[i][j] = '.';
                }
            }
        }
        int result = bfs(map, ballList);
        System.out.println(result);
    }
}
