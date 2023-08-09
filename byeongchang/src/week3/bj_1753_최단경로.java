package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1753_최단경로 {

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/bj_1753.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        solution(br, st);
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());  // 정점의 개수
        int E = Integer.parseInt(st.nextToken());  // 간선의 개수
        int K = Integer.parseInt(br.readLine())-1;  // 시작 정점, 배열 인덱스를 위해 -1
        ArrayList<Node>[] graph = new ArrayList[V];
        for(int i=0;i<V;i++) graph[i] = new ArrayList<>();
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;  // 출발, 배열 인덱스를 위해 -1
            int v = Integer.parseInt(st.nextToken())-1;  // 도착, 배열 인덱스를 위해 -1
            int w = Integer.parseInt(st.nextToken());  // 가중치
            graph[u].add(new Node(v,w));
        }
        dijkstra(K, graph, V);

    }
    /*
    다익스트라
    1. 방문하지 않은 정점 중 출발 정점과 직접 연결된 정점 방문
    2. 방문한 정점을 거쳐서 갈 수 있는 정점의 거리가 이전에 기록한 값보다 적으면 갱신
     */
    public static void dijkstra(int K, ArrayList<Node>[] graph, int V){
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> pq = new PriorityQueue<>();  // 최단 거리 판별용 우선순위 큐
        int[] dist = new int[V]; // 최단 거리 저장 배열
        boolean[] visited = new boolean[V];  // 방문 확인 배열
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(K,0));  // 출발 정점 큐 삽입
        dist[K] = 0;  // 출발 정점임으로 거리 0
        while(!pq.isEmpty()) {
            int idx = pq.poll().index;
            if(visited[idx]) continue; // 이미 방문했던 정점이면 스킵
            visited[idx] = true;  // 방문 처리
            for(Node n: graph[idx]) {
                if(dist[n.index] <= dist[idx]+n.cost) continue;  // 기록된 거리가 방문한 정점을 거쳐서 간 거리보다 작으면 패스(= 방문한 정점을 거쳐서 가는 것이 돌아가는 것이기 때문)
                dist[n.index] = dist[idx]+n.cost;
                pq.offer(new Node(n.index, dist[n.index]));
            }
        }
        for(int d: dist) {
            sb.append(d!=Integer.MAX_VALUE?d:"INF").append("\n");
        }
        System.out.println(sb);
    }

}
/*
우선순위 큐 사용을 위한 클래스
클래스 대신 int 배열 사용 가능. 대신 우선순위 큐에 적절한 람다식을 넣어야 함
 */
class Node implements Comparable<Node> {
    int index;
    int cost;
    public Node(int index, int cost){
        this.index = index;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o){
        return Integer.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("index=").append(index);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
