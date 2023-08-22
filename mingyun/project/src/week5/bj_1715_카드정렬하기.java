package week5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1715_카드정렬하기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_1715.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=0;i<n;i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int result = 0;
        while (queue.size() > 1) {
            int temp = queue.poll() + queue.poll();
            result += temp;
            queue.add(temp);
        }
        System.out.println(result);
    }
}