package week12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트 {
    static int[] arr;
    static boolean[] v, c;
    static int n, cnt;

    static void dfs(int p) {
        v[p] = true;
        int next = arr[p];

        if (!v[next]) {
            dfs(next);
        } else if (!c[next]) {
            for (int i = next; i != p; i = arr[i]) cnt++;
            cnt++;
        }
        c[p] = true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week12/텀프로젝트"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            v = new boolean[n];
            c = new boolean[n];
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken()) - 1;

            for (int i = 0; i < n; i++) {
                if (!v[i]) dfs(i);
            }
            System.out.println(n - cnt);
        }
    }
}

