package project.src.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_2606_바이러스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];
        boolean[] v = new boolean[n];

        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            map[x][y] = true;
            map[y][x] = true;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        int cnt = 0;
        v[0] = true;
        while (!deque.isEmpty()) {
            int target = deque.removeFirst();
            for (int i=0;i<n;i++) {
                if (map[target][i]&&!v[i]) {
                    deque.addLast(i);
                    cnt++;
                    v[i] = true;
                }
            }
        }
        System.out.println(cnt);
    }
}
