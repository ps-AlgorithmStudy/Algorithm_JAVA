package week2;

import java.io.*;
import java.util.StringTokenizer;

public class q20207 {
    static int[] arr = new int[367];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        int cnt = 0;
        int res = 0;
        int start,end = 0;
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            for (int j = start; j <=end; j++) arr[j] += 1;
        }

        for (int i = 1; i < arr.length; i++) {
            if(arr[i]==0) {
                res += cnt*max;
                cnt =0;
                max =0;
                continue;
            }
            cnt ++;
            max = Math.max(max, arr[i]);
        }
        System.out.println(res);
    }
}