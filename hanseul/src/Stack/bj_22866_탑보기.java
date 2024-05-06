package Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_22866_탑보기 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        int[] cnt = new int[N + 1], near = new int[N + 1];
        ArrayDeque<Integer> deque;

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            near[i] = -100_000;
        }

        deque = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
                deque.pollLast();
            }
            cnt[i] = deque.size();
            if(cnt[i] > 0) near[i] = deque.peekLast();
            deque.offer(i);
        }

        deque = new ArrayDeque<>();
        for(int i=N; i>0; i--){
            while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
                deque.pollLast();
            }
            int s = deque.size();
            cnt[i] += s;
            if(s > 0 && deque.peekLast()-i < i-near[i]) near[i] = deque.peekLast();
            deque.offer(i);
        }

        //result
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(cnt[i]);
            if(cnt[i] > 0){
                sb.append(" ").append(near[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}