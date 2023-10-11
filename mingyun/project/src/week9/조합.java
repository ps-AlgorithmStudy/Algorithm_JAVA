package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 조합 {
    static long n, r;
    static long divider = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            long v1 = 1;
            long v2 = 1;
            long t = Math.min(r, n- r);
            for(int i=0; i<t; i++) {
                v1 = v1*(n-i)% divider;
                v2 = v2*(t-i)% divider;
            }
            long ans = (v1% divider *div(v2, divider -2)% divider)% divider;

            System.out.println("#"+tc+" "+ans);
        }
    }

    static long div(long v1, long v2) {
        if(v2==1)
            return v1;
        long tmp = div(v1, v2/2);
        if(v2%2 == 1)
            return tmp*tmp% divider *v1% divider;
        else
            return tmp*tmp% divider;
    }
}