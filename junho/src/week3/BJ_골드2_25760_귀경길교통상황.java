package week3;

import java.io.*;
import java.util.*;

public class BJ_골드2_25760_귀경길교통상황 {
	
	static int N;
	
	// key - value; Set 이용
	static Set<Integer>[] graph;
	static boolean[] visited;
	
	static void solution() {
		// 그래프 입력 출력
		/*for(int i = 1; i < graph.length; i++) {
			System.out.println(graph[i]);
		}*/
		
		// 못 하겠다
		
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/week3/BJ_25760_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new Set[N + 1];
		// Arrays.fill(graph, new HashSet<Integer>()); // 다 똑같은 객체를 가르키네??
		for(int i = 1; i <= N; i++) {
			graph[i] = new HashSet<Integer>();
		}
		
		// 양방향 그래프를 구성한다.
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			graph[src].add(target);
			graph[target].add(src);
		}
		
		visited = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			visited[i] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
		}
		
		solution();
	}
}
