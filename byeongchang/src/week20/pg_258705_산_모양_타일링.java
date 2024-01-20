package week20;

// https://school.programmers.co.kr/learn/courses/30/lessons/258705
public class pg_258705_산_모양_타일링 {
    static final int TRAPEZOID = 0;    // 사다리꼴
    static final int MOD = 10007;    // 나누기 연산을 위한 값

    static final int TRIANGLE_TRAPEZOID = 2;    // 이전 도형의 경우의 수의 마지막이 삼각형이고, 다음 도형이 사다리꼴인 경우 이전 삼각형 경우의 수의 2배로 계산한 값이 현재 경우의 수의 마지막이 삼각형인 경우의 수
    static final int RHOMBUS_TRAPEZOID = 1;    // 이전 도형의 경우의 수의 마지막이 마름모이고, 다음 도형이 사다리꼴인 경우 이전 마름모 경우의 수의 1배로 계산한 값이 현재 경우의 수의 마지막이 삼각형인 경우의 수
    static final int TRIANGLE_TRIANGLE = 3;    // 이전 도형의 경우의 수의 마지막이 삼각형이고, 다음 도형이 삼각형인 경우 이전 삼각형 경우의 수의 3배로 계산한 값이 현재 경우의 수의 마지막이 삼각형인 경우의 수
    static final int RHOMBUS_TRIANGLE = 2;    // 이전 도형의 경우의 수의 마지막이 마름모이고, 다음 도형이 삼각형인 경우 이전 마름모 경우의 수의 2배로 계산한 값이 현재 경우의 수의 마지막이 삼각형인 경우의 수


    public int solution(int n, int[] tops) {
        int[][] cases = new int[n][2];   // 메모이제이션 활용을 위한 이차원 배열, [i][0] : 경우의 수의 마지막이 삼각형인 경우, [i][1] : 경우의 수의 마지막이 마름모인 경우

        // 초기값 생성
        if (tops[0] == TRAPEZOID) cases[0][0] = 2;
        else cases[0][0] = 3;
        cases[0][1] = 1;


        for (int i=1; i<n; i++) {
            if(tops[i] == TRAPEZOID) {
                cases[i][0] = Math.addExact(Math.multiplyExact(cases[i-1][0], TRIANGLE_TRAPEZOID) % MOD, Math.multiplyExact(cases[i-1][1], RHOMBUS_TRAPEZOID) % MOD) % MOD;
                cases[i][1] = Math.addExact(cases[i-1][0], cases[i-1][1]) % MOD;    // 현재 경우의 수의 마지막이 마름모인 경우
            }
            else {
                cases[i][0] = Math.addExact(Math.multiplyExact(cases[i-1][0], TRIANGLE_TRIANGLE) % MOD, Math.multiplyExact(cases[i-1][1], RHOMBUS_TRIANGLE) % MOD) % MOD;
                cases[i][1] = Math.addExact(cases[i-1][0], cases[i-1][1]) % MOD;

            }
        }

        return (cases[n-1][0] + cases[n-1][1]) % MOD;
    }


    public static void main(String[] args) {
        TestCase[] tc = new TestCase[4];
        tc[0] = new TestCase(2, new int[]{0, 0}, 8);
        tc[1] = new TestCase(2, new int[]{0, 1}, 11);
        tc[2] = new TestCase(4, new int[]{1, 1, 0, 1}, 149);
        tc[3] = new TestCase(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 7704);

        pg_258705_산_모양_타일링 sol = new pg_258705_산_모양_타일링();
        for (TestCase t : tc) {
            int answer = sol.solution(t.n, t.tops);
            System.out.println((answer == t.result) + " sol : " + answer + ", ans : " + t.result);
        }



    }
    static class TestCase {
        int n;
        int[] tops;
        int result;

        public TestCase(int n, int[] tops, int result) {
            this.n = n;
            this.tops = tops;
            this.result = result;
        }

    }
}
