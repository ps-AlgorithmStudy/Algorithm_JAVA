package project.src.week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_1991_트리순회 {
    public static class Tree {

        Node[] tree;
        int n;

        private static class Node {
            int left;
            int right;
        }

        public Tree(int n) {
            this.n = n;
            tree = new Node[n];
            for (int i=0;i<n;i++) {
                tree[i] = new Node();
            }
        }

        public void add(int parent, int child1, int child2) {
            tree[parent].left = child1;
            tree[parent].right = child2;
        }

        public void getOrders(int idx, ArrayList<Character>[] orders) {
            orders[0].add((char) (idx+'A'));
            if (tree[idx].left != -19) {
                getOrders(tree[idx].left, orders);
            }
            orders[1].add((char) (idx+'A'));
            if (tree[idx].right != -19) {
                getOrders(tree[idx].right, orders);
            }
            orders[2].add((char) (idx+'A'));
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_1991.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree tree = new Tree(n);
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tree.add(st.nextToken().charAt(0)-'A', st.nextToken().charAt(0)-'A',st.nextToken().charAt(0)-'A');
        }
        ArrayList<Character>[] result = new ArrayList[3];
        for (int i=0;i<3;i++) {
            result[i] = new ArrayList<>();
        }

        tree.getOrders(0,result);
        for (int i=0;i<3;i++) {
            for (char a:result[i]) System.out.printf("%c",a);
            System.out.println();
        }
    }
}
