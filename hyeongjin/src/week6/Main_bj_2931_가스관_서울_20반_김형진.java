package week6;
import java.util.*;
import java.io.*;
public class Main_bj_2931_가스관_서울_20반_김형진 {
	static int R, C;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = { 0, 1, 0,-1};
	static int[][] road;
	static char[][] map;
	static boolean[][] v;
	
	static char checkBlock(int i, int j) {
		// 인접 행렬중 1보다 큰 곳의 위치를 판별
		if(road[i+1][j]>0) {
			if(road[i-1][j] >0) return '|';
			if(road[i][j+1] >0) return '1';
			if(road[i][j-1] >0) return '4';
		}
		if(road[i-1][j]>0) {
			if(road[i][j+1] >0) return '2';
			if(road[i][j-1] >0) return '3';
		}
		if(road[i][j-1] > 0 && road[i][j+1] > 0)
			return '-';
		
		return ' ';
		
	}
	static void setRoad(int i, int j) {
		
		// 해당 블록과 연결되는 부분의 road[][] 배열에 +1
		char block = map[i][j];
		road[i][j] ++;
		switch(block) {
		case '|':
			if(0 <= i-1) road[i-1][j] ++;
			if(R >= i+1) road[i+1][j] ++;
			break;
		case '-':
			if(C >  j+1) road[i][j+1] ++;
			if(0 <  j-1) road[i][j-1] ++;
			break;
		case '+':
			if(0 <= i-1) road[i-1][j] ++;
			if(R >= i+1) road[i+1][j] ++;
			if(C >  j+1) road[i][j+1] ++;
			if(0 <  j-1) road[i][j-1] ++;
			break;
		case '1':
			if(C >  j+1) road[i][j+1] ++;
			if(R >= i+1) road[i+1][j] ++;
			break;
		case '2':
			if(C >  j+1) road[i][j+1] ++;
			if(0 <= i-1) road[i-1][j] ++;
			break;
		case '3':
			if(0 <= i-1) road[i-1][j] ++;
			if(0 <  j-1) road[i][j-1] ++;
			break;
		case '4':
			if(0 <= i-1) road[i-1][j] ++;
			if(C >  j+1) road[i][j+1] ++;
			break;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 도면의 행 열 길이 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // 도면을 표현할 2차원 문자형 배열
		road = new int[R][C];// 길을 표시할 2차원 정수형 배열
		
		// 결과 행/렬값
		int resR = 0;
		int resC = 0;
		char resB;
		
		// 빈칸 체크용 변수
		int hole = 0;
		
		// 도면 값 입력
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] != 'M' && map[i][j] != 'Z' && map[i][j] != '.') {
					setRoad(i,j);
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// setRoad로 연결되는 부분에 +1 처리를 해줬으므로
				// 값이 0보다 크면서 도로가 놓아지지 않은 블록을 탐색
				// hole이 4일 때와 2일 때를 구별하기 위해 hole에는 최댓값
				if(road[i][j] > hole && map[i][j] == '.') {
					hole = road[i][j];
					resR = i;
					resC = j;
				}
			}
		}
		
		if(hole==4) resB = '+';            // 값이 4라면 + 모양 길만 배치 가능
		else resB = checkBlock(resR,resC); // 아니라면 + 제외 나머지 길 모양중 판별
		for (int[] r : road) {
			System.out.println(Arrays.toString(r));
		}
		System.out.printf("%d %d %c\n", resR+1, resC+1, resB);
		br.close();
	}
}
