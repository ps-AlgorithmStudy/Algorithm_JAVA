package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1520_내리막_길 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		System.out.println(solution(br, st));
		br.close();
	}

	public static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		int[][] visited = new int[M][N];
		for(int[] v: visited) Arrays.fill(v, -1);
		int[][] direc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		return dfs(0, 0, M-1, N-1, map, visited, direc);
	}

	public static int dfs(int x, int y, int m, int n, int[][] map,int[][] visited, int[][] direc) {
		// 끝에 도달하면 1 반환
		if(x == m && y == n) return 1;
		// 이미 방문한 곳이면 경로 수 반환
		if(visited[x][y] != -1) return visited[x][y];

		visited[x][y] = 0;
		for(int[] d: direc) {
			int nx = x + d[0];
			int ny = y + d[1];
			// 범위를 벗어나면 continue
			if(nx < 0 || ny < 0 || nx > m || ny > n || map[nx][ny] >= map[x][y]) continue;
			visited[x][y] += dfs(nx, ny, m, n, map, visited, direc);
		}
		return visited[x][y];
	}
}
