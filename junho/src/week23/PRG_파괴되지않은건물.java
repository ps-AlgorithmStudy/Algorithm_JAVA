package week23;

class PRG_파괴되지않은건물 {
    
    final int ATTACK = 1;
    final int RECOVER = 2;
    
    public int solution(int[][] board, int[][] skill) { 
        
        final int N = board.length, M = board[0].length;
        int[][] sums = new int[N + 1][M + 1]; // 1's based indexing
        
        for(int[] s : skill) {
            int degree = s[5];
            if(s[0] == ATTACK) {
                degree *= -1;
            }
            
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            
            sums[r1][c1] += degree;
            sums[r1][c2 + 1] -= degree;
            sums[r2 + 1][c1] -= degree;
            sums[r2 + 1][c2 + 1] += degree;
                        
        }
        
        // 누적합 계산 + 최종 배열 결과 계산
        int answer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(i>0) sums[i][j] += sums[i-1][j]; // 누적합 가로 계산
                if(j>0) sums[i][j] += sums[i][j-1]; // 누적합 세로 계산
                if(i>0 && j>0) sums[i][j] -= sums[i-1][j-1]; // 누적합 대각선 계산
                
                // 확정된 누적합 계산으로 원래 맵 계산
                board[i][j] += sums[i][j];
                // 계산 결과가 양수면 건물이 파괴되지 않음
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }

}