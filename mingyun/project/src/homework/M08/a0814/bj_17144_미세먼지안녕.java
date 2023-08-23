package homework.M08.a0814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_17144_미세먼지안녕 {

    static int R,C,T;
    static int[] di = {0,-1,0,1};
    static int[] dj = {1,0,-1,0};

    static boolean inRange(int i, int j) {
        return 0<=i && i<R && 0<=j && j<C;
    }

    static int[][] spread(int[][] arr) {
        int[][] spread  = new int[R][C];
        for (int i=0;i<R;i++) {
            for (int j=0;j<C;j++) {
                spread[i][j] += arr[i][j];
                if (arr[i][j] == -1) spread[i][j] = -1;
                else if (arr[i][j]>0){
                    for (int d=0;d<4;d++) {
                        int mi = di[d] + i;
                        int mj = dj[d] + j;
                        if (inRange(mi,mj) && arr[mi][mj]!=-1) {
                            spread[i][j] -= arr[i][j]/5;
                            spread[mi][mj] += arr[i][j]/5;
                        }
                    }
                }
            }
        }
        return spread;
    }

    static void moveAir(int airCleaner, int[][] arr) {
        for (int z=0;z<=1;z++) {
            int i = airCleaner + z, j=0, d=0;
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            deque.addFirst(0);
            do {
                int mi = i + di[d] * (z==0?1:-1), mj = j + dj[d];
                if (inRange(mi,mj)) {
                    deque.addLast(arr[mi][mj]);
                    i = mi; j=mj;
                }
                else d++;
            } while (arr[i][j] !=-1);
            deque.removeLast();deque.removeLast();

            i=airCleaner + z; j=0; d=0;
            do {
                int mi = i + di[d] * (z==0?1:-1), mj = j + dj[d];
                if (inRange(mi,mj)) {
                    arr[mi][mj] = deque.removeFirst();
                    i = mi; j=mj;
                }
                else d++;
            } while (!deque.isEmpty());
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0814/res/input_17144.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());C = Integer.parseInt(st.nextToken());T = Integer.parseInt(st.nextToken());
        int[][] arr = new int[R][C];

        int airCleaner=0;
        for (int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<C;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j]==-1) airCleaner = i-1;
            }
        }

        for (int i=0;i<T;i++) {
            arr = spread(arr);
            moveAir(airCleaner, arr);
        }

        int result = 0;
        for (int i=0;i<R;i++) {
            for (int j=0;j<C;j++) {
                if (arr[i][j]!=-1) result += arr[i][j];
            }
        }
        System.out.println(result);
    }
}
