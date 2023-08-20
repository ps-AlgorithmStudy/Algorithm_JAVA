package greedy;

import java.util.*;
import java.io.*;

public class week5_bj_1715 {

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int compare = 0;
        while (!pq.isEmpty())
        {
            int a = pq.poll();
            int b;
            if (pq.isEmpty()) b = 0;
            else b = pq.poll();

            compare += a + b;
            if (pq.isEmpty()) break;
            pq.add(a + b);
        }
        if (N == 1)compare = 0;
        System.out.println(compare);
    }
}
/*
// Integer 타입으로 우선순위 큐 선언(낮은 숫자 순으로 우선순위 결정)
PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>();

// Integer 타입으로 우선순위 큐 선언(높은 숫자 순으로 우선순위 결정)
PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>(Collections.reverseOrder());

 */