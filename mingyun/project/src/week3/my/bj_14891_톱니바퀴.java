package project.src.week3.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_14891_톱니바퀴 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_14891.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer>[] g = new ArrayDeque[5];

        for (int i=1;i<=4;i++) {
            g[i] = new ArrayDeque<>();
            for (int d:br.readLine().toCharArray()){
                g[i].add(d-'0');
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i=0;i<k;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
        }
    }
}
