package week2;

import java.util.*;
import java.io.*;

public class bj_17143_낚시왕 {

    static int n, m;
    static int[] di = {0,-1,1,0,0};
    static int[] dj = {0,0,0,1,-1};

    static class Shark {
        int i;
        int j;
        int s;
        int d;
        int size;

        public Shark(int i, int j, int s, int d, int size) {
            this.i = i;
            this.j = j;
            this.s = s;
            this.d = d;
            this.size = size;
        }
    }

    public static void moveShark(Shark shark) {

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_17143.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<Shark> sharks = new ArrayList<>();
        for (int i=0;i<C;i++) {
            st = new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken()), c=Integer.parseInt(st.nextToken()), s=Integer.parseInt(st.nextToken()), d=Integer.parseInt(st.nextToken()), z=Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r,c, s,d,z));
        }

        int[][] v = new int[n+1][m+1];
        for (Shark s : sharks) {
            v[s.i][s.j]++;
        }

        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }
}
