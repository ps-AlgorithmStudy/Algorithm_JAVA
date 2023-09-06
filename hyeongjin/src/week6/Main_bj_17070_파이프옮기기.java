package week6;
import java.io.*;
import java.util.*;
public class Main_bj_17070_파이프옮기기 {
	static int N;
	static int ans = 0;
	static int[] di = {0, 1, 1};
	static int[] dj = {1, 1, 0};
	static int[][] map;
	static boolean[][]v;
	static boolean[] w;
	static void pipe(int i, int j, int before) {
		w = new boolean[] {true, true, true};
		if(i == N-1 && j == N-1) {
			ans ++;
			return;
		}
		for (int d = 0; d < 3; d++) {
			if(d == 0 && before == 2) continue;
			if(d == 2 && before == 0) continue;
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0 <= ni && ni < N && 0<= nj && nj < N && !v[ni][nj] && map[ni][nj] != 1) {
				if(d == 1 && (map[ni][j] == 1 || map[i][nj] == 1)) continue;
				v[ni][nj] = true;
				pipe(ni,nj,d);
				v[ni][nj] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ans = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v   = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		v[0][0] = true;
		v[0][1] = true;
		pipe(0,1,0);
		
		System.out.println(ans);
	}
}
