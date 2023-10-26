import java.util.Arrays;

public class Solution_prg_입국심사 {
    // 최저 처리 시간 ~ 최악의 처리 시간에 대해
    // 이분 탐색으로 모든 심사관이 처리 가능한 시간 중 최소 시간을 구한다.
    // 파라메트릭 서치
    public long solution(int n, int[] times) {
        Arrays.sort(times); // times를 오름차순 정렬

        long start = 0L; // inclusive
        long end = (long) Math.pow(10, 9) * (long) Math.pow(10, 9); // inclusive

        while (start < end) {
            long mid = (start + end) / 2;
            boolean cond = isPossible(mid, times, n);

            if (cond) { // 결정 조건 만족할 때 // 최소 결정 조건의 위치를 찾아야 한다.
                // System.out.println("무야호");
                end = mid;
            } else { // 만족하지 않을 때
                start = mid + 1;
            }
        }

        return start;
    }

    // 결정 조건의 참/불 여부를 확인
    boolean isPossible(long t, int[] times, int n) {
        long count = 0;

        // 각 심사관이 자신의 처리 속도로
        // 최대 얼마나 처리 가능한지 계산하고
        // 모든 심사관의 처리 가능량을 계산
        for (int time : times) {
            count += t / time;
        }
        return n <= count; // 모든 심사관이 처리 가능한 인원 수가 n 명 이상 이상일때 결정 조건이 참
    }
}
