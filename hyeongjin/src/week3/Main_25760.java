package week3;

import java.io.*;
import java.util.*;

public class Main_25760 {
    // 실패
    // 1. 트리 순회
    // 2. children.size()==2 ->  left & right 차 갯수 비교 -> count depth
    // 3.  max : max(왼쪽 depth, 오른쪽 depth) 차의 갯수 : cars
    // cars > max -> cars 부모노드에 cars 전달
    // else 부모노드에 max 전달
    static class Node{
        int index;
        boolean isCar;
        List<Node> children;
        public Node(int index){
            this.index = index;
            this.children = new ArrayList<>();
        }
        public void addChild(Node child){
            children.add(child);
        }
    }
    static int max = 0;
    static int bottleneck = 0;
    static Node[] nodes;
    static void BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(nodes[1]);

        while(!queue.isEmpty());
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n1,n2;

        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N+1];

        for(int i = 1; i<=N; i++) nodes[i] = new Node(i);
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            if(n1<n2) nodes[n1].addChild(nodes[n2]);
            if(n2<n1) nodes[n2].addChild(nodes[n1]);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if(Integer.parseInt(st.nextToken()) == 1){
                nodes[i+1].isCar = true;
            }
        }
        BFS();
        System.out.println(max+bottleneck);
    }

}
