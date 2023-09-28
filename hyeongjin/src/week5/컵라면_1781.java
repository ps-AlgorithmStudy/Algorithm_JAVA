import java.io.*;
import java.util.*;
public class 컵라면_1781 {
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int sum  = 0;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Cup> cr = new ArrayList<Cup>();
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int ramen    = Integer.parseInt(st.nextToken());
			cr.add(new Cup(deadline,ramen));
		}
		Collections.sort(cr); // �Ŷ�� ����

		for (Cup cup : cr) {
			int d = cup.deadline;
			int r = cup.ramen;
			q.add(r);
			if(d < q.size()) q.poll();
		}
		
		while(!q.isEmpty()) sum += q.poll();
		System.out.println(sum);
	}
}

class Cup implements Comparable<Cup>{
	int deadline;
	int ramen;
	public Cup(int d, int r) {
		this.deadline = d;
		this.ramen    = r;
	}
	
	@Override
	public int compareTo(Cup o) {
		if(deadline == o.deadline) return o.ramen - ramen; // ��������� ������ ����  ������
		else return deadline - o.deadline;				   // ������� ������
	}
}
