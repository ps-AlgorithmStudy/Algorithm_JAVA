package week4;

import java.io.*;
import java.util.*;

public class bj_1967_트리의지름 {
    static class Tree {
        class Node {
            ArrayList<Integer> value = new ArrayList<>();
            ArrayList<Integer> childList = new ArrayList<>();
        }

        Node[] nodes;
        int n;

        Tree(int n) {
            this.n = n;
            nodes = new Node[n+1];
            for (int i=1;i<=n;i++) {
                nodes[i] = new Node();
            }
        }

        void add(int x, int y, int v) {
            nodes[x].childList.add(y);
            nodes[y].childList.add(x);
            nodes[x].value.add(v);
            nodes[y].value.add(v);
        }

        int max;
        boolean[] v;

        private int dfs(int idx) {

        }

        int getLength() {
            max=0;
            v = new boolean[n+1];
            dfs(1);
            return max;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_1967.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Tree tree = new Tree(Integer.parseInt(br.readLine()));
        for (int i=0;i<tree.n-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tree.add(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        System.out.println(tree.getLength());
    }
}
