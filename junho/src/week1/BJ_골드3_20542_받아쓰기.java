package week1;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_골드3_20542_받아쓰기 {
	
	static int N, M;
	
	static int[] records;
	static int MIN;
	
	// 완탐으로 풀면
	// 최악의 경우 3^{백만}
	static int solution(String input, String answer) {
	
		char[] A = input.toCharArray();
		char[] B = answer.toCharArray();
		
		int[][] records = new int[N + 1][M + 1];
		
		// 자신의 편집거리 초기화 수행
		// 행 성분 : 문자열 input에 구성된 문자
		// 열 성분 : 문자열 answer에 구성된 문자
		for(int i = 1; i <= N; i++) {
			records[i][0] = i; 
		}
		
		for(int j = 1; j <= M; j++) {
			records[0][j] = j;
		}
		
		// 문자열 A의 i번째 문자가 끝문자일 때
		// 문자열 B의 j번째 문자가 끝 문자일 때의 편집거리를 구해가며
		// 해를 구해가며 기록해간다.
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				// 이때 records[i][j]는 
				// A[i] == B[j] 일치하면 이전 기록을 이어가고
				// +plus) 여기서 휘갈겨쓴 문자의 매칭 여부를 확인해준다.
				if(isMatch(A, B, i-1, j-1)) {
					records[i][j] = records[i-1][j-1];
					continue;
				}
				// A[i] != B[j] 라면 
				// 편집{추가, 삭제, 변환}을 수행하며 최소값을 기록해준다.
				records[i][j] = Math.min(records[i-1][j-1] + 1, Math.min(records[i][j-1] + 1, records[i-1][j] + 1));		
			}
		}
	
		return records[N][M];
	}
	
	static boolean isMatch(char[] A, char[] B, int i, int j) {
		boolean isSame = A[i] == B[j];
		boolean hasCommon = A[i] == 'i' && (B[j] == 'j' || B[j] == 'l');
		boolean hasCommon2 = (A[i] == 'v' && B[j] == 'w');
		
		return isSame || hasCommon || hasCommon2;
	}
	
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/week1/BJ_20524_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String input = br.readLine();
		String answer = br.readLine();
		
		System.out.println(solution(input, answer));
		
		br.close();
	}
}