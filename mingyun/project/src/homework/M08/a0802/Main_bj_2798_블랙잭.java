package homework.M08.a0802;

import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.CodingErrorAction;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main_bj_2798_블랙잭 {
    static int result = 0;
    static int n, m;
    static int[] card;

    public static void dfs(int c, int p, int[] arr) {
        if (c==3) {
            int sum=0;
            for (int a:arr) sum += a;
            if (sum <= m) result = Math.max(result, sum);
        }
        else {
            for(int i=p+1;i<n;i++) {
                arr[c] = card[i];
                dfs(c+1, i, arr);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_2798.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        card = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<n;i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,-1, new int[3]);

        System.out.println(result);
    }
}
