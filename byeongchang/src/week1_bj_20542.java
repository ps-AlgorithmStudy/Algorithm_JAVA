import java.util.*;
import java.io.*;

public class week1_bj_20542 {
    
    public static void main(String[] args) throws Exception {
    	/*입력파일 설정*/
        //System.setIn(new FileInputStream("res\\bj_20542.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        /* swea 방식으로 여러 테케 돌릴 때 */
        //TestCases(br, st);
        
        //st = new StringTokenizer(br.readLine());
        
        /* 단일 테케 돌릴 때 */
        solution(br, st);
        
    }

    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 승연 답안 길이
        int M = Integer.parseInt(st.nextToken()); // 정답 길이
        int result = 0;  // 변경 횟수
        String SY = br.readLine();  // 승연 답안
        String answer = br.readLine();  // 답안
        result = editDistance(SY.toCharArray(), answer.toCharArray());
        System.out.println(result);
    }
    
    public static int editDistance(char[] sy, char[] ans) {
    	int[][] ed = new int[sy.length+1][ans.length+1];
    	for(int i=0;i<=sy.length;i++) ed[i][0] = i;
    	for(int i=0;i<=ans.length;i++) ed[0][i] = i;
    	for(int i=0;i<sy.length;i++) {
    		for(int j=0;j<ans.length;j++) {
    			if(checkEqual(sy[i], ans[j])) ed[i+1][j+1] = ed[i][j];
    			else ed[i+1][j+1] = Math.min(Math.min(ed[i][j+1], ed[i+1][j]), ed[i][j])+1;
    		}
    	}
    	return ed[sy.length][ans.length];
    }
    // i == i,j,l   v == v,w
    public static boolean checkEqual(char s, char a) {
    	if (s == a) return true;
    	else if (s == 'i' && (a == 'j' || a == 'l')) return true;
    	else if (s == 'v' && a == 'w') return true;
    	else return false;
    }
    
    public static void TestCases(BufferedReader br, StringTokenizer st) throws Exception {
    	st = new StringTokenizer(br.readLine());
    	int TestCase = Integer.parseInt(st.nextToken());
    	for (int i = 1; i <= TestCase; i++) {
    		System.out.print("Case #" + i + ": ");
    		week1_bj_20542.solution(br, st);
    	}
    }
}

