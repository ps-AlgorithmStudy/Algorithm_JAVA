package homework.a0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_3040_백설공주와일곱난장이 {

    static boolean[] v = new boolean[9];
    static int[] nan;
    public static void dfs(int p, int cnt, int sum, int[] arr) {
        if (cnt==7) {
            if (sum==100) {
                for (int i=0;i<7;i++) {
                    System.out.println(arr[i]);
                }
            }
        }
        else {
            for (int i=p;i<9;i++) {
                if (!v[i]) {
                    v[i] = true;
                    arr[cnt] = nan[i];
                    dfs(i,cnt+1, sum+nan[i], arr);
                    v[i] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nan = new int[9];
        for (int i=0;i<9;i++) {
            nan[i] = Integer.parseInt(br.readLine());
        }
        dfs(0,0,0,new int[7]);
    }
}
