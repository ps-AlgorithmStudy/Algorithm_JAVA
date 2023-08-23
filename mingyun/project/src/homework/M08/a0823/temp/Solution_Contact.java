package homework.M08.a0823.temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_Contact {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0823/res/contact.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int TC = 1; TC <= 10; TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[100][100];
            boolean[] v = new boolean[100];

            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n/2;i++) {
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                map[a-1][b-1] = true;
            }

            ArrayDeque<int[]> deque = new ArrayDeque<>();
            deque.addLast(new int[] {m-1,0});
            v[m-1] = true;

            int max = 0;
            int dept = 0;
            while (!deque.isEmpty()) {
                int[] now = deque.removeFirst();
                if (dept != now[1]) {
                    dept = now[1]; max = 0;
                }
                max = Math.max(max, now[0]);
                for (int i=0;i<100;i++) {
                    if (map[now[0]][i] && !v[i]) {
                        v[i] = true;
                        deque.add(new int[] {i,now[1]+1});
                    }
                }
            }
            System.out.println("#" + TC + " " + (max+1));
        }

    }
}
