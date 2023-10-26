package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 창용마을무리의개수 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/창용마을무리의개수.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] edge = new ArrayList[n+1];
            boolean[] v = new boolean[n+1];

            for (int i=0;i<=n;i++) edge[i] = new ArrayList<>();
            for (int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                edge[a].add(b);
                edge[b].add(a);
            }

            ArrayDeque<Integer> deque = new ArrayDeque();
            int result = 0;
            for (int i=1;i<=n;i++) {
                if (!v[i]) {
                    result++;
                    deque.addFirst(i);
                    while(!deque.isEmpty()) {
                        int now = deque.removeFirst();
                        for (int a:edge[now]) {
                            if (!v[a]) {
                                deque.add(a);
                                v[a] = true;
                            }
                        }
                    }
                }
            }
            System.out.println("#"+ TC + " " + result);
        }
    }
}
