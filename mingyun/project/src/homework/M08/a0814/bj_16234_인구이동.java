package homework.M08.a0814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_16234_인구이동 {

    static int N, L, R;
    static int[] di = {0,-1,0,1};
    static int[] dj = {1,0,-1,0};
    static boolean inRange(int i, int j) {
        return 0<=i && i<N && 0<=j && j<N;
    }
    static int sum;
    static int cnt;
    static boolean flag;

    static class Data {
        int i, j;
        Data(int a, int b) {
            i = a; j=b;
        }
    }

    static void dfs(int i, int j, boolean[][] v, int[][] arr, Deque<Data> deque) {
        cnt++;
        sum += arr[i][j];
        deque.add(new Data(i,j));
        v[i][j] = true;
        for (int d=0;d<4;d++) {
            int mi = i + di[d];
            int mj = j + dj[d];
            if (inRange(mi,mj) && !v[mi][mj]) {
                int t = Math.abs(arr[i][j]-arr[mi][mj]);
                if (L <= t && t <= R) {
                    flag = true;
                    dfs(mi,mj, v, arr, deque);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0814/res/input_16234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());L = Integer.parseInt(st.nextToken());R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        do {
            boolean[][] v = new boolean[N][N];
            int[][] arrCopy = new int[N][N];

            flag = false;
            for (int i=0;i<N;i++) {
                for (int j = 0; j < N; j++) {
                    arrCopy[i][j] = arr[i][j];
                }
            }

            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++) {
                    if (!v[i][j]) {
                        cnt = 0;
                        sum = 0;
                        Deque<Data> temp = new ArrayDeque<>();
                        dfs(i, j, v, arrCopy, temp);
                        for (Data d : temp) arr[d.i][d.j] = sum / cnt;
                    }
                }
            }
            if (flag) result++;
        } while (flag);

        System.out.println(result);
    }
}
