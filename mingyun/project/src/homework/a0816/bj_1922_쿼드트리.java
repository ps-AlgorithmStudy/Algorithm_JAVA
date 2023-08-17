package homework.a0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class bj_1922_쿼드트리 {
    static int n;
    static StringBuilder sb = new StringBuilder();

    static void div(int pi, int pj,int w, int[][] arr) {
        int zero = 0;
        int one = 0;

        StringBuilder tsb = new StringBuilder();
        if (w==2) tsb.append("(");
        for (int i=pi;i<pi+w;i++) {
            for (int j=pj;j<pj+w;j++) {
                if (arr[i][j] == 0) zero++;
                else one++;
                if (w==2) tsb.append(arr[i][j]);
            }
        }
        if (w==2) tsb.append(")");

        if (zero==0) sb.append("1");
        else if (one==0) sb.append("0");
        else if (w!=2){
            int div = w/2;
            sb.append("(");
            div(pi,pj,div, arr);
            div(pi,pj+div,div, arr);
            div(pi+div,pj,div, arr);
            div(pi+div,pj+div,div, arr);
            sb.append(")");
        }
        else {
            sb.append(tsb);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0816/res/input_1873.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i=0;i<n;i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j=0;j<n;j++) {
                arr[i][j] = temp[j]-'0';
            }
        }
        div(0,0,n,arr);

        System.out.println(sb);
    }
}
