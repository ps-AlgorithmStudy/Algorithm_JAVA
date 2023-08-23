package week4;
import java.util.*;
import java.io.*;

public class Main_5639{
    static class Node {
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val= val;
        }
        public void setLeft(Node node) {
            this.left = node;
        }
        public void setRight(Node node) {
            this.right = node;
        }
        public void add(Node node){
            if(node.val<this.val) {
                if(left == null) setLeft(node);
                else left.add(node);
            }
            if(node.val>this.val) {
                if(right == null) setRight(node);
                else right.add(node);
            }
        }
    }
    static void postOrder(Node node) {
        if(node. left != null) postOrder(node.left);
        if(node.right != null) postOrder(node.right);
        System.out.println(node.val);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));
        while(true) {
            String input = br.readLine();
            if(input == null || input.equals("")) break;
            root.add(new Node(Integer.parseInt(input)));
        }
        postOrder(root);
        br.close();
    }
}
