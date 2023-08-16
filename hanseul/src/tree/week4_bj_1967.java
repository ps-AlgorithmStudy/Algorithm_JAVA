package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class week4_bj_1967 {

    static int N;
    static Node tree;

    static class Node{
        int num;
        int leftEdge, rightEdge;
        Node left, right;
        ArrayList<Node> children;
        ArrayList<Integer> edge;

        Node(int num){
            this.num = num;
            children = new ArrayList<>();
            edge = new ArrayList<>();
        }

    }

    static Node searchNode(Node node, int num){
        if (node == null) return null;
        if (num == node.num) return node;

        for(Node child : node.children){
            Node tmp = searchNode(child, num);
            if (tmp != null) return node;
        }
        return null;
    }

    static void insertNode(int parent, int child, int vertex){
        Node node = searchNode(tree, parent);

        System.out.println(node.num);
        node.children.add(new Node(child));
        node.edge.add(vertex);
    }

//    static int getLeftChild(Node node){
//        if (node == null) return 0;
//        if (node.leftEdge == 0) return 0;
//        return node.leftEdge + getLeftChild(node.left);
//    }
//
//    static int getRightChild(Node node){
//        if (node == null) return 0;
//        if (node.rightEdge == 0) return 0;
//        return node.rightEdge + getRightChild(node.right);
//    }


    static int[] max = new int[2];

    static void dfs(Node node, int sum){
        if (node.children.size() == 0){
//            System.out.println(sum);
            if (max[0] < sum){
                max[1] = max[0];
                max[0] = sum;
            }
            else if (max[1] < sum){
                max[1] = sum;
            }
        }
        for (int i = 0; i < node.edge.size(); i++) {
            dfs(node.children.get(i), sum + node.edge.get(i));
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int parent, child, vertex;
        st = new StringTokenizer(br.readLine(), " ");
        parent = Integer.parseInt(st.nextToken());
        child = Integer.parseInt(st.nextToken());
        vertex = Integer.parseInt(st.nextToken());

        tree = new Node(parent);
//        tree.leftEdge = vertex;
//        tree.left = new Node(child);
        tree.children.add(new Node(child));
        tree.edge.add(vertex);

        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());
            vertex = Integer.parseInt(st.nextToken());
            insertNode(parent, child, vertex);
        }

        searchNode(tree, 2);


        int result = 0;
        for (int i = 1; i <= N ; i++) {
            Node node = searchNode(tree, i);
            if (node.rightEdge == 0 && node.leftEdge == 0) continue;
            max = new int[2];
            dfs(node, 0);
            int tmp = max[0] + max[1];
            result = (result < tmp) ? tmp : result;
        }
        System.out.println(result);


    }
}
