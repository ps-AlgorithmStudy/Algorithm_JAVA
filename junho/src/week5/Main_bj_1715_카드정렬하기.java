package category.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_bj_1715_카드정렬하기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            pq.offer(arr[i]);
        }

        int sum = 0;

        while(pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            int temp = x + y;
            sum += temp;
            pq.offer(temp);
        }

        System.out.println(sum);
    }
}
