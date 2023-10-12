package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1629_곱셈 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/bj_1269.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        //for(int tc=1; tc<=10; tc++)
        System.out.println(solution(br,st));
        br.close();
    }
    public static long solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        return mod(A,B,C);
    }
    
    /* 
    재귀를 활용한 분할 정복
    b가 짝수인 경우 : b/2로 재귀 실행한 값을 제곱해 나머지 연산
    b가 홀수인 경우 : b = 2*(b/2)+1, b/2로 재귀 실행한 값을 재곱해 나머지 연산한 값에 a의 나머지 연산의 곱 */
    public static long mod(int a, int b, int c){
        if(b==1) return a%c;
        long tmp = mod(a,b/2,c);
        if(b%2==0){
            return tmp*tmp%c;
        }
        else {
            return tmp*tmp%c*a%c;
        }
    }
}
