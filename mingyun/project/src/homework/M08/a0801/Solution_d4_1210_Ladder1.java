package homework.M08.a0801;

import java.io.*;
import java.util.*;

public class Solution_d4_1210_Ladder1 {


    public static boolean inRange(int i, int j) {
        return i>=0&&i<100&&j>=0&&j<100;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_1210.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int[] di = {0,0,-1};
        int[] dj = {1,-1,0};

        for (int T=1;T<=10;T++) {
            int TC = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            int[][] v = new int[100][100];
            int si = 99;
            int sj = 0;
            for (int i=0;i<100;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<100;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int j=0;j<100;j++) {
                if (arr[99][j]==2) {
                    sj = j;
                    break;
                }
            }

            while (si!=0) {
                for (int i=0;i<3;i++) {
                    int pi = si + di[i];
                    int pj = sj + dj[i];
                    if (inRange(pi,pj) && v[pi][pj]==0&&arr[pi][pj]==1) {
                        si=pi;
                        sj=pj;
                        v[pi][pj]=1;
                        break;
                    }
                }
            }

            sb.append("#").append(T).append(" ").append(sj).append("\n");
        }
        System.out.println(sb);
    }
}
