package week6.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_4485_그래서녹색옷입은애가젤다지 {
    static class Work  {
        Work() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/week6/res/input_bj_4485.txt"));
        }

        void run() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int[] di = {0,1,0,-1};
            int[] dj = {1,0,-1,0};

            while (true) {
                int n = Integer.parseInt(br.readLine());
                int[][] arr = new int[n+1][n+1];
                int[][] result = new int[n+1][n+1];
                for (int i=0;i<=n;i++) {
                    Arrays.fill(arr[i], Integer.MAX_VALUE);
                    Arrays.fill(result[i], Integer.MAX_VALUE);
                }
                if (n==0) break;
                for (int i=1;i<=n;i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int j = 1; j <= n; j++) {
                        arr[i][j] =  Integer.parseInt(st.nextToken());
                    }
                }

                PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[3], o2[3])));
                boolean[][] v = new boolean[n+1][n+1];
                pq.add(new int[]{1,1,arr[1][1]});

                while(!pq.isEmpty()) {
                    int[] next = pq.remove();
                    int i = next[0], j = next[1], w = next[0];
                    if (v[i][j])
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Work work = new Work();
        work.run();
    }
}