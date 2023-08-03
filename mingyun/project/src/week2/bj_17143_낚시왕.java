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

    public static void move(List<Shark> sharks) {
        Shark[][] sharkMap = new Shark[n + 1][m + 1];

        for (Shark s : sharks) {
            moveShark(s);
            if (sharkMap[s.i][s.j] == null) {
                sharkMap[s.i][s.j] = s;
            } else {
                if (sharkMap[s.i][s.j].size < s.size) {
                    sharkMap[s.i][s.j] = s;
                }
            }
        }

        sharks.clear();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (sharkMap[i][j] != null) {
                    sharks.add(sharkMap[i][j]);
                }
            }
        }
    }

    public static void moveShark(Shark shark) {
        int i = shark.i;
        int j = shark.j;
        int s = shark.s;
        int d = shark.d;

        while (s > 0) {
            if (i==n&&d==2||j==m&&d==3) {
                if (d==2) d=1;
                if (d==3) d=4;
            }
            if (i==1&&d==1||j==1&&d==4) {
                if (d==1) d=2;
                if (d==4) d=3;
            }
            s--;
            i += di[d];
            j += dj[d];
        }
        shark.i = i;
        shark.j = j;
        shark.d = d;
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


        int result = 0;
        for (int work=1;work<=m;work++) {
            int min = Integer.MAX_VALUE;
            Shark catcher = null;
            for (Shark s : sharks) {
                if (s.j == work) {
                    if (min > s.i) {
                        min = s.i;
                        catcher = s;
                    }
                }
            }
            if (catcher!=null) {
                result += catcher.size;
                sharks.remove(catcher);
            }

            move(sharks);
        }
        System.out.println(result);
    }
}
