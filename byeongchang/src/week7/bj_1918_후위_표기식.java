package week7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class bj_1918_후위_표기식 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/bj_1918.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println(solution(br));
        for(int tc=1; tc<=7; tc++) {
            System.out.println(solution(br));
        }
        br.close();
    }
    public static String solution(BufferedReader br) throws Exception {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String notation = br.readLine();
        /*
        스택 사용
        스택에 기호만 넣기
        기호를 넣을 때 스택 최상단 비교
        () 만나면 사이에 있는 기호 출력
        +-가 ///* 만나면 스택 안 기호 다 출력
         */
        for(int i=0; i<notation.length(); i++) {
            char c = notation.charAt(i);
            // 알파벳인 경우
            if(Character.isAlphabetic(c)) {
                sb.append(c);
            }
            // 스택 비어있음
            else if(stack.isEmpty()) {
                stack.push(c);
            }
            // 스택 안에 기호 있음
            else {
                char oper;
                /*
                c: ( 그냥 스택에 push
                c: +,- 스택 top에 (이면 push, 아니면 poll하고 push
                c: *,/ 스택 top에 (,+,-이면 push, 아니면 poll하고 push
                c: ) 스택에서 ( 나올 때까지 poll
                 */
                if(c=='(') stack.push(c);
                else if(c==')') {
                    oper = stack.poll();
                    while(oper!='('){
                        sb.append(oper);
                        oper = stack.poll();
                    }
                }
                else if(c=='*' || c=='/'){
                    oper = stack.peek();
                    if(oper=='(' || oper=='+' || oper=='-') stack.push(c);
                    else {
                        oper = stack.peek();
                        while(oper!='(' && oper!='+' && oper!='-'){
                            sb.append(stack.poll());
                            if(stack.isEmpty()) break;
                            oper = stack.peek();
                        }
                        stack.push(c);
                    }
                }
                else {
                    oper = stack.peek();
                    if(oper=='(') stack.push(c);
                    else {

                        while(oper!='('){
                            sb.append(stack.poll());
                            if(stack.isEmpty()) break;
                            oper = stack.peek();
                        }
                        stack.push(c);
                    }
                }

            }

        }
        while(!stack.isEmpty()){
            sb.append(stack.poll());
        }
        return sb.toString();
    }
}
