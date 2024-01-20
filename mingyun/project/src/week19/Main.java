package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 상우하좌 순서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // 입력받은 체스판 정보만 저장
        int[][] a = new int[l][l];
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < l; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] chess = new int[n + 1][][];
        int[][] shield = new int[l][l];
        int[][] knight = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            chess[i] = new int[h * w][2];
            knight[i] = new int[]{r - 1, c - 1, h, w, k};
            for (int j = 0; j < h; j++) {
                for (int jj = 0; jj < w; jj++) {
                    shield[r - 1 + j][c - 1 + jj] = i;
                    chess[i][j * w + jj] = new int[]{r - 1 + j, c - 1 + jj};
                }
            }
        }

        int ans = 0;
        int[] damage = new int[n + 1];
        for (int order = 0; order < q; order++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (knight[i] == null) continue;

            // 다음 칸에 벽이 있는지 확인
            int wall = 0;
            int x = knight[i][0];
            int y = knight[i][1];
            Deque<int[]> qu = new ArrayDeque<>();
            qu.add(new int[]{x, y});
            int[][] check = new int[l][l];
            check[x][y] = 1;
            int[] move_shield = new int[n + 1];
            move_shield[i] = 1;
            while (!qu.isEmpty()) {
                int[] pos = qu.poll();
                x = pos[0];
                y = pos[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (nx >= 0 && nx < l && ny >= 0 && ny < l) {
                        if (check[nx][ny] == 0) {
                            if (shield[nx][ny] == shield[x][y]) {
                                check[nx][ny] = 1;
                                qu.add(new int[]{nx, ny});
                            }
                            if (j == d) {
                                if (shield[nx][ny] > 0 && shield[nx][ny] != shield[x][y]) {
                                    check[nx][ny] = 1;
                                    qu.add(new int[]{nx, ny});
                                    move_shield[shield[nx][ny]] = 1;
                                }
                                if (a[nx][ny] == 2) {
                                    wall = 1;
                                    break;
                                }
                            }
                        }
                    } else {
                        if (j == d) {
                            wall = 1;
                            break;
                        }
                    }
                }
                if (wall == 1) break;
            }

            int[][] temp_shield = new int[l][l];
            if (wall == 0) {
                for (int idx = 1; idx <= n; idx++) {
                    if (move_shield[idx] == 1) {
                        for (int[] pos : chess[idx]) {
                            x = pos[0];
                            y = pos[1];
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            temp_shield[nx][ny] = idx;
                            pos[0] = nx;
                            pos[1] = ny;
                        }
                        knight[idx][0] = chess[idx][0][0];
                        knight[idx][1] = chess[idx][0][1];
                    }
                }

                for (x = 0; x < l; x++) {
                    for (y = 0; y < l; y++) {
                        if (shield[x][y] > 0 && temp_shield[x][y] == 0) {
                            if (move_shield[shield[x][y]] == 1) {
                                shield[x][y] = 0;
                            }
                        }
                        if (temp_shield[x][y] > 0) {
                            shield[x][y] = temp_shield[x][y];
                        }
                    }
                }

                for (x = 0; x < l; x++) {
                    for (y = 0; y < l; y++) {
                        if (a[x][y] == 1 && shield[x][y] > 0) {
                            int idx = shield[x][y];
                            if (move_shield[idx] == 1 && idx != i) {
                                if (knight[idx] != null) {
                                    knight[idx][4]--;
                                    damage[idx]++;
                                    if (knight[idx][4] == 0) {
                                        for (int[] pos : chess[idx]) {
                                            shield[pos[0]][pos[1]] = 0;
                                        }
                                        knight[idx] = null;
                                        damage[idx] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            ans += damage[i];
        }

        System.out.println(ans);
    }
}
