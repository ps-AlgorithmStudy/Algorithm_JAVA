package homework.M08.a0804;

import java.io.*;
import java.util.*;

public class bj_17140_이차원배열과연산 {

    public static class Data {
        int[][] arr;
        int x;
        int y;

        public Data(int [][] arr, int x, int y) {
            this.arr = arr;
            this.x = x;
            this.y = y;
        }
    }

    public static class Arr {
        int[] arr;
        int len;

        public Arr(int[] arr, int len) {
            this.arr = arr;
            this.len = len;
        }
    }

    public static Arr arrayBuilder(int[] arr) {
        int[] res = new int[101];
        int[] cnt = new int[101];

        for (int i=1;i<=100;i++) {
            cnt[arr[i]]++;
        }

        int c = 1;

        for (int i=1;i<=100;i++) {
            for (int j=1;j<=100;j++) {
                if (cnt[j]==i) {
                    res[c++] = j;
                    res[c++] = i;
                }
                if (c==101) return new Arr(res,c-1);
            }
        }
        return new Arr(res,c-1);
    }

    public static Data R(Data data) {
        data.x = 0;
        for (int i=1;i<=100;i++) {
            Arr res = arrayBuilder(data.arr[i]);
            data.arr[i] = res.arr;
            data.x = Math.max(data.x, res.len);
        }
        return data;
    }

    public static Data C(Data data) {
        int[][] arr = new int[101][101];
        data.y = 0;
        for (int i=1;i<=100;i++) {
            int[] tempArr = new int[101];
            for (int j=1;j<=100;j++) {
                tempArr[j] = data.arr[j][i];
            }
            Arr res = arrayBuilder(tempArr);
            for (int j=1;j<=100;j++) {
                arr[j][i] = res.arr[j];
                data.y = Math.max(data.y, res.len);
            }
        }
        data.arr = arr;
        return data;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_17140.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[101][101];
        for (int i=0;i<3;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0;j<3;j++) {
                arr[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }

        Data data = new Data(arr, 3, 3);

        if (data.arr[r][c] == k) {
            System.out.println(0);
            return;
        }

        for (int i=1;i<=100;i++) {
            if (data.x <= data.y) {
                data = R(data);
            }
            else {
                data = C(data);
            }
            if (data.arr[r][c] == k) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
