package Stack;

import java.util.*;
import java.io.*;

public class week10_pg_주식가격 {

    static int[] price = {1, 2, 3, 4, 5, 4, 3, 2, 1};
    public static void main(String[] args) throws Exception{

        int[] result = solution(price);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {0, prices[0]});
        for (int i = 1; i < prices.length; i++) {
            if (q.getLast()[1] <= prices[i])
                q.offer(new int[] {i, prices[i]});
            else{
                while (!q.isEmpty() && q.getLast()[1] > prices[i]){
                    int[] rm = q.pollLast();
                    answer[rm[0]] = i - rm[0];
                }
                q.offer(new int[] {i, prices[i]});
            }

        }

        while (!q.isEmpty()){
            int[] cur = q.poll();
            answer[cur[0]] = prices.length - 1 - cur[0];
        }

        return answer;
    }
}
/*

1 2 3 4 5 4 3 2 1


 */
