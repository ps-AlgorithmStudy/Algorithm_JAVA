package week3;

import java.io.*;
import java.util.*;

public class bj_25760_귀경길교통상황을알려드립니다 {

    static class Node {
        ArrayList<Integer> child = new ArrayList<>();
        int dept = 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_25760.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Node[] tree = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new Node();
        }

        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].child.add(b);
            tree[b].child.add(a);
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        tree[1].dept = 1;

        boolean[] visited = new boolean[n+1];
        visited[1] = true;

        while (!q.isEmpty()) {
            int target = q.removeFirst();
            for (int child : tree[target].child) {
                if (visited[child]) continue;
                tree[child].dept = tree[target].dept + 1;
                visited[child] = true;
                q.add(child);
            }
        }

        List<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) arr.add(tree[i].dept);
        }

        Collections.sort(arr, Collections.reverseOrder());

        int result = 0, time = 0;
        for (Integer dept : arr) {
            result = Math.max(result, dept + time);
            time++;
        }

        System.out.println(result);
    }

}