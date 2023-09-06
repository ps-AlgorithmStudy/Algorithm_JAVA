package week6;
import java.util.*;
import java.io.*;

public class Main_bj_17142_연구소3 {
	static int N, R;
	static int ans, room;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = { 0, 1, 0,-1};
	static int[][] map;
	static boolean[][] wall;
	static Virus[] virus;

	static class Virus{
		int i;
		int j;
		
		Virus(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static void comb(int s,int cnt, int vcnt, Virus[] Activated) {
		if(cnt == R) {
			for (Virus virus : Activated) {
				System.out.println(virus.i+" , "+ virus.j);
			}
			BFS(Activated); return;
		}
		for (int i = s; i < vcnt; i++) {
			Activated[cnt] = virus[i];
			comb(i+1, cnt +1, vcnt, Activated);
		}
	}
	static void BFS(Virus[] Activated) {
		int[][] visit = new int[N][N];
		ArrayDeque<int[]> vq = new ArrayDeque<>();
		int max = 0;
		int infected = 0;
		for (Virus v : virus) {
			 visit[v.i][v.j] = -1;
		}
		for (Virus va : Activated) {
			 visit[va.i][va.j] = 1;
			 vq.add(new int[] {va.i, va.j});
		}
		
		while(!vq.isEmpty()) {
			int[] current = vq.poll();
			int i = current[0];
			int j = current[1];
			int curV = visit[i][j];
			max = Math.max(max, curV);
			if(visit[i][j] != -1) infected ++;
			for (int d = 0; d < 4; d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(0 <= ni && ni < N && 0 <= nj && nj < N && !wall[ni][nj] && visit[ni][nj]!=-1){
					visit[ni][nj] = curV+1;
					vq.offer(new int[] {ni,nj});
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visit[i][j]+" ");
			}System.out.println();
		}System.out.println();
		System.out.println(infected + " , " +(room+R));
		if(infected == room + R) {
			  ans = Math.min(ans, max-1);
		}
		
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		map = new int[N][N];
		wall = new boolean[N][N];
		Virus[] Activated = new Virus[R];
		
		int vcnt = 0;
		room = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) vcnt ++;
				if(map[i][j] == 1) wall[i][j] = true;
				if(map[i][j] == 0) room ++;
			}
		}
		int cnt = 0;
		virus = new Virus[vcnt];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) {
					virus[cnt] = new Virus(i,j);
					cnt ++;
				}
			}
		}
		comb(0,0,vcnt,Activated);
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}
}
