package week5;

import java.io.*;
import java.util.*;

public class Main_bj_2212_센서 {
	static int N, K;
	static final int L = 10_000;
	
	// O(N * log N)
	static void solution(int[] arr) {
		
		// 입력에 대해 정렬
		Arrays.sort(arr);
		
		// 인접한 센서간 차이를 구하고
		int[] diff = new int[arr.length - 1];
		for(int i = 0; i < arr.length - 1; i++) {
			diff[i] = arr[i + 1] - arr[i];
		}
		
		// 센서간 차이에 대해 오름차순 정렬
		Arrays.sort(diff);
		
		// 끝에서 부터
		int last = diff.length - 1;
		
		// K하나가 왼쪽 오른쪽을 구분하니까
		// K > 1일 때까지 반복문 수행
		while(K-- > 1) {
			diff[last--] = 0;
		}
		
		// System.out.println(Arrays.toString(diff));
		
		// 전체 합 반환
		int sum = 0;
		for(int d : diff) {
			sum += d;
		}
		
		System.out.println(sum);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		// 해당 인덱스 위치에 센서가 있나?
		// 다른 집중국이 사용하고 있나?
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 집중국 수가 많으면
		// N개의 센서가  적어도 하나의 집중국과 관하여
		// 어떤 집중국엔 센서가 연결이 안되는 경우가 있을 수 있다.
		if(K >= N) {
			System.out.println(0);
		} else {
			solution(arr);
		}	
	}
}
