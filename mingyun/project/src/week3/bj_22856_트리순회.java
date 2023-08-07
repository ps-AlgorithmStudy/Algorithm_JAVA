package project.src.week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_22856_트리순회 {

    static int cnt = 0;
    static int v = 1;
    static int N;

    public static class Node {
        boolean visit;
        Node parent;
        Node left;
        Node right;
    }

    public static void dfs(Node node, boolean isRight) {
        if (node.left!=null&&!node.left.visit) {
            cnt++;
            v++;
            node.left.visit = true;
            dfs(node.left, true);
        }
        if (node.right!=null&&!node.right.visit) {
            cnt++;
            v++;
            node.right.visit = true;
            dfs(node.right, isRight);
        }
        if (node.parent!=null){
            if (v==N && !isRight) return;
            cnt++;
        }
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_22856.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N+1];
        for (int i=1;i<=N;i++) {
            nodes[i] = new Node();
        }

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int childB = Integer.parseInt(st.nextToken());
            int childC = Integer.parseInt(st.nextToken());
            if (childB != -1) {
                nodes[childB].parent = nodes[parent];
                nodes[parent].left = nodes[childB];
            }
            if (childC != -1) {
                nodes[childC].parent = nodes[parent];
                nodes[parent].right = nodes[childC];
            }
        }
        nodes[1].visit = true;
        dfs(nodes[1], false);
        System.out.println(cnt);
    }
}
