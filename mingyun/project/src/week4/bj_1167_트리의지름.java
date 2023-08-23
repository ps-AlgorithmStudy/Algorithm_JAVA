package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_1167_트리의지름 {
    static class Tree {
        class Node {
            ArrayList<Integer> value = new ArrayList<>(100);
            ArrayList<Integer> child = new ArrayList<>(100);
        }

        int n;
        Node[] nodes;
        Tree(int n) {
            this.n = n+1;
            nodes = new Node[n+1];
            for (int i=0;i<=n;i++) {
                nodes[i] = new Node();
            }
        }

        void add(int a, int b, int value) {
            nodes[a].child.add(b);
            nodes[a].value.add(value);
            nodes[b].child.add(a);
            nodes[b].value.add(value);
        }
        int max;
        int idx;

        void dfs(int p, int sum, boolean[] v) {
            Node node = nodes[p];
            v[p] = true;
            if (max<sum) {
                max = sum;
                idx = p;
            }
            for (int i=0;i<node.child.size();i++) {
                if (!v[node.child.get(i)]) dfs(node.child.get(i),sum+node.value.get(i), v);
            }
        }

        int getDistance() {
            max=0;idx=0;
            dfs(1,0, new boolean[n]);
            max=0;
            dfs(idx,0, new boolean[n]);
            return max;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_1167.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree trees = new Tree(n);

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            while (true) {
                int t = Integer.parseInt(st.nextToken());
                if (t == -1) break;
                int v = Integer.parseInt(st.nextToken());
                trees.add(a,t,v);
            }
        }
        System.out.println(trees.getDistance());
    }
}
