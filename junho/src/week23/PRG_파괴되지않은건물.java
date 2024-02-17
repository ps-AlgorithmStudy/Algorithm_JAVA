class PRG_파괴되지않은건물 {
    
    final int ATTACK = 1;
    final int RECOVER = 2;
    
    static class Record {
        int to;
        int sum;
        
        Record(int to, int sum) {
            this.to = to;
            this.sum = sum;
        }
        
        Record(int to) {
            this(to, 0);
        }
    }
    
    public int solution(int[][] board, int[][] skill) { 
        
        final int N = board.length, M = board[0].length;
        int[] records = new int[N * M];
        
        for(int[] s : skill) {
            int degree = s[5];
            if(s[0] == ATTACK) {
                degree *= -1;
            }
            
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            
            for(int i = r1; i <= r2; i++) {
                for(int j = c1; j <= c2; j++) {
                    board[i][j] += degree;
                }
            }
                        
        }
        
        int answer = N * M;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] < 1) {
                    answer--;
                }
            }
        }
        
        return answer;
    }

}