package week31;

import java.io.*;
import java.util.*;

public class bj_2143_두_배열의_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		System.out.println(solution(br, st));
		br.close();

	}

	public static long solution(BufferedReader br, StringTokenizer st) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) B[i] = Integer.parseInt(st.nextToken());

		// A의 부분합
		int[] sumA = new int[n * (n + 1) / 2];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += A[j];   // 누적합 활용
				sumA[idx++] = sum;
			}
		}

		// B의 부분합
		int[] sumB = new int[m * (m + 1) / 2];
		idx = 0;
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				sum += B[j];   // 누적합 활용
				sumB[idx++] = sum;
			}
		}

		// 탐색을 위한 정렬
		Arrays.sort(sumA);
		Arrays.sort(sumB);

		return twoPointer(T, sumA, sumB);
	}

	static long twoPointer(int T, int[] sumA, int [] sumB) {
		long result = 0;
		int left = 0;
		int right = sumB.length - 1;
		int lenA = sumA.length;
		while(left<lenA && right>=0) {
			int curA = sumA[left];
			int curB = sumB[right];
			int sum = curA + curB;
			if(sum == T) {
				int cntA = 0;    // curA와 같은 원소의 수
				int cntB = 0;    // curB와 같은 원소의 수
				while (left<lenA && curA==sumA[left]) {
					cntA++;
					left++;
				}
				while (right>=0 && curB==sumB[right]) {
					cntB++;
					right--;
				}
				 result += (long)cntA * cntB;    // curA, curB와 같은 원소의 개수를 곱한 값이 경우의 수
			}
			else if(sum <T){
				left++;
			}
			else {
				right--;
			}
		}
		return result;
	}
}
