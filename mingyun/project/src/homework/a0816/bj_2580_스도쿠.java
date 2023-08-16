package homework.a0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2580_스도쿠 {

    static class Blank {
        int i, j, value;
        Blank(int a, int b, int c) {
            i = a;
            j = b;
            value = c;
        }
    }

    static class NumberInfo {
        ArrayList<Integer> arr = new ArrayList<>(9);
        boolean[] hash = new boolean[10];
    }
    static boolean dfs(int idx, ArrayList<Blank> arrayList,  NumberInfo[] w, NumberInfo[] h) {
        if (idx == arrayList.size()) {
            return true;
        }
        Blank blank = arrayList.get(idx);
        NumberInfo tw = w[blank.i];
        NumberInfo th = h[blank.j];

        for (int a:tw.arr) {
            if (!tw.hash[a]&&!th.hash[a]) {
                blank.value = a;
                tw.hash[a] = true;
                th.hash[a] = true;
                if(dfs(idx+1, arrayList, w, h)) return true;
                blank.value = 0;
                tw.hash[a] = false;
                th.hash[a] = false;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0816/res/input_2580.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[9][9];

        NumberInfo[] w = new NumberInfo[9];
        NumberInfo[] h = new NumberInfo[9];

        for (int i=0;i<9;i++) {
            w[i] = new NumberInfo();
            h[i] = new NumberInfo();
            for (int j=0;j<=9;j++) {
                w[i].arr.add(j);
                h[i].arr.add(j);
            }
        }

        ArrayList<Blank> arrayList = new ArrayList<>(81);
        for (int i=0;i<9;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<9;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                w[i].arr.remove((Integer) arr[i][j]);
                h[j].arr.remove((Integer) arr[i][j]);
                w[i].hash[arr[i][j]] = true;
                h[j].hash[arr[i][j]] = true;
                if (arr[i][j]==0) arrayList.add(new Blank(i,j,0));
            }
        }
        dfs(0,arrayList,w,h);
        for (Blank blank:arrayList) {
            arr[blank.i][blank.j] = blank.value;
        }
        for (int[] a:arr) {
            for (int b:a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}
