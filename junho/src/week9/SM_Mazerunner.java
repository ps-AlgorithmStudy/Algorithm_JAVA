package algo_study.week9;

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

		// 출력부터
		System.out.println(users);

		while (K > 0) {
			print(maps);
			
			// K == 0 이전에 모든 유저가 탈출 했으면 while break;
			if (users.isEmpty()) {
				break;
			}

			/* step1 - 유저 이동 구현 */
			for (User user : users) {
				// 현재 dist
				int curDist = getDist(user.x, user.y);
				
				for(int d = 0; d < dx.length; d++) {
					int nx = user.x + dx[d];
					int ny = user.y + dy[d];
					
					if(isOutbound(nx, ny) || maps[nx][ny] > 0) {
						continue;
					}
					
					int nextDist = getDist(nx, ny);
					
					// 이동하는 경우
					if(nextDist < curDist) {
						log("유저"+user.key + " 이동! prev{"+user.x + ", " + user.y +"}, next{" + nx + ", " + ny +"}");
						user.x = nx;
						user.y = ny;
						user_dist[user.key] += 1;
					}
				}
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
		System.out.println(ex + " " + ey);

	}
	
	
	static int[] getMinArea(List<User> users) {
		
		
		return new int[] {0, 0};
	}
	
	
	static void rotate(int[] pos, int[][] maps, List<User> users) {
		// 특정 범위를 copy할까 아님 그대로 둘까
		int x = pos[0], y = pos[1];
		final int L = Math.abs(ex - x) + 1;
		
		// 벽 정보 갱신
		int[][] copied = new int[L][L];
		
		for(int i = x; i < x + L; i++) {
			for(int j = y; j < y + L; j++) {
				int value = maps[i][j];
				if(value > 0) {
					value--;
				}
				copied[j][L-1-i] = value;
			}
		}
		
		for(int i = x; i < x + L; i++) {
			for(int j = y; j < y + L; j++) {
				maps[i][j] = copied[i][j];
			}
		}
		
		// User 정보 갱신
		for(User user : users) {
			if(isOutbound(user, x, y, L)) {
				continue;
			}
			int tempX = user.x, tempY = user.y;
			
			user.x = tempY;
			user.y = L - 1 - tempX;
		}
		
		// 탈출 위치 갱신
		int tempX = ex, tempY = ey;
		ex = ey;
		ey = L - 1 - tempX;
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

	static void print(int[][] maps) {
		System.out.println("==================== K : " + K + " ====================");
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
			users.add(new User(i, x-1, y-1));
		}
		
		// 0'based 로 변환
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;

		solution(maps, users);
	}

}
