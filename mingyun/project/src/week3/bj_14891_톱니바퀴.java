package week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_14891_톱니바퀴 {
    public static void move(Deque<Integer> g, int d) {
        if (d == 1) {
            g.addFirst(g.removeLast());
        }
        else {
            g.addLast(g.removeFirst());
        }
    }

    public static void work(int p, int d, Deque<Integer>[] g, int mv) {
        int nextDirection = -d;
        int neighbor = p + mv;
        boolean shouldRotateNeighbor = false;

        if (mv == -1 && p > 1 && g[p].toArray()[6] != g[neighbor].toArray()[2]) {
            shouldRotateNeighbor = true;
        } else if (mv == 1 && p < 4 && g[p].toArray()[2] != g[neighbor].toArray()[6]) {
            shouldRotateNeighbor = true;
        }

        move(g[p], d); // 현재 톱니바퀴 회전

        if (shouldRotateNeighbor) {
            work(neighbor, nextDirection, g, mv);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/week3/input_bj_14891.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer>[] g = new ArrayDeque[5];

        for (int i=1;i<=4;i++) {
            g[i] = new ArrayDeque();
            for (int d:br.readLine().toCharArray()){
                g[i].add(d-'0');
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i=0;i<k;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            work(p,d,g,-1);
            work(p,d,g,1);
        }

        int sum = 0;
        for (int i=1;i<=4;i++) {
            Object[] object = g[i].toArray();
            sum += (int) ((Integer) object[0] * Math.pow(2,i-1));
        }
        System.out.println(sum);
    }
}
