package project.src.week6;

import java.util.*;
import java.io.*;

public class 연구소3 {
    static int N, M, min = Integer.MAX_VALUE, empty;
    static int[][] lab, tempLab;
    static List<Point> virus = new ArrayList<>();
    static Point[] selectedVirus;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];
        tempLab = new int[N][N];
        selectedVirus = new Point[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) virus.add(new Point(i, j));
                if (lab[i][j] == 0) empty++;
            }
        }

        dfs(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int start, int depth) {
        if (depth == M) {
            spreadVirus();
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            selectedVirus[depth] = virus.get(i);
            dfs(i + 1, depth + 1);
        }
    }

    static void spreadVirus() {
        for (int i = 0; i < N; i++) {
            System.arraycopy(lab[i], 0, tempLab[i], 0, N);
        }

        Queue<Point> queue = new ArrayDeque<>();
        for (Point p : selectedVirus) {
            queue.add(p);
            tempLab[p.x][p.y] = 3;
        }

        int time = 0, cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (cnt == empty) break;
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (tempLab[nx][ny] == 0 || tempLab[nx][ny] == 2) {
                        if (tempLab[nx][ny] == 0) cnt++;
                        queue.add(new Point(nx, ny));
                        tempLab[nx][ny] = 3;
                    }
                }
            }
            time++;
        }

        if (cnt == empty) min = Math.min(min, time);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
