package week4;


import java.io.*;
import java.util.*;

public class Main_bj_골드5_5639_이진검색트리 {
	
	static StringBuilder sb;
	
	static void postOrder(List<Integer> list, int start, int end) {
		// 탐색이 완료된 경우
		if(start >= end) {
			return;
		}
		
		// leftSubtree의 끝을 찾는다
		int parent = list.get(start);
		int i = start + 1;
		for(; i < end; i++) {
			if(parent < list.get(i)) {
				break;
			}
 		}
		
		postOrder(list, start + 1, i); // [start + 1, i)
		postOrder(list, i, end); // [i, end);
		sb.append(parent).append("\n");
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		List<Integer> list = new ArrayList<>();
		String input = "";
		while((input = br.readLine()) != null) {
			list.add(Integer.parseInt(input));
		}
		/*while(true) {
			String input = br.readLine();
			if(input == null || input == "") {
				break;
			}
			list.add(Integer.parseInt(input));
		}*/
		
		
		postOrder(list, 0, list.size());
		System.out.print(sb);
		br.close();
	}
}