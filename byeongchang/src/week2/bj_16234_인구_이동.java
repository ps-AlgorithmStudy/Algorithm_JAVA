package week2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16234_인구_이동 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/bj_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int scNum = 1;
		for(int i=0;i<scNum;i++) {
			Solution(br, st);
		}	

	}
	
	public static void Solution(BufferedReader br, StringTokenizer st) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		int L = Integer.parseInt(st.nextToken()); // 인구수 차이 최소값
		int R = Integer.parseInt(st.nextToken()); // 인구수 차이 최대값
		int[][] country = new int[N][N];
		int[][] visited = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = 1;
		boolean check = false;
		do{
			check = false;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(BFS(i, j, L, R, num, country, visited)) continue;
					if(makeAvg(country, visited, num)) continue;
					num++;
					check = true;
				}
			}
			visited = new int[N][N];
		} while(check);
		System.out.println(num);
	}
	public static boolean BFS(int x, int y, int L, int R, int num, int[][] country, int[][] visited) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		if(visited[x][y] != 0) return true;
		visited[x][y] = num;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= country.length || ny < 0 || ny >= country.length) continue;
				if (visited[nx][ny] == num) continue;
				if (Math.abs(country[cur[0]][cur[1]] - country[nx][ny]) < L || Math.abs(country[cur[0]][cur[1]] - country[nx][ny]) > R) continue;
				q.offer(new int[]{nx, ny});
				visited[nx][ny] = num;

			}
		}
		return false;
	}
	public static boolean makeAvg(int[][] country, int[][] visited, int num) {
		int sum = 0;
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		for(int i=0;i<country.length;i++) {
			for(int j=0;j<country.length;j++) {
				if(visited[i][j] == num) {
					sum += country[i][j];
					cnt++;
					q.offer(new int[] {i, j});
				}
			}
		}
		if(cnt <= 1) return true;
		int avg = sum/cnt;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			country[cur[0]][cur[1]] = avg;
		}
		return false;
	}
}
