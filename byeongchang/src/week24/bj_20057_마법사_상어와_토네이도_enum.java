package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

// https://www.acmicpc.net/problem/20057
public class bj_20057_마법사_상어와_토네이도_enum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }

    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return tornado(map);
    }

    public static int tornado(int[][] map) {
        int answer = 0;
        int idx = 1;
        Direction[] dir = Direction.values();
        SpreadSand[] sv = SpreadSand.values();
        int N = map.length;
        int x = N / 2;
        int y = N / 2;
        Sand sn = new Sand(x,y,0,0, map);
        while (x > 0 || y > 0) {
            int direction = idx % 2 == 1 ? 0 : 1;    // idx가 홀수이면 진행방향이 좌,하, idx가 짝수이면 진행방향이 우,상
            for (int i = 2 * direction; i <= 2 * direction + 1; i++) {
                for (int j = 0; j < idx; j++) {
                    int dx = dir[i].dx;
                    int dy = dir[i].dy;
                    int nx = x + dx;
                    int ny = y + dy;
                    if (nx < 0 || ny < 0)
                        return answer;    // 토네이도가 0,0이면 소멸

                    sn.change(nx, ny, dx, dy);    // 객체 재사용해서 생성하는 시간 줄임
                    map[nx][ny] = 0;

                    // 좌표 별 이동하는 모래 계산하기
                    answer += transferSand(sn, sv);

                    x = nx;
                    y = ny;
                }
            }
            idx++;
        }

        return answer;
    }



    // 좌표 별 이동하는 모래 계산
    public static int transferSand(Sand sn, SpreadSand[] sv) {
        int result = 0;

        // enum을 이용한 반복문
        for(SpreadSand ss: sv) {
            int res = ss.calc.apply(sn);
            result += res;
        }
        return result;
    }

    // 경계 확인 후 벗어나는 모래의 양 반환, 아니면 map에 반영
    public static int checkBorder(int value, int ax, int ay, int N, int[][] map) {
        if (ax < 0 || N <= ax || ay < 0 || N <= ay)
            return value;
        else {
            map[ax][ay] += value;
            return 0;
        }
    }

    enum Direction {
        LEFT(0, -1),
        DOWN(1, 0),
        RIGHT(0, 1),
        UP(-1, 0);

        final int dx;
        final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    enum SpreadSand {
        A((n) -> {
            int ax = n.nx + (2 * n.dx);
            int ay = n.ny + (2 * n.dy);
            int dv = (int) (n.sand * 0.05);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        B((n)->{
            int ax = n.nx + n.dy;
            int ay = n.ny + -1 * n.dx;
            int dv = (int) (n.sand * 0.07);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        C((n)->{
            int ax = n.nx + 2 * n.dy;
            int ay = n.ny + -2 * n.dx;
            int dv = (int) (n.sand * 0.02);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        D((n)->{
            int ax = n.nx + -1 * n.dy;
            int ay = n.ny + n.dx;
            int dv = (int) (n.sand * 0.07);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        E((n)->{
            int ax = n.nx + -2 * n.dy;
            int ay = n.ny + 2 * n.dx;
            int dv = (int) (n.sand * 0.02);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        F((n)->{
            int ax, ay;
            if(n.dx == 0) {
                ax = n.nx + n.dy;
                ay = n.ny + n.dy;

            } else {
                ax = n.nx + n.dx;
                ay = n.ny + -1 * n.dx;
            }
            int dv = (int) (n.sand * 0.1);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        G((n)->{
            int ax, ay;
            if(n.dx == 0) {
                ax = n.nx - n.dy;
                ay = n.ny + n.dy;

            } else {
                ax = n.nx + n.dx;
                ay = n.ny + n.dx;
            }
            int dv = (int) (n.sand * 0.1);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        H((n)->{
            int ax, ay;
            if (n.dx == 0) {
                ax = n.nx + n.dy;
                ay = n.ny - n.dy;
            } else {
                ax = n.nx - n.dx;
                ay = n.ny - n.dx;
            }
            int dv = (int) (n.sand * 0.01);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        I((n)->{
            int ax, ay;
            if (n.dx == 0) {
                ax = n.nx - n.dy;
                ay = n.ny - n.dy;
            } else {
                ax = n.nx - n.dx;
                ay = n.ny + n.dx;
            }
            int dv = (int) (n.sand * 0.01);
            return checkBorder(dv, ax, ay, n.N, n.map);
        }),
        J((n)->{
            int ax = n.nx + n.dx;
            int ay = n.ny + n.dy;
            int dv = n.sand -((int) (n.sand * 0.05) + (int) (n.sand * 0.07) * 2 + (int) (n.sand * 0.02) * 2 + (int) (n.sand * 0.1) * 2 + (int) (n.sand * 0.01) * 2);
            return checkBorder(dv, ax, ay, n.N, n.map);
        });



        final Function<Sand, Integer> calc;

        SpreadSand(Function<Sand, Integer> func) {
            this.calc = func;
        }



    }
    static class Sand {
        int nx;
        int ny;
        int dx;
        int dy;
        int sand;
        int N;
        int[][] map;

        public Sand(int nx, int ny, int dx, int dy, int sand, int n, int[][] map) {
            this.nx = nx;
            this.ny = ny;
            this.dx = dx;
            this.dy = dy;
            this.sand = sand;
            this.N = n;
            this.map = map;
        }
        public Sand(int nx, int ny, int dx, int dy, int[][] map) {
            this(nx, ny, dx, dy, map[nx][ny], map.length, map);
        }

        void change(int nx, int ny, int dx, int dy) {
            this.nx = nx;
            this.ny = ny;
            this.dx = dx;
            this.dy = dy;
            this.sand = map[nx][ny];
        }
    }
}