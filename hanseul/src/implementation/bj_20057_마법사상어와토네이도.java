package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_20057_마법사상어와토네이도 {

    static int N;
    static int[][] arr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int sandOut = 0;

    static void tornado(){
        int dir = 0;
        int x = N / 2;
        int y = N / 2;
        for (int i = 1; i < N; i++) {
            moveTornado(x, y, dir, i);
            x += dx[dir] * i;
            y += dy[dir] * i;
            dir = (dir + 1) % 4;
            moveTornado(x, y, dir, i);
            x += dx[dir] * i;
            y += dy[dir] * i;
            dir = (dir + 1) % 4;
        }
        moveTornado(x, y, dir, N - 1);
    }

    static void moveTornado(int x, int y, int dir, int repeat){
        int tx = x;
        int ty = y;
        for (int i = 0; i < repeat; i++) {
            tx += dx[dir];
            ty += dy[dir];
            calSand(tx, ty, dir);
        }
    }

    static void calSand(int x, int y, int dir){
        int way = (dir + 1) % 4;
        int[] sand = new int[5];
        int tx, ty;
        sand[0] = (int) (arr[x][y] * 0.01);
        sand[1] = (int) (arr[x][y] * 0.02);
        sand[2] = (int) (arr[x][y] * 0.05);
        sand[3] = (int) (arr[x][y] * 0.07);
        sand[4] = (int) (arr[x][y] * 0.10);

        int sum = (sand[0] + sand[1] + sand[2] + sand[3] + sand[4]) * 2 - sand[2];

        if (sand[0] != 0){
            tx = x - dx[dir];
            ty = y - dy[dir];
            isRange(tx + dx[way], ty + dy[way], sand[0]);
            isRange(tx - dx[way], ty - dy[way], sand[0]);
        }
        if (sand[1] != 0){
            tx = x; ty = y;
            isRange(tx + dx[way] * 2, ty + dy[way] * 2, sand[1]);
            isRange(tx - dx[way] * 2, ty - dy[way] * 2, sand[1]);
        }
        if (sand[2] != 0){
            tx = x; ty = y;
            isRange(tx + dx[dir] * 2, ty + dy[dir] * 2, sand[2]);
        }
        if (sand[3] != 0){
            tx = x; ty = y;
            isRange(tx + dx[way], ty + dy[way], sand[3]);
            isRange(tx - dx[way], ty - dy[way], sand[3]);
        }
        if (sand[4] != 0){
            tx = x + dx[dir];
            ty = y + dy[dir];
            isRange(tx + dx[way], ty + dy[way], sand[4]);
            isRange(tx - dx[way], ty - dy[way], sand[4]);
        }
        tx = x + dx[dir];
        ty = y + dy[dir];
        isRange(tx, ty, arr[x][y] - sum);
        arr[x][y] = 0;
    }

    static void isRange(int x, int y, int sand){
        if (x < 0 || x >= N || y < 0 || y >= N)
            sandOut += sand;
        else
            arr[x][y] += sand;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado();

        System.out.println(sandOut);

    }
}
