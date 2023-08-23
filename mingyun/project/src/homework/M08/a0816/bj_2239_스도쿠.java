package homework.M08.a0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bj_2239_스도쿠 {
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

    static NumberInfo[] w = new NumberInfo[10];
    static NumberInfo[] h = new NumberInfo[10];
    static NumberInfo[] box = new NumberInfo[10];

    static boolean dfs(int idx, ArrayList<Blank> arrayList) {
        if (idx == arrayList.size()) {
            return true;
        }
        Blank blank = arrayList.get(idx);
        NumberInfo tw = w[blank.i];
        NumberInfo th = h[blank.j];
        int boxIndex = (blank.i / 3) * 3 + blank.j / 3;

        for (int a : tw.arr) {
            if (!tw.hash[a] && !th.hash[a] && !box[boxIndex].hash[a]) {
                blank.value = a;
                tw.hash[a] = true;
                th.hash[a] = true;
                box[boxIndex].hash[a] = true;
                if (dfs(idx + 1, arrayList)) return true;
                blank.value = 0;
                tw.hash[a] = false;
                th.hash[a] = false;
                box[boxIndex].hash[a] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0816/res/input_2239.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[9][9];

        for (int i = 0; i < 9; i++) {
            w[i] = new NumberInfo();
            h[i] = new NumberInfo();
            box[i] = new NumberInfo();
            for (int j = 1; j <= 9; j++) {
                w[i].arr.add(j);
                h[i].arr.add(j);
                box[i].arr.add(j);
            }
        }

        ArrayList<Blank> arrayList = new ArrayList<>(81);
        for (int i = 0; i < 9; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = temp[j]-'0';
                w[i].arr.remove((Integer) arr[i][j]);
                h[j].arr.remove((Integer) arr[i][j]);
                int boxIndex = (i / 3) * 3 + j / 3;
                box[boxIndex].arr.remove((Integer) arr[i][j]);
                w[i].hash[arr[i][j]] = true;
                h[j].hash[arr[i][j]] = true;
                box[boxIndex].hash[arr[i][j]] = true;
                if (arr[i][j] == 0) arrayList.add(new Blank(i, j, 0));
            }
        }

        dfs(0, arrayList);

        for (Blank blank : arrayList) {
            arr[blank.i][blank.j] = blank.value;
        }

        for (int[] a : arr) {
            for (int b : a) {
                System.out.print(b + "");
            }
            System.out.println();
        }
    }
}
