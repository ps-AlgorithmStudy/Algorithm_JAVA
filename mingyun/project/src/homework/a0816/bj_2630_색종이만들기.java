package homework.a0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2630_색종이만들기 {
    static int n;
    static int o = 0;
    static int z = 0;
    static void div(int pi, int pj,int w, int[][] arr) {
        int zero = 0;
        int one = 0;

        for (int i=pi;i<pi+w;i++) {
            for (int j=pj;j<pj+w;j++) {
                if (arr[i][j] == 0) zero++;
                else one++;
            }
        }

        if (zero==0) z++;
        else if (one==0) o++;
        else if (w!=1){
            int div = w/2;
            div(pi,pj,div, arr);
            div(pi,pj+div,div, arr);
            div(pi+div,pj,div, arr);
            div(pi+div,pj+div,div, arr);
        }
        else {
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0816/res/input_2630.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        div(0,0,n,arr);

        System.out.println(o);
        System.out.println(z);
    }
}
