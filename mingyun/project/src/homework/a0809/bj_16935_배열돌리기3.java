package src.homework.a0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj_16935_배열돌리기3 {

    static int[][] first(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];
        for (int i= arr.length-1;i>=0;i--) {
            System.arraycopy(arr[i], 0, result[arr.length - i - 1], 0, arr[0].length);
        }
        return result;
    }

    static int[][] second(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];
        for (int i=0; i<arr.length;i++) {
            for (int j=0; j<arr[0].length;j++) {
                result[i][j] = arr[i][arr[0].length-j-1];
            }
        }
        return result;
    }

    static int[][] third(int[][] arr) {
        int[][] result = new int[arr[0].length][arr.length];
        for (int i=0; i<arr[0].length; i++) {
            for (int j=0; j<arr.length;j++) {
                result[i][arr.length-1-j] = arr[j][i];
            }
        }
        return result;
    }

    static int[][] fourth(int[][] arr) {
        int[][] result = new int[arr[0].length][arr.length];
        for (int i=0; i<arr[0].length; i++) {
            for (int j=0; j<arr.length;j++) {
                result[i][j] = arr[j][arr[0].length-1-i];
            }
        }
        return result;
    }

    static int[][][] split(int[][] arr) {
        int width = arr.length/2;
        int height = arr[0].length/2;
        int[][][] result = new int[4][width][height];
        for (int x=0;x<2;x++) {
            int hg = height * x;
            for (int i=0; i<width; i++) {
                for (int j=hg; j<height+hg; j++) {
                    result[x][i][j-hg] = arr[i][j];
                    result[2+x][i][j-hg] = arr[width+i][j];
                }
            }
        }
        return result;
    }

    public static int[][] merge(int[][][] piece) {
        int height = piece[0].length;
        int width = piece[0][0].length;
        int[][] merged = new int[height*2][width*2];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                merged[i][j] = piece[0][i][j];
                merged[i][j + width] = piece[1][i][j];
                merged[i + height][j] = piece[2][i][j];
                merged[i + height][j + width] = piece[3][i][j];
            }
        }
        return merged;
    }

    static int[][] fifth(int[][] arr) {
        int[][][] piece = split(arr);
        int[][] temp = piece[1];
        piece[1] = piece[0];
        piece[0] = piece[2];
        piece[2] = piece[3];
        piece[3] = temp;
        return merge(piece);
    }

    static int[][] sixth(int[][] arr) {
        int[][][] piece = split(arr);
        int[][] temp = piece[2];
        piece[2] = piece[0];
        piece[0] = piece[1];
        piece[1] = piece[3];
        piece[3] = temp;
        return merge(piece);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0809/res/input_16935.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<k;i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x==1) arr = first(arr);
            else if (x==2) arr = second(arr);
            else if (x==3) arr = third(arr);
            else if (x==4) arr = fourth(arr);
            else if (x==5) arr = fifth(arr);
            else if (x==6) arr = sixth(arr);
        }

        for (int i=0;i< arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
    }
}
