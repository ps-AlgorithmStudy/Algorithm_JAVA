package week9;

import java.util.*;

public class PRG_2023KAKAO_이모티콘할인행사 {
	final int[] RATE = { 40, 30, 20, 10 };

	// 이모티콘 별 다양한 할인율
	// 완전 탐색 하면서
	// 플러스 가입이 max 갱신하면서 판매금액도 갱신하는 알고리즘 구현
	public int[] solution(int[][] users, int[] emoticons) {

		// step1) 중복 순열 4^n
		final int L = emoticons.length;
		List<int[]> rates = new ArrayList<>();
		dupPerm(rates, new int[L], L, 0);

		int max_user = 0, max_amount = 0;

		// O(4^7 * 100 * 7)
		for (int[] rate : rates) {
			// System.out.println(">> 현재 할인율 " + Arrays.toString(rate));
			int temp_user = 0, temp_amount = 0; // int 걱정 ㄴㄴ

			// 이모티콘 만 구매할 건가?
			// 플러스에 가입할 건가?
			for (int[] user : users) {
				int temp = 0;
				boolean hasJoined = false;

				for (int i = 0; i < rate.length; i++) {
					// i번 이모티콘의 할인율이 구매 의사가 있는 할인율 이상 할인하나?
					if (user[0] <= rate[i]) {
						temp += emoticons[i] * (100 - rate[i]) * 0.01;
						// System.out.println(String.format("%d 할인 기대 유저의 temp: %d\n", user[0], temp));
						if (temp >= user[1]) {
							temp_user++;
							// System.out.println("플러스 가입");
							hasJoined = true;
							break;
						}
					}
				}

				if (!hasJoined) {
					temp_amount += temp;
				}
			}

			// 갱신 작업
			if (max_user < temp_user) {
				max_user = temp_user;
				max_amount = temp_amount;
			} else if (max_user == temp_user) {
				max_amount = Math.max(max_amount, temp_amount);
			}

			// System.out.println(">>> max_user : " + max_user + ", max_amount : " + max_amount);
		}

		return new int[] { max_user, max_amount };
	}

	// copy 주의
	public void dupPerm(List<int[]> rates, int[] ans, int n, int r) {
		if (n == r) {
			int[] temp = Arrays.copyOf(ans, n);
			rates.add(temp);
			return;
		}

		for (int i = 0; i < RATE.length; i++) {
			ans[r] = RATE[i];
			dupPerm(rates, ans, n, r + 1);
		}
	}
}
