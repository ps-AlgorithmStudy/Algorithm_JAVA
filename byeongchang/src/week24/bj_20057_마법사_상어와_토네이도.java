package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20057
public class bj_20057_마법사_상어와_토네이도 {
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
        int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
        int N = map.length;
        int x = N / 2;
        int y = N / 2;
        while (x > 0 || y > 0) {
            int direction = idx % 2 == 1 ? 0 : 1;
            for (int i = 2 * direction; i <= 2 * direction + 1; i++) {
                for (int j = 0; j < idx; j++) {
                    int dx = dir[i][0];
                    int dy = dir[i][1];
                    int nx = x + dx;
                    int ny = y + dy;
                    if (nx < 0 || ny < 0)
                        return answer;

                    // 좌표 별 이동하는 모래 계산하기
                    answer += transferSand(nx, ny, dx, dy, map);

                    x = nx;
                    y = ny;
                }

            }
            idx++;
        }

        return answer;
    }

    // 좌표 별 이동하는 모래 계산
    public static int transferSand(int nx, int ny, int dx, int dy, int[][] map) {
        int result = 0;
        int sand = map[nx][ny];
        int left = sand;
        map[nx][ny] = 0;
        int N = map.length;

        int ax = nx + (2 * dx);
        int ay = ny + (2 * dy);
        int dv = (int) (sand * 0.05);
        left -= dv;
        result += checkBorder(dv, ax, ay, N, map);
        ax = nx + dy;
        ay = ny + -1 * dx;
        dv = (int) (sand * 0.07);
        left -= dv;
        result += checkBorder(dv, ax, ay, N, map);
        ax = nx + 2 * dy;
        ay = ny + -2 * dx;
        dv = (int) (sand * 0.02);
        left -= dv;
        result += checkBorder(dv, ax, ay, N, map);
        ax = nx + -1 * dy;
        ay = ny + dx;
        dv = (int) (sand * 0.07);
        left -= dv;
        result += checkBorder(dv, ax, ay, N, map);
        ax = nx + -2 * dy;
        ay = ny + 2 * dx;
        dv = (int) (sand * 0.02);
        left -= dv;
        result += checkBorder(dv, ax, ay, N, map);
        
        // 대각선 4개 좌표는 dx가 0인지에 따라 좌표 계산식이 다름
        if (dx == 0) {
            ax = nx + dy;
            ay = ny + dy;
            dv = (int) (sand * 0.1);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + -1 * dy;
            ay = ny + dy;
            dv = (int) (sand * 0.1);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + dy;
            ay = ny + -1 * dy;
            dv = (int) (sand * 0.01);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + -1 * dy;
            ay = ny + -1 * dy;
            dv = (int) (sand * 0.01);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
        } else {
            ax = nx + dx;
            ay = ny + -1 * dx;
            dv = (int) (sand * 0.1);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + dx;
            ay = ny + dx;
            dv = (int) (sand * 0.1);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + -1 * dx;
            ay = ny + -1 * dx;
            dv = (int) (sand * 0.01);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
            ax = nx + -1 * dx;
            ay = ny + dx;
            dv = (int) (sand * 0.01);
            left -= dv;
            result += checkBorder(dv, ax, ay, N, map);
        }
        ax = nx + dx;
        ay = ny + dy;
        result += checkBorder(left, ax, ay, N, map);

        return result;
    }
    
    // 경계 확인 후 벗어나는 모래 반환, 아니면 map에 반영
    public static int checkBorder(int value, int ax, int ay, int N, int[][] map) {
        if (ax < 0 || N <= ax || ay < 0 || N <= ay)
            return value;
        else {
            map[ax][ay] += value;
            return 0;
        }
    }
}