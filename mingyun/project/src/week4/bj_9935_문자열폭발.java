package project.src.week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class bj_9935_문자열폭발 {

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_9935.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] bom = br.readLine().toCharArray();

        ArrayDeque<Character> deque = new ArrayDeque<>();
        List<Character> lastChars = new LinkedList<>();
        int bombLength = bom.length;

        for (Character a : str) {
            deque.addLast(a);
            lastChars.add(a);

            if (lastChars.size() > bombLength) {
                lastChars.remove(0);
            }

            if (lastChars.size() == bombLength) {
                boolean isBomb = true;
                for (int i = 0; i < bombLength; i++) {
                    if (lastChars.get(i) != bom[i]) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    for (int i = 0; i < bombLength; i++) {
                        deque.removeLast();
                        if (!lastChars.isEmpty()) {
                            lastChars.remove(lastChars.size() - 1);
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }

        if (sb.toString().isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
