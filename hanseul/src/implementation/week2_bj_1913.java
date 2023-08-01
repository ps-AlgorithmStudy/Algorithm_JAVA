package implementation;

import java.io.*;
import java.util.*;

public class week2_bj_1913 {

    static int[][] num;
    static int N;
    static int direct;
    static int search_num;
    static int number;
    static int start_x;
    static int start_y;

    static int x, y;

    static void snail_direct(int n)
    {
        if (direct == 0)
        {
            for (int i = 0; i < n; i++) {
                num[--x][y] = ++number;
                if (number == search_num)
                {
                    start_x = x;
                    start_y = y;
                }
            }
        }
        else if (direct == 1){
            for (int i = 0; i < n; i++) {
                num[x][++y] = ++number;
                if (number == search_num)
                {
                    start_x = x;
                    start_y = y;
                }
            }

        }else if (direct == 2){
            for (int i = 0; i < n; i++) {
                num[++x][y] = ++number;
                if (number == search_num)
                {
                    start_x = x;
                    start_y = y;
                }
            }

        }else{
            for (int i = 0; i < n; i++) {
                num[x][--y] = ++number;
                if (number == search_num)
                {
                    start_x = x;
                    start_y = y;
                }
            }
        }
        direct = (direct + 1) % 4;
    }

    static void snail(int n){

        if (n == N) return ;

        snail_direct(n);
        snail_direct(n);

        if (n != N - 1)
            snail(n + 1);
        else
            snail_direct(n);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        search_num = Integer.parseInt(br.readLine());

        num = new int[N][N];

        if (N % 2 == 0){
            start_x = N / 2;
            start_y = start_x - 1;
        }else{
            start_x = start_y = N / 2;
        }
        num[start_x][start_y] = ++number;
        x = start_x; y = start_y;
        snail(1);


        for (int[] arr2 : num){
            for (int arr1 : arr2){
                sb.append(arr1 + " ");
            }
            sb.append("\n");
        }
        sb.append(++start_x + " " + ++start_y);
        System.out.println(sb.toString());

    }
}
