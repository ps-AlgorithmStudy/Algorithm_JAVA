package homework.M08.a0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2252_줄세우기 {

    static class Node {
        int entry = 0;
        ArrayList<Integer> nodes = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0821/res/input_2252.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        Node[] nodes = new Node[n+1];
        Arrays.setAll(nodes, i -> new Node());

        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
            nodes[b].entry++;
            nodes[a].nodes.add(b);
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i=1;i<=n;i++) {
            if (nodes[i].entry == 0) deque.addLast(i);
        }

        while (!deque.isEmpty()) {
            int num = deque.removeFirst();
            sb.append(num).append(" ");
            for (int a:nodes[num].nodes) {
                nodes[a].entry--;
                if (nodes[a].entry==0) deque.addLast(a);
            }
        }

        System.out.println(sb);
    }
}
