package homework.M08.a0803;

import java.util.Scanner;

public class bj_2023_신기한소수 {

    static int n;
    static boolean isPrime(int num) {
        for (int i=2;i<=Math.sqrt(num);i++) {
            if (num%i==0) return false;
        }
        return true;
    }
    static void uniquePrim(int dept, int num, boolean[] prim, StringBuilder sb) {
        if (dept==n-1) {
            for (int i=1;i<=9;i++) {
                int t = num * 10 + i;
                if (isPrime(t)) {
                    sb.append(t).append("\n");
                }
            }
        }
        else {
            for (int i=1;i<=9;i++) {
                int t = num * 10 + i;
                if (!prim[t]) {
                    uniquePrim(dept+1, t,prim,sb);
                }
            }
        }
    }

    public static void main(String[] args) {
        boolean[] prim = new boolean[10000000];

        prim[0] = true;
        prim[1] = true;
        for (int i=2;i<=Math.sqrt(9999999)+1;i++) {
            if (!prim[i]) {
                for (int j=i*2;j<=9999999;j+=i) {
                    prim[j] = true;
                }
            }
        }

        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i=2;i<=9;i++) {
            if (n==1) {
                if (!prim[i]) {
                    sb.append(i).append("\n");
                }
            }
            else if (!prim[i]) uniquePrim(1,i,prim, sb);
        }

        System.out.print(sb);
    }
}
