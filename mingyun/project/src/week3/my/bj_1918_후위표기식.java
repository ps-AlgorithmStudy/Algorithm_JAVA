package project.src.week3.my;

import java.io.*;
import java.util.*;

public class bj_1918_후위표기식 {
    static HashSet<Character> first = new HashSet<>(Arrays.asList('(',')'));
    static HashSet<Character> second = new HashSet<>(Arrays.asList('*','/'));
    static HashSet<Character> third = new HashSet<>(Arrays.asList('+','-'));

    static String convert(String s1) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> deque =new ArrayDeque<>();

        for (char c:s1.toCharArray()) {
            if (first.contains(c)||second.contains(c)||third.contains(c)) {
                while (!deque.isEmpty()) {
                    if (third.contains(c)&&third.contains(deque.getLast())) {
                        sb.append(deque.removeLast());
                    }
                    else if (second.contains(c)&&second.contains(deque.getLast())) {
                        sb.append(deque.removeLast());
                    }
                    else if (third.contains(c)&&second.contains(deque.getLast())) {
                        sb.append(deque.removeLast());
                    }
                    else break;
                }
                if (c==')') {
                    while (deque.getLast()!='(') sb.append(deque.removeLast());
                    deque.removeLast();
                }else deque.addLast(c);
            }
            else {
                sb.append(c);
            }
        }
        while (!deque.isEmpty()) sb.append(deque.removeLast());
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week3/res/input_bj_15652.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(convert(br.readLine()));
    }

}
