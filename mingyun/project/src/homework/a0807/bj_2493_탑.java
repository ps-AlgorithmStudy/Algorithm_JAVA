package project.src.homework.a0807;

import java.io.*;
import java.util.*;
public class bj_2493_탑 {

    static class Top {
        int index;
        int value;
        Top(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_2493.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Top> deque = new ArrayDeque<>();

        for (int i=0;i<N;i++) {
            deque.add(new Top(i+1, Integer.parseInt(st.nextToken())));
        }

        Deque<Top> workStack = new ArrayDeque<>();
        int[] result = new int[N];

        workStack.push(deque.removeLast());
        int cnt =0;
        while(!deque.isEmpty()) {

            Top top = deque.removeLast();
            while (!workStack.isEmpty()) {
                if (top.value > workStack.getLast().value) {
                    Top pop = workStack.removeLast();
                    result[pop.index-1] = top.index;
                }
                else break;
            }
            workStack.addLast(top);
        }

        for (int i=0;i<N;i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb + " " + cnt);
    }
}
