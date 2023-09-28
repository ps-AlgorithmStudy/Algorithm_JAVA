package math;

import java.io.*;
import java.util.*;

public class week8_bj_1629_곱셈 {
    static long recurMuliply(long a, int b, int c){
        if (b == 1) return a % c;
        long mul = (recurMuliply(a, b / 2, c) % c);
        mul = (mul * mul) % c;
        if (b % 2 == 0)
            return (mul);
        else
            return (mul * a) % c;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(recurMuliply(A, B ,C) % C);
    }

}
