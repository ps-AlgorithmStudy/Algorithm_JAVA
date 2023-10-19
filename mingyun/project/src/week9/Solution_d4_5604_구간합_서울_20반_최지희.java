package week9;

import java.util.*;
import java.io.*;

public class Solution_d4_5604_구간합_서울_20반_최지희 {

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            st=new StringTokenizer(br.readLine());
            long a=Long.parseLong(st.nextToken());
            long b=Long.parseLong(st.nextToken());
            long ans=findSum(b,findDigit(b))-findSum(a-1,findDigit(a-1));
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
    private static long findDigit(long n){
        long v=1L;
        for(int i=1;i<16;i++) {
            if(n/(v*10)>0) v*=10;
            else break;
        }
        return v;
    }
    private static long findSum(long n, long v) {
        if(v==0) return 0;
        long p=n/v;
        long r=n%v;
        long res=0L;
        for(long i=0;i<p;i++) res+=v*i;
        res+=findSum(v-1,v/10)*p;
        res+=findSum(r,v/10);
        res+=p*(r+1);
        return res>0?res:0;
    }
}