import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class q20542 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		String NM = br.readLine();
		st = new StringTokenizer(NM);
		
		// n:답안 m:정답
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String input = br.readLine();
		String answer = br.readLine();
		
		int[][] dp = new int[n+1][m+1];
		dp[0][0] = 0;

		for (int i = 0; i < n+1; i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i < m+1; i++) {
			dp[0][i] = i;
		}
		// 편집거리 알고리즘 dp[i][j]
		// 1. 글자가 다를때
		// dp[i][j] = min(dp[i-1][j]-1,dp[i][j-1]-1)
		// 2. 글자가 같을 때 : dp[i][j] = dp[i-1][j-1] 1의 경우를 2번
		
		for(int i = 1; i<m+1; i++) {
			for(int j = 1; j<n+1;j++) {
				if(checkSpell(input.charAt(j-1),answer.charAt(i-1))) {
					dp[j][i] = dp[j-1][i-1];
				}else {
					dp[j][i] = Math.min(Math.min(dp[j-1][i]+1,dp[j][i-1]+1), dp[j-1][i-1]+1);
				}
			}
		}
		
		
		System.out.println(dp[n][m]);
//		for(int[] d : dp) {
//			System.out.println(Arrays.toString(d));
//		}
//		
	}
	static boolean checkSpell(char N, char M){
		if(N==M) return true;
		else if(N=='i'&&(M=='j'||M=='l')) return true;
		else if(N=='v'&&(M=='v'||M=='w')) return true;
		else return false;
	}

}