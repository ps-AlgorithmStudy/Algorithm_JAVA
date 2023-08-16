package tree;

import java.util.*;
import java.io.*;

public class week4_bj_5639 {

    static ArrayDeque<Integer> stack = new ArrayDeque<>();

    static StringBuilder sb = new StringBuilder();
    static class Node {
        int num;
        Node left, right;

        Node(int num){
            this.num = num;
        }

        void insert(int n){
            if (n < this.num){
                if (this.left == null)
                    this.left = new Node(n);
                else this.left.insert(n);
            }
            else{
                if (this.right == null)
                    this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }


    static void postOrder(Node node)
    {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.num).append("\n");
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        Node tree = new Node(Integer.parseInt(br.readLine()));

        while ((input = br.readLine()) != null && !input.isEmpty()){
            tree.insert(Integer.parseInt(input));
        }
        postOrder(tree);
        System.out.println(sb.toString());
    }


}
