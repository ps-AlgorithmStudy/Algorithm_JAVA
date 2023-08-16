package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_5639_이진_검색_트리 {
    static class Node {
        int value;
        Node left,right;
        Node(int value) {
            this.value = value;
        }
        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        void add(int n) {
            if(this.value > n) {
                if(this.left == null){
                    this.left = new Node(n);
                }
                else this.left.add(n);
            }
            else {
                if(this.right == null) {
                    this.right = new Node(n);
                }
                else this.right.add(n);
            }
        }

    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/bj_5639.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        solution(br, sb);
        br.close();
    }
    public static void solution(BufferedReader br, StringBuilder sb) throws Exception {
        Node tree = new Node(Integer.parseInt(br.readLine()));
        while(br.ready()) {
            String temp = br.readLine();
            if(temp == null || temp.isEmpty() || temp.equals("\n")) break;
            tree.add(Integer.parseInt(temp));
        }
        postOrder(tree, sb);
        System.out.println(sb);
    }
    public static void postOrder(Node tree, StringBuilder sb) {
        if( tree== null) return;
        postOrder(tree.left, sb);
        postOrder(tree.right, sb);
        sb.append(tree.value).append("\n");
    }
}
