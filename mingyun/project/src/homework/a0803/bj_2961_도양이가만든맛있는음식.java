package homework.a0803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2961_도양이가만든맛있는음식 {

    static int n;
    static int[] s;
    static int[] b;
    static int r = Integer.MAX_VALUE;

    public static void dfs(int dept, int sumS, int sumB) {
        if(dept == n-1) {
            if (sumS!=1&&sumB!=0) r = Math.min(r, Math.abs(sumS-sumB));
        }
        else {
            for (int i=dept+1;i<n;i++) {
                dfs(i,sumS*s[i], sumB+b[i]);
                dfs(i,sumS, sumB);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/week3/input_bj_2961.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        s = new int[n];
        b = new int[n];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }
        dfs(-1,1,0);
        System.out.println(r);
    }
}
