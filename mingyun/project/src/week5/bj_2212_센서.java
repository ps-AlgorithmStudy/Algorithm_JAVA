package week5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_2212_센서 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_2212.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> queue = new ArrayList<>(10000);
        ArrayList<Integer> list = new ArrayList<>(10000);

        for (int i=0;i<n;i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.sort(Comparator.naturalOrder());

        for (int i=1;i<n;i++) queue.add(list.get(i) - list.get(i-1));
        queue.sort(Comparator.reverseOrder());

        int res = 0;
        for (int i=m-1;i<n-1;i++) {
            res += queue.get(i);
        }
        System.out.println(res);
    }
}