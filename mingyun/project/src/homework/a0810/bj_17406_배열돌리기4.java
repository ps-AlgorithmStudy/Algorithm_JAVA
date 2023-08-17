package project.src.homework.a0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_17406_배열돌리기4 {

    static boolean inRange(int i, int j, int n, int m) {
        return 0 > i || i >= n || 0 > j || j >= m;
    }

    static int[][] moveArr(int[][] arr) {
        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};
        int n = arr.length;
        int m = arr[0].length;
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
            dequeArr[i].addFirst(dequeArr[i].removeLast());
        }


        int[][] result = new int[n][m];
        v = new boolean[n][m];
        for (int k=0;k<range;k++) {
            int i = k;
            int j = k;
            int cd = 0;
            result[i][j] = dequeArr[k].removeFirst();
            while (cd!=4) {
                v[i][j] = true;
                int mi = i+di[cd];
                int mj = j+dj[cd];
                if (inRange(mi, mj, n, m) || v[mi][mj]) cd++;
                else {
                    result[mi][mj] = dequeArr[k].removeFirst();
                    i = mi; j = mj;
                }
            }
        }
        return result;
    }

    public static int[][] order(int r, int c, int s, int[][] arr) {
        int[][] temp = new int[s*2+1][s*2+1];
        int ti=0;
        for (int i=r-s-1;i<r+s;i++,ti++) {
            int tj=0;
            for (int j=c-s-1;j<c+s;j++,tj++) {
                temp[ti][tj] = arr[i][j];
            }
        }
        temp = moveArr(temp);

        ti=0;
        for (int i=r-s-1;i<r+s;i++,ti++) {
            int tj=0;
            for (int j=c-s-1;j<c+s;j++,tj++) {
                arr[i][j] = temp[ti][tj];
            }
        }
        return arr;
    }

    static int result = Integer.MAX_VALUE;
    static int[][] work;
    static boolean[] v;
    static int n;
    static int m;
    static int k;

    static void dfs(int[][] arr, int C) {
        if (C==k) {
            for (int i=0;i<n;i++) {
                int cnt = 0;
                for (int j=0;j<m;j++) {
                    cnt += arr[i][j];
                }
                result = Math.min(result,cnt);
            }
            return;
        }
        for (int w=0;w<k;w++) {
            if (!v[w]) {
                int[][] temp = new int[n][m];
                for (int i=0;i<n;i++) {
                    for (int j=0;j<m;j++) {
                        temp[i][j] = arr[i][j];
                    }
                }
                v[w] = true;
                arr = order(work[w][0],work[w][1],work[w][2],arr);
                dfs(arr, C+1);
                v[w] = false;
                arr =temp;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0810/res/input_11725.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        work = new int[k][3];
        v = new boolean[k];
        for (int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
            work[i][2] = Integer.parseInt(st.nextToken());
        }
        dfs(arr,0);
        System.out.println(result);
    }
}
