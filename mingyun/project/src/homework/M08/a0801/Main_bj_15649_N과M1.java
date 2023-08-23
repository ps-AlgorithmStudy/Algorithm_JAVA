package homework.M08.a0801;


import java.io.*;
import java.util.*;

public class Main_bj_15649_N과M1 {
    static int[] v;
    static int n, m;

    public static void dfs(int dept, List<Integer> arr) {
        if (m==dept) {
            for (int r:arr) {
                System.out.print((r+1) + " ");
            }
            System.out.println();
        }
        else {
            for (int i=0;i<n;i++) {
                if (v[i]==0) {
                    v[i] = 1;
                    arr.add(i);
                    dfs(dept+1, arr);
                    arr.remove(arr.size()-1);
                    v[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_15649.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new int[n];

        dfs(0, new ArrayList<>());
    }
}
