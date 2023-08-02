package dp;

import java.io.*;
import java.util.*;

public class week1_bj_1695 {
    static int N;
    static int arr[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        for (int i=0;i<N;i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int front = 0;
        int end = N-1;
        while (front < end)
        {
            if (arr[front] != arr[end])
            {

            }

        }




        br.close();
    }
}


//1 2 3 4 2
//1 0 1
//
//2 4 3 2 1




