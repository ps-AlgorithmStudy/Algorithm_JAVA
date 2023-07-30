package implementation;

import java.io.*;
import java.util.*;

public class week2_bj_16926 {
    static int N, M , r;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    static void rotate(){
        int min = (N > M) ? M/2 : N/2;
        int[][] arr_copy = new int[N][M];
        for (int i = 0; i < arr_copy.length; i++) {
            arr_copy[i]= arr[i].clone();
        }
        for (int i = 0; i < min; i++){
            int row = N - (2 * i) - 1;
            int col = M - (2 * i) - 1;
            for (int j=0;j<col;j++){
                arr_copy[i][i + j] = arr[i][i + j + 1];
                arr_copy[N - 1 - i][i + j + 1] = arr[N - 1 - i][i + j];
            }
            for (int j=0;j<row;j++){
                arr_copy[j + i + 1][i] = arr[j + i][i];
                arr_copy[i + j][M - 1 - i] = arr[i + j + 1][M - 1 - i];
            }
        }
        arr = arr_copy;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr= new int[N][M];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0;i<r;i++){
            rotate();
        }
        for(int[] arr1 : arr){
            for(int arr2: arr1){
                sb.append(arr2).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
