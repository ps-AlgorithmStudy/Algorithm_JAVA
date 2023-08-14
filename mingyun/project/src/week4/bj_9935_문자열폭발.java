package project.src.week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_9935_문자열폭발 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_9935.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        char[] string = br.readLine().toCharArray();
        char[] bom = br.readLine().toCharArray();
        Deque<Character> deque = new ArrayDeque<>();

        int bs = bom.length;

        char[] check = new char[bs];
        boolean flag;
        for (char c:string) {
            deque.addLast(c);
            if (deque.size() >= bs) {
                for (int i=bs-1;i>=0;i--) check[i] = deque.removeLast();
                flag = false;
                for (int i=0;i<bs;i++) {
                    if (check[i]!=bom[i]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    for (char t:check) deque.add(t);
                }
            }
        }
        while (!deque.isEmpty()) sb.append(deque.removeFirst());
        if (sb.toString().isEmpty()) sb.append("FRULA");
        System.out.println(sb);
    }
}
