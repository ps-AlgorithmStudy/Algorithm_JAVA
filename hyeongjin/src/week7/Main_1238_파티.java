package week7;

import java.io.*;
import java.util.*;
public class Main_1238_파티 {
    static class Node implements Comparable{
        int vertex;
        int weight;

        Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            Node O = (Node)o;
            return weight - O.weight;
        }
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int R = 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>();

        int[] goParty = new int[N+1];
        int[] goHome  = new int[N+1];

        Arrays.fill(goParty, Integer.MAX_VALUE);
        Arrays.fill(goHome , Integer.MAX_VALUE);

        goParty[X] = 0;
        goHome[X] = 0;

        ArrayList<Node>[] edgeForward = new ArrayList[N+1];
        ArrayList<Node>[] edgeReverse = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            edgeForward[i] = new ArrayList<>();
            edgeReverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int depart = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            int cost   = Integer.parseInt(st.nextToken());

            edgeForward[depart].add(new Node(arrive,cost));
            edgeReverse[arrive].add(new Node(depart,cost));
        }
        // x로  출발
        q.add(new Node(X,0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            int vertex = cur.vertex;
            for (int i = 0; i < edgeForward[vertex].size(); i++) {
                int nextV = edgeForward[vertex].get(i).vertex;
                int nextW = edgeForward[vertex].get(i).weight;
                if(goParty[nextV] > goParty[vertex] + nextW){
                    goParty[nextV] = goParty[vertex] + nextW;
                    q.add(new Node(nextV, goParty[nextV]));
                }
            }
        }

        // x에서 출발
        q.add(new Node(X,0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            int vertex = cur.vertex;
            for (int i = 0; i < edgeReverse[vertex].size(); i++) {
                int nextV = edgeReverse[vertex].get(i).vertex;
                int nextW = edgeReverse[vertex].get(i).weight;
                if(goHome[nextV] > goHome[vertex] + nextW){
                    goHome[nextV] = goHome[vertex] + nextW;
                    q.add(new Node(nextV, goHome[nextV]));
                }
            }
        }

        for (int i = 1; i <=N ; i++) R = Math.max( R ,  goParty[i] + goHome[i] );
        System.out.println(R);
    }
}

