package week18;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1277
public class bj_1277_발전소_설치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }

    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        double M = Double.parseDouble(br.readLine());
        int[][] plant = new int[N+1][2];   // 발전소 위치 저장, i : 발전소 번호, i[0] : x좌표, i[1] : y좌표
        int[][] connect = new int[N+1][N+1];   // 발전소 연결 여부 저장 1 : 연결, 0 : 연결X
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            plant[i][0] = x;
            plant[i][1] = y;
        }
        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connect[x][y] = 1;
            connect[y][x] = 1;
        }

        double min = dijkstra(1, M, connect, plant);

        return (int) (min*1000);
    }

    private static double dijkstra(int start, double M, int[][] connect, int[][] plant) {
        boolean[] visited = new boolean[connect.length];   // 발전소 방문 여부
        double[] line = new double[connect.length];   // 발전소 번호까지 연결에 필요한 전선 길이
        PriorityQueue<Node> pq = new PriorityQueue<>();    // 현재 발전소 번호, 지금까지 사용한 전선 길이
        Arrays.fill(line, Double.MAX_VALUE);
        line[start] = 0;
        pq.offer(new Node(start, line[start]));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.plant]) continue;   // 이미 더 짧은 전선을 사용해 연결한 경우
            if(cur.plant == connect.length-1) return cur.use;   // 마지막 발전소에 도착한 경우
            visited[cur.plant] = true;   // 현재 발전소 방문 처리
            for(int i=1; i<connect.length; i++) {
                if(visited[i]) continue;   // 이미 방문한 발전소인 경우
                if(connect[cur.plant][i] == 1) {   // 연결된 발전소인 경우
                    line[i] = Math.min(line[i], line[cur.plant]);   // i번째 발전소까지 필요한 전선 길이와, cur번째 발전소까지 필요한 전선 길이 중 작은 값으로 갱신 ( cur번째 발전소에서 i번째 발전소 연결에 필요한 전선은 없음)
                    pq.offer(new Node(i, line[i]));
                    continue;
                }
                double dist = getDist(plant[cur.plant], plant[i]);
                if(Double.compare(dist, M) > 0) continue;   // 거리가 M보다 큰 경우
                line[i] = Math.min(line[i], cur.use + dist);   // i번째 발전소까지 필요한 전선 길이와, cur번째 발전소까지 필요한 길이 + cur번째 발전소와 i번째 발전소 사이의 거리 중 작은 값으로 갱신
                pq.offer(new Node(i, line[i]));
            }
        }
        return line[connect.length-1];
    }

    private static double getDist(int[] p1, int[] p2){
        return Math.sqrt(Math.pow(p1[0]-p2[0],2)+Math.pow(p1[1]-p2[1],2));
    }

    static class Node implements Comparable<Node>{
        int plant;   // 발전소 번호
        double use;   // 사용한 전선 길이

        public Node(int plant, double use){
            this.plant = plant;
            this.use = use;
        }

        @Override
        public int compareTo(Node o){
            return Double.compare(this.use, o.use);
        }
    }
}
