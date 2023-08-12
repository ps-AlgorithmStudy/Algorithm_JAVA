package project.src.homework.a0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2839_설탕배달 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0811/res/input_2839.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int r=Integer.MAX_VALUE;

        for (int i=0;i<n;i++) {
            if ((n-5*i)%3==0 && (n-5*i) >=0) {
                r = Math.min(r,i + ((n-5*i)/3));
            }
        }

        if (r==Integer.MAX_VALUE) r = -1;
        System.out.println(r);
    }
}
