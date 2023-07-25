package week1;

public class PRG_LV3_42898_등굣길 {

	// -1 : puddle
    // 0 : possible
    // other : short distance
    int[][] mem; 
    
    static int M;
    static int N;
    final int DIV = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        M = m;
        N = n;
        mem = new int[M + 1][N + 1];
        
        // 물웅덩이 체크
        for(int[] puddle : puddles) {
            int x = puddle[0], y = puddle[1];
            mem[x][y] = -1;
        }    
        
        // 재귀함수 호출 
        // 최적화 : 메모제이션DP를 이용
        return rec(1, 1);
    }
    
    // row, col
    int rec(int x, int y) {
        
        // base case : 유효한 좌표범위인지 확인
        if(isOutbound(x, y) || mem[x][y] == -1) {
            return 0;
        }
        
        // base case : 이미 방문한 경로인지 부터 확인
        if(mem[x][y] > 0) {
            return mem[x][y];
        }
        
        // base case : 종료 조건
        if(x == M && y == N) {
        	// 경로가 존재한다는 의미; depth 따로 필요 없을 듯
        	// 경로의 개수를 반환하여 누적
            return 1; 
        }
        
        // 점화식
        // 현재 위치에서 종료 지점까지 오른쪽, 아래 쪽 이동할 때
        // 오른쪽으로 이동했을 때 최단 경로 개수
        // 아래쪽으로 이동했을 때 최단 경로 개수
        mem[x][y] = (rec(x+1, y) + rec(x, y + 1)) % DIV;
        return mem[x][y];
        
    }
    
    // 유효 범위 [1, M], [1, N];
    boolean isOutbound(int x, int y) {
        return (x < 1 || x > M) || (y < 1 || y > N);
    }

    public static void main(String[] args) {
    	PRG_LV3_42898_등굣길 test = new PRG_LV3_42898_등굣길();
    	int[][] inputs = {
    			{4, 3}
    	};
    	
    	int[][][] inputPuddles = {
    			{{2, 2}}	
    	};
    	
    	
    	for(int tc = 0; tc < inputs.length; tc++) {
    		int m = inputs[tc][0];
    		int n = inputs[tc][1];
    		
    		int[][] puddles = inputPuddles[tc];
    		System.out.println(test.solution(m, n, puddles));
    	}
    	
	}
}
