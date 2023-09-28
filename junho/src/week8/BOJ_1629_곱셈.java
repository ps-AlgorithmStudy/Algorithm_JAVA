import java.io.*;
import java.util.*;

public class BOJ_1629_곱셈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// 모듈러 성질을 이용한다.
		// int result = (A % C * B % C) % C;
		long result = rec(A, B, C);
		
		System.out.println(result);
		br.close();
	}

	static long rec(int A, int pow, int C) {
		if (pow == 1) {
			return A % C;
		}

		long temp = rec(A, pow / 2, C);
		
		// 홀수인 경우
		if(pow % 2 == 1) {
			// 예를 들어 a^3 이라 하면 temp는 a^1의 결과를 구했을 것
			// return (temp * rec(A, 2, C)) % C;
			return (temp * temp % C) * A % C;
		}
		// return (rec(A, pow / 2, C) * rec(A, pow / 2, C)) % C; // 지수 법칙 이용
		return (temp * temp) % C;
	}
}