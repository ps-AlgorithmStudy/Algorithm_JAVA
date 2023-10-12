package week8;
import java.io.*;
import java.util.*;
public class Main_1629_곱셈 {
    static int C;
    static long mod(long a){
        return a%C;
    }
    static long solve(long A, long B){
        // 지수가 1일 때 종료
        if(B == 1) return mod(A);

        // if B is even
        // (A ^ B) % C  =>
        // {(A  ^ B/2 ) % C * (A  ^ B/2 ) % C } % C
        long tmp = solve(A, B/2);

        // if B is odd
        // (A ^ B) % C  =>
        // ((A  ^ B/2 ) % C * (A  ^ B/2 ) % C * A % C) % C
        if(B % 2 == 1) return mod(mod(tmp * tmp)*mod(A));
        return mod(tmp*tmp);
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(solve(A,B));

    }
}
