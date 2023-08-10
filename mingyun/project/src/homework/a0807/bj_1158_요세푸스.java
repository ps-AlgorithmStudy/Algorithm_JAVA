package project.src.homework.a0807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1158_요세푸스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Deque<Integer> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i=1;i<=n;i++) {
            deque.addLast(i);
        }
        int cnt=0;
        int[] result = new int[n];
        int p=0;
        while (!deque.isEmpty()) {
            if (++cnt==k) {
                cnt = 0;
                result[p++] = deque.removeFirst();
            }
            else {
                deque.addLast(deque.removeFirst());
            }
        }

        sb.append("<");
        for (int i=0;i<n-1;i++) {
            sb.append(result[i]).append(", ");
        }
        sb.append(result[n-1]).append(">");
        System.out.println(sb);
    }
}
