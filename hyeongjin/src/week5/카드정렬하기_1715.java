import java.io.*;
import java.util.*;

public class 카드정렬하기_1715 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		int[] cards = new int[N];
		for (int i = 0; i < N; i++) cards[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < cards.length; i++) q.add(cards[i]);
		
		int a, b;
		for (int i = 0; i < N-1; i++) {
			while(q.size()>1) {
				a = q.poll();
				b = (q.isEmpty()) ? 0 : q.poll();
				if(b>0) sum += a+b;
				q.offer(a+b);
			}
		}
		System.out.println(sum);
	}
}
