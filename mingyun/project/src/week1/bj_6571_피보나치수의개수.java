package week1;

import java.io.*;
import java.math.*;
import java.util.*;

public class bj_6571_피보나치수의개수 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_6571.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger[] fib = new BigInteger[1000];
        fib[0] = new BigInteger(new String("1"));
        fib[1] =  new BigInteger(new String("1"));

        for (int i=2;i<1000;i++) {
            fib[i] = fib[i-1].add(fib[i-2]);
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger n = new BigInteger(st.nextToken());
            BigInteger m = new BigInteger(st.nextToken());
            if (n.compareTo(new BigInteger("0")) == 0 && m.compareTo(new BigInteger("0")) == 0) {
                break;
            }
            else {
                int cnt = 0;
                for (int i=0;i<1000;i++) {
                    if((fib[i].compareTo(n)==1||fib[i].compareTo(n)==0) && (fib[i].compareTo(m)==-1||fib[i].compareTo(m)==0)) {
                        cnt++;
                    }
                }
                System.out.println(cnt);
            }
        }
    }
}
