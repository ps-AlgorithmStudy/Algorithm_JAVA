package week5.my;

import java.io.*;
import java.util.*;

public class bj_2931_가스관 {

	static boolean inRange(int i, int j, int n, int m) {
		return 0<=i && i<n && 0<=j && j<m;
	}

	static int findDirection(int direction, int next) {
		if (next == '-') {
			if (direction==1 || direction==3) direction = -1;
		}
		else if (next == '|') {
			if (direction==0 || direction==2) direction = -1;
		}
		else if (next == '1') {
			if (direction==2) direction=1;
			else if (direction==3) direction=0;
			else direction=-1;
		}
		else if (next == '2') {
			if (direction==1) direction=0;
			else if (direction==2) direction=3;
			else direction=-1;
		}
		else if (next == '3') {
			if (direction==0) direction=3;
			else if (direction==1) direction = 2;
			else direction=-1;
		}
		else if (next == '4') {
			if (direction==0)direction=1;
			else if (direction==3) direction=2;
			else direction=-1;
		}
		return direction;
	}

	static int[] di = {0, 1, 0, -1};
	static int[] dj = {1, 0, -1, 0};

	static boolean check(int i, int j, char[][] map, int n, int m, int direction) {
		int[][] v = new int[n][m];
		v[i][j] = 1;

		i += di[direction];
		j += dj[direction];
		while (inRange(i,j,n,m) && map[i][j]!='.' && map[i][j]!='Z') {
			char next = map[i][j];
			v[i][j]++;
			direction = findDirection(direction, next);
			if (direction==-1) break;
			i += di[direction];
			j += dj[direction];
		}
		if (inRange(i,j,n,m)&&map[i][j]=='Z') {
			v[i][j]++;
			for (int x=0;x<n;x++) {
				for (int y=0;y<m;y++) {
					if (map[x][y]=='+' && v[x][y]!=2) {
						return false;
					}
					else if (map[x][y]!='+' && map[x][y]!='.' && v[x][y]!=1) {
						return false;
					}
				}
			}
		}
		else return false;
		return true;
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_2931.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char [][] map = new char[n][m];
		int si=0, sj=0;

		for (int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();//맵저장
			for (int j=0;j<m;j++) {
				if (map[i][j]=='M') {
					si=i;
					sj=j;
				}
			}
		}


		int direction = 0;
		for (int d=0;d<4;d++) {
			int mi = si + di[d]; int mj = sj + dj[d];
			try {
				if (map[mi][mj]!='.'&&map[mi][mj]!='Z') direction = d;
			}catch (Exception ignored) {}
		}
		int startDirection = direction;

		int i = si, j = sj;
		while (map[i][j]!='.') {
			int mi = i + di[direction];
			int mj = j + dj[direction];
			if (!inRange(mi,mj,n,m)) break;
			char next = map[mi][mj];
			direction = findDirection(direction, next);
			i = mi; j = mj;
		}

		for (char c:new char[]{'|','-','1','2','3','4', '+'}) {
			map[i][j] = c;
			if (check(si,sj,map,n,m, startDirection)) {
				System.out.println((i+1) + " " + (j+1) + " " + c);
			}
		}
	}
}
