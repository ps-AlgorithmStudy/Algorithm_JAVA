package project.src.week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_5639_이진검색트리 {
    static class Tree {
        static class Node {
            int value;
            Node left;
            Node right;
            Node(int value) {
                this.value = value;
            }
        }

        Node root;

        Tree(int value) {
            root = new Node(value);
        }

        void addSearch(Node parent, Node child) {
            if (parent.value > child.value) {
                if (parent.left==null) parent.left = child;
                else addSearch(parent.left, child);
            }
            else {
                if (parent.right==null) parent.right = child;
                else addSearch(parent.right, child);
            }
        }

        void orderSearch(Node node, StringBuilder sb) {
            if (node.left!=null) {
                orderSearch(node.left, sb);
            }
            if (node.right!= null) {
                orderSearch(node.right, sb);
            }
            sb.append(node.value).append("\n");
        }

        void order(StringBuilder sb) {
            orderSearch(root, sb);
        }

        void add(int value) {
            Node node = new Node(value);
            addSearch(root, node);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_5639.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Tree tree = new Tree(Integer.parseInt(br.readLine()));
        while (true) {
            String temp = br.readLine();
            if (temp==null) break;
            tree.add(Integer.parseInt(temp));
        }
        tree.order(sb);
        System.out.print(sb);
    }
}
