package algo_study.week10;

import java.io.*;
import java.util.*;

public class Main_bj_13460_구슬탈출2_bfs {

	static int N,M;
	static char[][] map;
	static boolean[][][][] v; // {rx, ry, bx, by, dist}
	
	static int min = Integer.MAX_VALUE;
	
	// down - up - right - left
	static int[] dx = {1, -1, 0 ,0};
	static int[] dy = {0, 0, 1, -1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		v = new boolean[N][M][N][M];
		
		int rx =0, ry =0;
		int bx =0, by =0;
		
		for(int i=0; i<N; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				char value = temp[j];
				if (value == 'R') {
					rx = i;
					ry = j;
				} else if (value == 'B') {
					bx = i;
					by = j;
				} /*else if (value == 'O') {
					
				}*/
			}
			
			map[i] = temp;
		}
		
		bfs(rx,ry,bx,by,0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void bfs(int rx, int ry, int bx, int by, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {rx,ry,bx,by, cnt});
		v[rx][ry][bx][by] =true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int pCnt = pos[4];
			
			if(pCnt>=10) {
				return;
			}
			for(int i=0; i<4; i++){
				int nRx = pos[0], nRy = pos[1];
				int nBx = pos[2], nBy = pos[3];
				
				// 빨간 구슬 이동 
				while(map[nRx+dx[i]][nRy+dy[i]] != '#') {
					nRx += dx[i];
					nRy += dy[i];
					if(map[nRx][nRy] == 'O') break;
				}
				
				// 파란 구슬 이동
				while(map[nBx+dx[i]][nBy+dy[i]] != '#') {
					nBx += dx[i];
					nBy += dy[i];
					if(map[nBx][nBy] == 'O') break;
				}
				
				// 규칙에 맞지 않게 홀에 파란공이 들어가는 경우엔 바로 continue;
				if(map[nBx][nBy] == 'O') continue;
				
				// Red만 들어간 경우 min 갱신 수행
				if(map[nRx][nRy] == 'O') {
					min = Math.min(min, pCnt+1);
					return;
				}
				
				// 빨간 파랑 서로 만났을 때 
				if(nRx == nBx && nRy == nBy) { 
					int red_move = Math.abs(nRx-pos[0]) + Math.abs(nRy-pos[1]);
					int blue_move = Math.abs(nBx-pos[2]) + Math.abs(nBy-pos[3]);
					
					// 파란 공이 더 빨리 도착한 경우  
					if(red_move > blue_move) {
						nRx -= dx[i];
						nRy -= dy[i];
					}else { // 빨간 공이 더 빨리 도착한 경우  
						nBx -= dx[i];
						nBy -= dy[i];
					}
				}
				
				
				// BFS에 상태를 넣어주는 조건!
				if(!v[nRx][nRy][nBx][nBy]) {
					v[nRx][nRy][nBx][nBy] = true;
					q.add(new int[] {nRx, nRy, nBx, nBy, pCnt+1});
				}
			}
		}
		
	}
}
