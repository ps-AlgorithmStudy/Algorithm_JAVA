package homework.M08.a0823.temp;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

import java.io.*;
import java.util.*;

public class Prim {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] minEdge = new int[N];
        for(int i=0;i<N;i++) {
            for (int j =0;j<N;j++) {
                g[i][j] = sc.nextInt();
            }
            minEdge[i] = Integer.MAX_VALUE;
        }

        int result = 0, cnt =0;
        minEdge[0] = 0;
        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;
            for(int j = 0 ; j < N ; j ++) {
                if (!v[j] && min > minEdge[j]) {
                    minVertex = j;
                    min = minEdge[j];
                }
            }
            v[minVertex] = true;
            result += min;
            if(cnt++==N-1) break;
            for(int j=0;j<N;j++) {
                if(!v[j] && g[minVertex][j]!=0 && minEdge[j] > g[minVertex][j]) {
                    minEdge[j] = g[minVertex][j];
                }
            }
        }
        System.out.println(result);
        sc.close();
    }
}
