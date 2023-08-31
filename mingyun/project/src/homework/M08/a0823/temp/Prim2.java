package homework.M08.a0823.temp;

import java.util.Scanner;

public class Prim2 {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] minEdge = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g[i][j] = sc.nextInt();
            }
            minEdge[i] = Integer.MAX_VALUE;
        }

        int result=0;
        int cnt =0;
        minEdge[0] = 0;

        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!v[j] && min > minEdge[j]) {
                    min = minEdge[j];
                    minVertex = j;
                }
            }
            v[minVertex] = true;
            result += min;

        }
    }
}