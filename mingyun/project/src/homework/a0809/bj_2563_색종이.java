package project.src.homework.a0809;

import java.io.*;
import java.util.*;

public class bj_2563_색종이 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] board = new boolean[101][101];
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int m=0;m<n;m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int i=x;i<x+10;i++) {
                for (int j=y;j<y+10;j++) {
                    if(!board[i][j]) {
                        board[i][j] = true;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
