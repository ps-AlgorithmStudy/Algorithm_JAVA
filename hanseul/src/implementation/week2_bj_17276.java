package implementation;

import java.io.*;
import java.util.*;

public class week2_bj_17276 {

    static StringBuilder sb = new StringBuilder();
    static int testCase;
    static int[][] arr;
    static int N;
    static int degree;

    static void degreeHalf(){
        int[] line1 = new int[N];
        int[] line2 = new int[N];
        int[] line3 = new int[N];
        int[] line4 = new int[N];

        for (int i=0;i<N;i++)
        {
            line1[i] = arr[i][i];
            line2[i] = arr[N/2][i];
            line3[i] = arr[N - 1 - i][i];
            line4[i] = arr[N - 1 - i][N/2];
        }
        for (int i=0;i<N;i++)
        {
            arr[i][i] = line2[i];
            arr[N/2][i] = line3[i];
            arr[N - 1 - i][i] = line4[i];
            arr[i][N/2] = line1[i];
        }
    }

    static void degreeHalf_(){
        int[] line1 = new int[N];
        int[] line2 = new int[N];
        int[] line3 = new int[N];
        int[] line4 = new int[N];

        for (int i=0;i<N;i++)
        {
            line1[i] = arr[i][i];
            line2[i] = arr[i][N/2];
            line3[i] = arr[i][N - 1 - i];
            line4[i] = arr[N/2][i];
        }
        for (int i=0;i<N;i++)
        {
            arr[i][i] = line2[i];
            arr[i][N/2] = line3[i];
            arr[N - 1 - i][i] = line4[i];
            arr[N/2][i] = line1[i];
        }
    }

    static void rightAngle()
    {
        int tmp[][] = new int[N][N];

        for (int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
                tmp[i][j] = arr[N - j - 1][i];

        }
        arr = tmp;
    }

    static void rightAngle_()
    {
        int tmp[][] = new int[N][N];

        for (int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
                tmp[i][j] = arr[j][N - i - 1];
        }
        arr = tmp;
    }

    static void reverse()
    {
        int tmp[][] = new int[N][N];

        for (int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
            {
                tmp[i][j] = arr[N - i - 1][j];
            }
        }
        arr = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int tc=0;tc<testCase;tc++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            degree = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            for (int i=0;i<N;i++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j=0;j<N;j++)
                    arr[i][j] = Integer.parseInt(st.nextToken());
            }

            if (degree == 45 || degree == -315){
                degreeHalf();
            } else if (degree == 90 || degree == -270) {
                degreeHalf();
                degreeHalf();
            } else if (degree == 135 || degree == -225) {
                degreeHalf();
                degreeHalf();
                degreeHalf();
            } else if (degree == 180 || degree == -180) {
                degreeHalf();
                degreeHalf();
                degreeHalf();
                degreeHalf();
            } else if (degree == 225 || degree == -135) {
                degreeHalf_();
                degreeHalf_();
                degreeHalf_();
            } else if (degree == -90 || degree == 270) {
                degreeHalf_();
                degreeHalf_();
            } else if (degree == -45 || degree == 315) {
                degreeHalf_();
            }

            for(int[] arr1 : arr)
            {
                for(int arr2 : arr1){
                    sb.append(arr2).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
