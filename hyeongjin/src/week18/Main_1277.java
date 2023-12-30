package week18;

import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main_1277 {
    static int N, W;
    static double M;

    static int[][] nodes;
    static Double[][] distance;

    static class Node implements Comparable<Node>{
        int no;
        double dist;

        Node(int no, double dist){
            this.no = no;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n){
            return Double.compare(this.dist, n.dist);
        }
    }
    static double dSquare(int[] p1, int[] p2){
        double d  = (double)(p1[0]-p2[0])*(p1[0]-p2[0]);
        d += (double)(p1[1]-p2[1])*(p1[1]-p2[1]);
        return d;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        nodes = new int[N][2];
        distance = new Double[N][N];

        // 각각의 노드에 대한 거리 담기
        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());
            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < i; j++) {
                double tmp = dSquare(nodes[i], nodes[j]);
                if(tmp <= M*M){
                    distance[i][j] = Math.sqrt(tmp);
                    distance[j][i] = Math.sqrt(tmp);
                }
            }
        }

        // 이미 이어진 노드 간 거리를 0으로 하자
        for (int i = 0; i < W; i++) {
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) -1;
            int b = Integer.parseInt(st.nextToken())-1;

            distance[a][b] = 0.0;
            distance[b][a] = 0.0;
        }

        // dijkstra

        double[] minDist = new double[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            minDist[i] = Double.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble((n -> n.dist)));
        pq.add(new Node(0,0.0));

        while(!pq.isEmpty()){
            Node n = pq.poll();
            int no = n.no;
            double dist = n.dist;

            if(minDist[no] < dist) continue;

            for (int i = 0; i < nodes.length; i++) {
                if(distance[no][i] == null) continue;

                if(dist + distance[no][i] < minDist[i]){
                    minDist[i] = dist + distance[no][i];
                    pq.add(new Node(i,minDist[i]));
                }

            }
        }
        int result = (int) (minDist[nodes.length-1]*1000);
        System.out.println(result);
    }
}
