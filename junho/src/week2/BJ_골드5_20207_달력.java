package week2;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_골드5_20207_달력 {
	static int[] records;
	static final int L = 365;

	// O(365 * 1000) 연산 가능
	static void mark(int start, int to) {
		for (int i = start; i <= to; i++) {
			records[i]++;
		}
	}

	// O(365);
	static int solution() {
		int total = 0;
		int leftIdx = 0, rightIdx = 0;
		int prev = 0, height = 0;
		for (int i = 1; i < L + 2; i++) {

			if (prev == 0 && records[i] != 0) {
				leftIdx = i;
			}

			height = Math.max(height, records[i]);

			if (prev != 0 && records[i] == 0) {
				rightIdx = i - 1;
				total += (rightIdx - leftIdx + 1) * height;
				height = 0;
			}
			prev = records[i];
		}
		return total;
	}

	// 최종 연산 횟수 
	// O(L * N + L) = O(L*N); L = 365, N = 1000
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStrea(""));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		records = new int[L + 2]; // 1'based indexing, [1, 365] 범위에서 0, 366에 접근하기 위해

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
			mark(start, to);
		}
		sb.append(solution()).append("\n");
		System.out.print(sb);
	}
}
