package homework.M08.a0804;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class bj_2164_카드2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i=1;i<=n;i++) {
            deque.add(i);
        }

        while (deque.size()>=2) {
            deque.removeFirst();
            deque.add(deque.removeFirst());
        }
        System.out.println(deque.pop());
    }
}
