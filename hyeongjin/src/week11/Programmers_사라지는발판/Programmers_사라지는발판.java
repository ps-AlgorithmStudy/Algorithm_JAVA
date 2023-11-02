package Programmers_사라지는발판;

class Solution {
    public static int R, C; // 보드의 크기
    public static int[] di = {-1, 1, 0, 0};
    public static int[] dj = { 0, 0,-1, 1};

    // 결과 정보를 담을 클래스
    public static class Result{
        boolean win;
        int cnt;

        public Result(boolean win, int cnt){
            this.win = win;
            this.cnt = cnt;
        }
    }
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        Result answer = solve(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, 0);
        return answer.cnt;
    }

    public static Result solve(int[][] board, int ai, int aj, int bi, int bj, int aDepth, int bDepth){
        int winner = Integer.MAX_VALUE;  // 현대 플레이어가 이기는 플레이어일 때 최소 턴
        int loser  = 0;					 // 현재 플레이어가 지는 플레이어일 때 최대 턴
        boolean canMove = false;		 // 해당 차례가 움직일 수 있는지?
        Result res;				         // 다음 턴의 결과( 승패여부, 턴 수 )
        
        // A턴
        if(aDepth == bDepth){
            // A의 위치를 체크
            if(board[ai][aj] == 0){
                return new Result(false,aDepth+bDepth);
            }
            
            // DFS탐색
            board[ai][aj] = 0;
            for (int d = 0; d < 4; d++) {
                int nai = ai + di[d];
                int naj = aj + dj[d];

                if(isInRange(nai, naj) && board[nai][naj] == 1){
                    // 움직일 곳이 있다
                	canMove = true;
                    
                    // A다음 턴 (B의 결과)
                    res = solve(board, nai, naj, bi, bj, aDepth+1,bDepth);
                    
                    // B가 이긴다 = A는 진다
                    // A가 지는 경우중 최댓값으로 갱신해준다
                    if(res.win) loser = Math.max(loser, res.cnt);
                    	
                    // B가 진다 = A가 이긴다
                    // A가 이기는 경우중 최솟값으로 갱신해준다
                    else  winner = Math.min(winner, res.cnt);
                }
            }
            board[ai][aj] = 1;
        }
        
        // B턴
        else if (aDepth > bDepth) {
        	// B의 위치를 체크
            if(board[bi][bj] == 0){
                return new Result(false,aDepth+bDepth);
            }
            
            
            // DFS탐색
            board[bi][bj] = 0;
            for (int d = 0; d < 4; d++) {
                int nbi = bi + di[d];
                int nbj = bj + dj[d];
                if(isInRange(nbi, nbj) && board[nbi][nbj] == 1){
                	// 움직일 곳이 있다
                	canMove = true;
                    
                    // A다음 턴 (B의 결과)
                    res = solve(board, ai, aj, nbi, nbj, aDepth, bDepth+1);
                    
                    // A가 이긴다 = B는 진다
                    // B가 지는 경우중 최댓값으로 갱신해준다
                    if(res.win){
                        loser = Math.max(loser, res.cnt);
                        
                    // B가 이긴다 = A는 진다
                    // A가 이기는 경우중 최솟값으로 갱신해준다
                    }else{
                        winner = Math.min(winner, res.cnt);
                    }
                }
            }
            board[bi][bj] = 1;
        }
        
        if(!canMove){
        	// 현재 플레이어가 움직일 수 없는경우
        	// => 현재 플레이어가 패배
            return new Result(false,aDepth+bDepth);
        }else if(winner != Integer.MAX_VALUE){
        	// 탐색 과정에서 이길 경우의 수가 갱신되었다
        	// => 현재 플레이어가 승리
            return new Result(true, winner);
        }else{
        	// 탐색 과정에서 이길 경우의 수가 갱신되지 않았다 (=이길경우의 수가없다)
        	// => 현재 플레이어는 패배
            return new Result(false, loser);
        }
    }
    
    public static boolean isInRange(int i, int j){
        return (0 <= i && i < R && 0 <= j && j < C);
    }
}

// Test Code
public class Programmers_사라지는발판 {
	public static void main(String[] args) {
		
		int[][] b1 = {{1,1,1},{1,1,1},{1,1,1}};
		int[][] b2 = {{1,1,1},{1,0,1},{1,1,1}};
		int[][] b3 = {{1,1,1,1,1}};
		int[][] b4 = {{1}};
		
		int[] aloc1 = {1,0};
		int[] aloc2 = {1,0};
		int[] aloc3 = {0,0};
		int[] aloc4 = {0,0};
		
		int[] bloc1 = {1,2};
		int[] bloc2 = {1,2};
		int[] bloc3 = {0,4};
		int[] bloc4 = {0,0};
		
 		Solution s = new Solution();
 		System.out.println(s.solution(b1, aloc1, bloc1));
 		System.out.println(s.solution(b2, aloc2, bloc2));
 		System.out.println(s.solution(b3, aloc3, bloc3));
 		System.out.println(s.solution(b4, aloc4, bloc4));
	}
}