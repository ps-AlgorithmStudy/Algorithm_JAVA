package week3;

import java.io.*;
import java.util.*;

public class BJ_골드4_미세먼지안녕 {
	static int R, C, T;
	static int[][] maps;

	// 공기 청정기의 위쪽, 아래쪽 방향 출구
	static int airR1, airR2;
	// 상 - 하 - 좌 - 우
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static class Dust {
		int x;
		int y;
		int amount; // 따로 빼어 줘야

		public Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}

	static void solution() {

		while (T > 0) {
			// 새로운 먼지 queue 계산 후 반복
			Queue<Dust> queue = new ArrayDeque<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (maps[i][j] > 4) {
						// queue.offer(new int[] {i, j});
						queue.offer(new Dust(i, j, maps[i][j]));
					}
				}
			}

			// 현재 T에 대한 큐에 있는 먼지 객체들을 꺼내고
			while (!queue.isEmpty()) {
				Dust dust = queue.poll();
				// 인접 먼지 체크 및 관계
				int x = dust.x, y = dust.y;
				int diffuse = dust.amount / 5;
				int adjCount = 0;

				for (int d = 0; d < dx.length; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (isOutbound(nx, ny) || maps[nx][ny] == -1) {
						continue;
					}

					adjCount++;
					maps[nx][ny] += diffuse;
				}

				maps[x][y] -= diffuse * adjCount;
			}

			// 공기 바람 이동에 따른 먼지 이동
			circulate();
			T--;
			/*
			 * for (int[] b : maps) { System.out.println(Arrays.toString(b)); }
			 * System.out.println();
			 */
		}

		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (maps[i][j] > 0) {
					sum += maps[i][j];
				}
			}
		}
		System.out.println(sum);
	}

	static void circulate() {
		// airR1 회전
		for (int i = airR1 - 1; i > 0; i--) {
			maps[i][0] = maps[i - 1][0]; // 아래로 이동
		}
		for (int i = 0; i < C - 1; i++) {
			maps[0][i] = maps[0][i + 1]; // 한칸씩 땡겨줌
		}
		for (int i = 1; i <= airR1; i++) {
			maps[i - 1][C - 1] = maps[i][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			maps[airR1][i] = maps[airR1][i - 1];
		}
		maps[airR1][1] = 0;

		// airR2 회전
		for (int i = airR2 + 1; i < R - 1; i++) {
			maps[i][0] = maps[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			maps[R - 1][i] = maps[R - 1][i + 1];
		}
		for (int i = R - 1; i > airR2; i--) {
			maps[i][C - 1] = maps[i - 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			maps[airR2][i] = maps[airR2][i - 1];
		}
		maps[airR2][1] = 0;
	}

	static boolean isOutbound(int x, int y) {
		return (x < 0 || x >= R) || (y < 0 || y >= C);
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/input_bj_17144.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		maps = new int[R][C];
		boolean hasFound = false;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
				if (!hasFound && maps[i][j] == -1) {
					airR1 = i;
					airR2 = i + 1;
					hasFound = true;
				}
			}
		}

		solution();
		br.close();
	}
}
