package week4;

import java.io.*;
import java.util.*;

public class bj_11725_트리의부모찾기 {

    static class Tree {
        class Node {
            int parent;
            ArrayList<Integer> relation = new ArrayList<>();
        }
        int n;
        Node[] nodes;

        Tree(int n) {
            this.n = n;
            nodes = new Node[n+1];
            for (int i=1;i<=n;i++) {
                nodes[i] = new Node();
            }
        }

        void add(int x, int y) {
            nodes[x].relation.add(y);
            nodes[y].relation.add(x);
        }

        void dfs(int idx, boolean[] v) {
            v[idx] = true;
            for(int t:nodes[idx].relation) {
                if (!v[t]) {
                    nodes[t].parent = idx;
                    dfs(t,v);
                }
            }
        }

        void printParent() {
            dfs(1,new boolean[n+1]);
            StringBuilder sb = new StringBuilder();

            for (int i=2;i<=n;i++) {
                sb.append(nodes[i].parent).append("\n");
            }
            System.out.println(sb);
        }
    }


    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0811/res/input_11725.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tree tree = new Tree(Integer.parseInt(br.readLine()));

        for (int i=0;i<tree.n-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tree.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        tree.printParent();
    }

}
