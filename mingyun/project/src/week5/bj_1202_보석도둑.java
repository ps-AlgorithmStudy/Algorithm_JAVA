package week5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1202_보석도둑 {

    static class Gem {
        int m, v;
        Gem(int a, int b) {
            m = a; v = b;
        }
        @Override
        public String toString() {
            return m + " " + v;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_1202.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Gem> gems = new ArrayList<>(n);
        ArrayList<Integer> bag = new ArrayList<>(m);

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            gems.add(new Gem(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        for (int i=0;i<m;i++) bag.add(Integer.parseInt(br.readLine()));
        bag.sort(Comparator.naturalOrder());
        gems.sort(((o1, o2) -> Integer.compare(o1.m, o2.m)));

        PriorityQueue<Integer> queue = new PriorityQueue<>(n,Comparator.reverseOrder());

        int x = 0;
        long result = 0;
        for (int b:bag) {
            while (x<n && gems.get(x).m <= b) {
                queue.add(gems.get(x++).v);
            }
            if (!queue.isEmpty()) {
                result = result + queue.remove();
            }
        }
        System.out.println(result);
    }
}