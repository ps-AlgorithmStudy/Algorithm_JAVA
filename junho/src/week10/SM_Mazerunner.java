package algo_study.week10;

import java.io.*;
import java.util.*;

public class SM_Mazerunner {

	static int N, M, K;

	// 상하 우선
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static int[] user_dist; // 유저들의 이동 거리 합을 기록
	static int ex, ey; // 출구 좌표를 기록

	// 사용자 정보
	static class User {
		int key;
		int x;
		int y;

		public User(int key, int x, int y) {
			super();
			this.key = key;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("User [key=").append(key).append(", x=").append(x).append(", y=").append(y).append("]");
			return builder.toString();
		}

	}

	static void solution(int[][] maps, List<User> users) {

		while (K > 0) {
			print(maps, "==================== K : " + K + " ====================");
			System.out.println(users);
			System.out.println("탈출 위치 {" + ex + ", " + ey + "}");
	

			Queue<User> removeQ = new ArrayDeque<>();
			/* step1 - 유저 이동 구현 */
			for (User user : users) {
				// 현재 dist
				int curDist = getDist(user.x, user.y);

				for (int d = 0; d < dx.length; d++) {
					int nx = user.x + dx[d];
					int ny = user.y + dy[d];

					if (isOutbound(nx, ny) || maps[nx][ny] > 0) {
						continue;
					}

					int nextDist = getDist(nx, ny);

					// 이동하는 경우
					if (nextDist < curDist) {
						log("유저" + user.key + " 이동! prev{" + user.x + ", " + user.y + "}, next{" + nx + ", " + ny
								+ "}");
						user.x = nx;
						user.y = ny;
						user_dist[user.key] += 1;

						// 탈출구 확인
						if (user.x == ex && user.y == ey) {
							log("유저" + user.key + " 탈출");
							removeQ.add(user);
						}
						break;
					}
				}
			
			}
			
			// 유저 탈출 시켜줌
			while(!removeQ.isEmpty()) {
				users.remove(removeQ.poll());
			}
			
			if(users.isEmpty()) {
				break;
			}
			
			/* step2 */
			// 탈출 좌표를 기준으로
			// 유저들과 비교해서
			// 가장 작은 정사각형 범위를 구한다
			int[] pos = getMinArea(users);

			/* step3 */
			// 그리고 90도 회전 구현한다.
			// 이때 범위에 존재하는 유저들의 좌표도 90도 회전하도록 구현
			// 벽들을 내구도 1씩 감소하게끔
			rotate(pos, maps, users);

			// K감소시킴
			K--;
		}

		// final. 결과 출력
		int answer = 0;
		for (int num : user_dist) {
			answer += num;
		}

		System.out.println(answer);
		System.out.println((ex + 1) + " " + (ey + 1)); // 0'based -> 1'based

	}

	// 탈출 지점을 기준으로 정사각형 길이, 좌상단 좌표 값을
	// 주어진 정렬 기준에 맞춰 정렬 후 제일 앞에 오는 거 반환
	// TODO
	static int[] getMinArea(List<User> users) {

		// 가장 작은 정사각형부터 모든 정사각형을 만들어봅니다.
		for (int sz = 2; sz <= N; sz++) {
			// 가장 좌상단 r 좌표가 작은 것부터 하나씩 만들어봅니다.
			for (int x1 = 0; x1 < N - sz + 1; x1++) {
				// 가장 좌상단 c 좌표가 작은 것부터 하나씩 만들어봅니다.
				for (int y1 = 0; y1 < N - sz + 1; y1++) {
					int x2 = x1 + sz - 1;
					int y2 = y1 + sz - 1;

					// 만약 출구가 해당 정사각형 안에 없다면 스킵합니다.
					if (!(x1 <= ex && ex <= x2 && y1 <= ey && ey <= y2)) {
						continue;
					}

					// 한 명 이상의 참가자가 해당 정사각형 안에 있는지 판단합니다.
					boolean isTravelerIn = false;

					for (User user : users) {
						if (x1 <= user.x && user.x <= x2 && y1 <= user.y && user.y <= y2) {
							isTravelerIn = true;
						}
					}

					// 만약 한 명 이상의 참가자가 해당 정사각형 안에 있다면
					// sx, sy, sqaureSize 정보를 갱신하고 종료합니다.
					if (isTravelerIn) {
						return new int[] { x1, y1, sz};
					}

				}
			}
		}

		return null;
	}

	static void rotate(int[] pos, int[][] maps, List<User> users) {
		// 특정 범위를 copy할까 아님 그대로 둘까
		final int X = pos[0], Y = pos[1], L = pos[2];
		// final int L = Math.abs(ex - X) + 1;

		print(maps, ">> 이동 전 maps{X : " + X + ", Y : " + Y + ", L : " + L + "}");

		// 벽 정보 갱신
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		for (int i = X; i < X + L; i++) {
			int[] temp = new int[L];
			for (int j = Y; j < Y + L; j++) {
				int value = maps[i][j];
				if (value > 0)
					value--;
				temp[j - Y] = value; // [0, L) 범위
			}
			stack.push(temp);
		}

		// 90 도 회전 결과 갱신
		for (int j = Y; j < Y + L; j++) {
			if (stack.isEmpty())
				continue;

			int[] temp = stack.pop();
			for (int i = X; i < X + L; i++) {
				maps[i][j] = temp[i - X];
			}
		}

		print(maps, ">> 이동 후 maps");

		// User 정보 갱신
		for (User user : users) {
			if (isOutbound(user, X, Y, L)) {
				continue;
			}

			// 원점으로 해당 부분 정사각형의 요소들을 옮겼다가
			// 이동하고 나서
			// 다시 기존 offset 위치로 옮겨줌
			int ox = user.x - X, oy = user.y - Y;
			user.x = X + oy;
			user.y = Y + L - ox - 1;
		}

		// 탈출 위치 갱신
		int ox = ex - X, oy = ey - Y;
		ex = X + oy;
		ey = Y + (L - ox - 1);
	}

	static boolean isOutbound(User user, int startX, int startY, int L) {
		return (user.x < startX || user.x >= startX + L) || (user.y < startY || user.y >= startY + L);
	}

	static boolean isOutbound(int x, int y) {
		return (x < 0 || x >= N) || (y < 0 || y >= N);
	}

	static int getDist(int x, int y) {
		return Math.abs(ex - x) + Math.abs(ey - y);
	}

	static void print(int[][] maps, String msg) {
		System.out.println(msg);
		for (int[] m : maps) {
			System.out.println(Arrays.toString(m));
		}
	}

	static void log(String msg) {
		System.out.println(">> " + msg);
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src\\algo_study\\week9\\input_sm_given_01.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] maps = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		List<User> users = new ArrayList<>();
		user_dist = new int[M];

		// 0'based 로 변환
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			users.add(new User(i, x - 1, y - 1));
		}

		// 0'based 로 변환
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;

		solution(maps, users);
	}

}
