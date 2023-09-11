import java.io.*;
import java.util.*;

public class Main_bj_1918_후위표기식 {
	
	static void solution(String s) {
		// 피연산자는 그대로 출력
		// 연산자는 더 높은 우선순위를 만나기 전엔 stack에 넣고
		// 더 높은 우선순위를 만나면 stack의 연산자들을 pop해줌
		ArrayDeque<Character> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(char c : s.toCharArray()) {
			if(isOperand(c)) {
				sb.append(c);
			} else {
				// 우선순위 고려
				// stack.pop;
				if(c == '(') {
					stack.push(c);
				} else if(c == ')') {
					while(!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop();
					
				} else {
					// 더 높은 우선순위를 만났다면
					// 스택을 비어줌
					while(!stack.isEmpty() && getPriority(c) <= getPriority(stack.peek())) {
						sb.append(stack.pop());
					}
					stack.push(c);
				}
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
	
	static boolean isOperand(char c) {
		return Character.isAlphabetic(c);
	}
	
	static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	static int getPriority(char c) {
		int result = 0;
		if(c == '+' || c == '-') {
			result = 1;
		} else if(c == '*' || c == '/') {
			result = 2;
		}
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		solution(input);
	}
}