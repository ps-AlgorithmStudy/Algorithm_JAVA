package homework.M08.a0808;

import java.io.*;
import java.util.*;

public class bj_16926_배열돌리기1 {

    static boolean inRange(int i, int j, int n, int m) {
        return 0 > i || i >= n || 0 > j || j >= m;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_16926.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];


        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};
        boolean[][] v = new boolean[n][m];
        int range = (Math.min(n,m)-1)/2+1;

        Deque<Integer>[] dequeArr = new ArrayDeque[range];
        for (int k=0;k<range;k++) {
            int i = k;
            int j = k;
            int cd = 0;
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addLast(arr[i][j]);
            while (cd!=4) {
                v[i][j] = true;
                int mi = i+di[cd];
                int mj = j+dj[cd];
                if (inRange(mi, mj, n, m) || v[mi][mj]) cd++;
                else {
                    deque.addLast(arr[mi][mj]);
                    i = mi; j = mj;
                }
            }
            dequeArr[k] = deque;
        }

        for (int i=0;i<range;i++) {
            int loop = r%dequeArr[i].size();
            for (int j=0;j<loop;j++) dequeArr[i].addLast(dequeArr[i].removeFirst());
        }


        arr = new int[n][m];
        v = new boolean[n][m];
        for (int k=0;k<range;k++) {
            int i = k;
            int j = k;
            int cd = 0;
            arr[i][j] = dequeArr[k].removeFirst();
            while (cd!=4) {
                v[i][j] = true;
                int mi = i+di[cd];
                int mj = j+dj[cd];
                if (inRange(mi, mj, n, m) || v[mi][mj]) cd++;
                else {
                    arr[mi][mj] = dequeArr[k].removeFirst();
                    i = mi; j = mj;
                }
            }
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
