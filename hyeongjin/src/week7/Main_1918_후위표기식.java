package week6;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1918_후위표기식 {
    static int priority(char op){
        switch (op){
            case '(' : case ')':
                return 0;
            case '+' : case '-':
                return 1;
            case '*' : case '/':
                return 2;
            default:
                return -1;
        }
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        Stack <Character> stack = new Stack<>();
        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);

            switch (current){
                case '+' : case'-' :case '*' : case '/' :
                    while(!stack.isEmpty() && priority(stack.peek()) >= priority(current)){
                        sb.append(stack.pop());
                    }
                    stack.add(current);
                    break;
                case '(':
                    stack.add(current);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(current);
                    break;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.print(sb.toString());
    }
}
