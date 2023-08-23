package week4;
import java.io.*;
import java.util.*;

public class Main_17073 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        double cnt = 0.0;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] pair = new int[N+1];
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pair[Integer.parseInt(st.nextToken())] += 1;
            pair[Integer.parseInt(st.nextToken())] += 1;
        }
        for(int i = 2; i<=N; i++){
            if(pair[i]==1) cnt++;
        }
        System.out.println(W/cnt);
    }
}
