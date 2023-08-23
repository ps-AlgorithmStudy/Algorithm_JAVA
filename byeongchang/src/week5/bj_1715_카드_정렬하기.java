package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj_1715_카드_정렬하기 {
    //https://www.acmicpc.net/problem/1715
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br));
        br.close();
    }
    public static int solution(BufferedReader br) throws Exception {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> card = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            card.add(Integer.parseInt(br.readLine()));
        }
        int ans = 0;
        while(card.size()>=2){
            int num1 = card.poll();
            int num2 = card.poll();
            ans += num1+num2;
            card.add(num1+num2);
        }
        return ans;
    }
}
