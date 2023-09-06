package Stack;
import java.io.*;
import java.util.*;
public class week7_bj_1918_후위표기식
{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('A' <= ch && ch <= 'Z')
                sb.append(ch);
            else if (ch == ')'){
                while (!stack.isEmpty()){
                    char pop = stack.pollFirst();
                    if (pop == '(') break;
                    sb.append(pop);
                }
            }
            else if (ch == '('){
                stack.offerFirst(ch);
            }
            else{
                if ((ch == '+' || ch == '-')){
                    while (!stack.isEmpty()){
                        Character pop = stack.pollFirst();
//                    System.out.println(pop);
                        if (pop == '('){
                            stack.offerFirst(pop); break;
                        }
                        sb.append(pop);
                        if (pop == '+' || pop == '-') break;
                    }
                }
                else if ((ch == '*' || ch == '/')){
                    while (!stack.isEmpty()){
                        Character pop = stack.pollFirst();
//                    System.out.println(pop);
                        if (pop == '('){
                            stack.offerFirst(pop); break;
                        }
                        if ((pop == '+' || pop == '-')){
                            stack.offerFirst(pop);
                            break;
                        }
                        sb.append(pop);
                    }
                }
                stack.offerFirst(ch);
            }
        }
        while (!stack.isEmpty()){
            char pop = stack.pollFirst();
            if (pop == '(') break;
            sb.append(pop);
        }

        System.out.println(sb.toString());
    }
}
/*
A*B+C+D+E*F*G+E
(((((A*B)+C)+D)+((E*F)*G))+E)
AB*C+D+EF*G*+E+


G*(A-B*(C/D+E)/F)
GABCD/E+*F/-*

 */