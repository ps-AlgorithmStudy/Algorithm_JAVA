package algo_study.week10;

import java.io.*;
import java.util.*;

public class Main_bj_13460_구슬탈출_failed {

	static class Node {
		int x;
		int y;
		char type;

		public Node(int x, int y, char type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [x=").append(x).append(", y=").append(y).append(", type=").append(type).append("]");
			return builder.toString();
		}

	}

	static int N, M;

	static final int MAX_DEPTH = 11;

	// 중력 방향
	// 왼쪽(), 오른쪽(), 위(), 아래();
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { -1, 1, 0, 0 };

	static boolean hasReached;
	static int min;

	// O(4^L * M); L = 10, M = 10*10;
	static void solution(char[][] maps, Node red, Node blue, Node hole) {
		hasReached = false;

		rec(maps, red, blue, hole, 0, 0);

		if (!hasReached) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	static void rec(char[][] maps, Node red, Node blue, Node hole, int depth, int move_cnt) {

		// base case; 백트랙킹; 규칙에 맞지 않게 홀에 파란공이 들어가는 경우엔 바로 return;
		if (blue.x == hole.x && blue.y == hole.y) {
			return;
		}

		// base case;
		if (red.x == hole.x && red.y == hole.y) {
			hasReached = true;
			min = Math.min(min, move_cnt);
			return;
		}

		// base case
		if (depth == MAX_DEPTH) {
			// 이 때까지 빨간 구슬 구멍을 통해 못 빼냈다면 -1 반환될 것
			return;
		}

		for (int d = 0; d < dx.length; d++) {
			// 현재 상태를 기준으로 deepcopy
			char[][] temp = copy(maps);
			Node _red = copy(red);
			Node _blue = copy(blue);
			Node _hole = copy(blue);

			// 현재 방향으로 중력 적용
			log(temp, "================= depth: " + depth + " 중력 이동 전; 방향: " + d + "=================");
			rotate(temp, _red, _blue, _hole, d);
			log(temp, "================= depth: " + depth + " 중력 이동 후 방향: " + d + "=================");

			// 다음 상태로 전이
			rec(temp, _red, _blue, _hole, depth + 1, move_cnt + 1);
		}

	}

	static void rotate(char[][] maps, Node red, Node blue, Node hole, int d) {
		
		int br_x = 0, br_y = 0;
		int bb_x = 0, bb_y = 0;
		
		// 중력 왼쪽
		if (d == 0) {
			// (row, M-2)부터
			if(red.x == blue.x) {
				rotate(maps, red, blue, hole, red.x, M-2, d);
				return;
			}
			
			br_x = red.x;
			br_y = 1;
			bb_x = blue.x;
			bb_y = br_y;
			
		}
		// 중력 오른쪽
		else if (d == 1) {
			if(red.x == blue.x) {
				rotate(maps, red, blue, hole, red.x, M-2, d); // M in [3, 10]
				return;
			}
			
			br_x = red.x;
			br_y = M -2;
			bb_x = blue.x;
			bb_y = br_y;
		}
		// 중력 위쪽
		else if (d == 2) {		
			if(red.y == blue.y) {
				rotate(maps, red, blue, hole, 1, red.y, d);
				return;
			}
			
			br_x = 1;
			br_y = red.y;
			bb_x = br_x;
			bb_y = blue.y;
		}

		// 아래쪽
		else if (d == 3) {
			if(red.y == blue.y) {				
				rotate(maps, red, blue, hole, N - 2, red.x, d);
				return;
			}
			
			br_x = N - 2;
			br_y = red.y;
			bb_x = br_x;
			bb_y = blue.y;
		}
		
		rotate(maps, red, hole, br_x, br_y, d);
		rotate(maps, blue, hole, bb_x, bb_y, d);
	}

	static void rotate(char[][] maps, Node red, Node blue, Node hole, int bottomX, int bottomY, int d) {

		int row = bottomX, col = bottomY;
		
		do {
			int nx = row + dx[d], ny = col + dy[d];
			if (isOutbound(nx, ny)) {
				break;
			}

			char prev = maps[row][col];
			char now = maps[nx][ny];
			if (prev == '#' || now == '#') {
				break;
			}

			if (prev == '.' && (now == 'R' || now == 'B')) {
				maps[row][col] = maps[nx][ny];
				maps[nx][ny] = prev;
				continue;
			}

			if (prev == 'O' && now == 'R') {
				maps[nx][ny] = '.';
				red.x = row;
				red.y = col;
			}

			if (prev == 'O' && now == 'B') {
				maps[nx][ny] = '.';
				blue.x = row;
				blue.y = col;
			}

			row = nx;
			col = ny;
		} while (true);
	}

	static void rotate(char[][] maps, Node ball, Node hole, int bottomX, int bottomY, int d) {
		
		int x = bottomX, y = bottomY;
		int nx = x + dx[d], ny = y + dy[d];
		while (!isOutbound(nx, ny)) {
			char prev = maps[x][y];
			char now = maps[nx][ny];

			if (prev == '#' || now == '#') {
				break;
			}

			if (prev == '.' && now == ball.type) {
				maps[x][y] = maps[nx][ny];
				maps[nx][ny] = prev; // 공이 하나만 이동하는 거라
			}

			if (prev == 'O' && now == ball.type) {
				maps[nx][ny] = '.';
				ball.x = x;
				ball.y = y;
				break;
			}

			x = nx;
			y = ny;
			nx += dx[d];
			ny += dy[d];
		}
	}

	static void log(char[][] maps, String msg) {
		System.out.println(msg);
		for (char[] m : maps) {
			System.out.println(Arrays.toString(m));
		}
	}

	static boolean isOutbound(int x, int y) {
		return (x <= 0 || x >= N) || (y <= 0 || y >= M); // 경계칸은 모두 벽이라
	}

	static char[][] copy(char[][] maps) {
		char[][] result = new char[N][M];
		for (int i = 0; i < N; i++) {
			result[i] = Arrays.copyOf(maps[i], M);
		}
		return result;
	}

	static Node copy(Node n) {
		return new Node(n.x, n.y, n.type);
	}

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src\\algo_study\\week10\\input_13460_02.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		char[][] maps = new char[N][M];
		Node red = null, blue = null, hole = null;

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				char value = temp[j];
				if (value == 'R') {
					red = new Node(i, j, value);
				} else if (value == 'B') {
					blue = new Node(i, j, value);
				} else if (value == 'O') {
					hole = new Node(i, j, value);
				}
			}
			maps[i] = temp;
		}

		solution(maps, red, blue, hole);
	}
}
