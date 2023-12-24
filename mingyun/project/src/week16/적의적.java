package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 적의적 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                Queue<Integer> dq = new ArrayDeque<>();
                dq.offer(i);
                visited[i] = 1;

                while (!dq.isEmpty()) {
                    int x = dq.poll();
                    for (int j : graph.get(x)) {
                        if (visited[j] == 0) {
                            visited[j] = visited[x] * (-1);
                            dq.offer(j);
                        } else if (visited[j] == visited[x]) {
                            System.out.println(0);
                            System.exit(0);
                        }
                    }
                }
            }
        }

        System.out.println(1);
    }
}
