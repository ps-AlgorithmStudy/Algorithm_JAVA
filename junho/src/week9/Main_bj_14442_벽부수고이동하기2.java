package week9;

import java.io.*;
import java.util.*;

public class Main_bj_14442_벽부수고이동하기2 {
	
	static int N, M, K;
	
	// 4방탐색
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	
	static int solution(char[][] maps) {
		// bfs 구현
		// 3가지 상태를 담는다		
		Queue<int[]> q = new ArrayDeque<>();
		
		// (x, y, 현재 k)에 대한 기록 배열
		int[][][] v = new int[N][M][K + 1];
		
		v[0][0][K] = 1;
		q.add(new int[] {0, 0, K});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1], k = cur[2];
			
			// 목표 지점에 도착했을 때
			if(x == N -1 && y == M - 1) {
				return v[x][y][k];
			}
			
			for(int d = 0; d < dx.length; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(isOutbound(nx, ny)) {
					continue;
				}
				
				
				// 다음 위치가 벽인 경우
				// 다음 k-1에 대해 방문한적이 없는 좌표 공간이면
				// 벽을 제거하고 이동하는 상태를 큐에 넣음
				if(maps[nx][ny] == '1' && k > 0 && v[nx][ny][k-1] == 0) {
					v[nx][ny][k-1] = v[x][y][k] + 1;
					q.offer(new int[] {nx, ny, k-1});
				} 
				
				// 현재 k에 대해 방문한적이 없는 좌표 공간이면
				// 벽을 제거하지 않고 이동하는 상태를 큐에 넣는다.
				if(maps[nx][ny] == '0' && v[nx][ny][k] == 0) {
					v[nx][ny][k] = v[x][y][k] + 1;
					q.offer(new int[] {nx, ny, k});
				}
				
			}
			
		}
		
		return -1;
	}
	
	static boolean isOutbound(int x, int y) {
		return (x < 0 || x >= N) || (y < 0 || y >= M);
	}
	
	public static void main(String[] args) throws Exception {
		
		// System.setIn(new FileInputStream("src\\algo_study\\week9\\input_14442_hd_3.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		char[][] maps = new char[N][M];
		for(int i = 0; i < N; i++) {
			maps[i] = br.readLine().toCharArray();
		}
		
		System.out.println(solution(maps));
		
		br.close();
	}
}
