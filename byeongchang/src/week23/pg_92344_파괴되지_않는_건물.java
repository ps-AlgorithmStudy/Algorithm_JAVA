package week23;

//https://school.programmers.co.kr/learn/courses/30/lessons/92344
public class pg_92344_파괴되지_않는_건물 {

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int h = board.length;
        int w = board[0].length;
        int[][] sum = new int[h + 1][w + 1];   // 누적합 계산을 위한 배열
        for(int[] s: skill) {
            if(s[0]==1) s[5] *= -1;  // 파괴
            sum[s[1]][s[2]] += s[5];
            sum[s[1]][s[4]+1] -= s[5];   // 2차원 누적합은 세로 방향에 반대 값을 더해야 함
            sum[s[3]+1][s[2]] -= s[5];   // 2차원 누적합은 가로 방향에 반대 값을 더해야 함
            sum[s[3]+1][s[4]+1] += s[5];   // 2차원 누적합은 대각선 방향에 원래 값을 더해야 함(반대 값이 2번 더해지기 때문)
        }
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                // 누적합 계산은 가로, 세로 값을 더하고 대각선 값을 빼주면 확정됨
                if(i>0) sum[i][j] += sum[i-1][j];
                if(j>0) sum[i][j] += sum[i][j-1];
                if(i>0 && j>0) sum[i][j] -= sum[i-1][j-1];
                // 확정된 누적합 계산으로 원래 맵 계산
                board[i][j] += sum[i][j];
                // 계산 결과가 양수면 건물이 파괴되지 않음
                if(board[i][j] > 0) answer++;
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        TestCase[] tc = new TestCase[2];
        tc[0] = new TestCase(
                new int[][]{{5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}},
                new int[][]{{1,0,0,3,4,4}, {1,2,0,2,3,2}, {2,1,0,3,1,2}, {1,0,1,3,3,1}},
                10
        );
        tc[1] = new TestCase(
                new int[][]{{1,2,3}, {4,5,6}, {7,8,9}},
                new int[][]{{1,1,1,2,2,4}, {1,0,0,1,1,2}, {2,2,0,2,0,100}},
                6
        );

        pg_92344_파괴되지_않는_건물 sol = new pg_92344_파괴되지_않는_건물();
        for (TestCase t : tc) {
            int result = sol.solution(t.board, t.skill);
            System.out.println((result == t.result) + " sol: " + result + " ans: " + t.result);
        }
    }
    static class TestCase {
        int[][] board;
        int[][] skill;
        int result;

        public TestCase(int[][] board, int[][] skill, int result) {
            this.board = board;
            this.skill = skill;
            this.result = result;
        }
    }
}
