import java.util.*;

class Solution_P_등산코스정하기 {
    
   Set<Integer> summitSet; 
   Set<Integer> gateSet;
    
   List<Node>[] graph;
   final int INF = Integer.MAX_VALUE / 2;
    
    
    static class Node implements Comparable<Node> {
        int key;
        int cost;
        
        Node(int key, int cost) {
            this.key = key; 
            this.cost = cost;
        }
        
        @Override
        public String toString() {
            return "Node{key: "+key+", cost: "+ cost+"}";
        }
        
        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost); // 오름차순
        }
        
    }
    
    // n in [2, 5만]
    // gates in [1, n]
    // summits in [1,n]
    // 출입구이면서 동시에 산봉우린 X
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // graph를 구성
        initGraph(n, paths);
        
        // O(1)에 산봉우리 / 게이트 여부를 확인하기 위해
        summitSet = new HashSet<>();
        for(int s : summits) {
            summitSet.add(s);
        }
        
        gateSet = new HashSet<>();
        for(int g : gates) {
            gateSet.add(g);
        }
        
        // 다익스트라 알고리즘
        // 1. 거리 기록 배열 및 INF 초기화
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        
        // 시작점을 모두 PQ에 넣어줌
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int g : gates) {
            pq.offer(new Node(g, 0));
            dist[g] = 0;
        }
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll(); // 현재 최소 비용을 갖는 정점
            
            // 만약 summit이라면 출입구부터 정상까지 왔으니 하산 경로의 intensity도 동일할 것.여기서 등산 끝
            // 안그러면 gate가 다른 경로로 갈 수도
            if(summitSet.contains(cur.key)) {
                continue;
            }
            
            // 현재 정점의 dist[cur.key]의 값보다 더 큰 w를 갖는다면
            // 주어진 문제 정의에 따라 등산할 필요가 없음
            if(dist[cur.key] < cur.cost) {
                continue;
            }
            
            
            for(Node adjV : graph[cur.key]) {
                // 현재 정점으로 왔던 최소 intensity와
                // 인접한 다음 정점으로 이동하는 비용(해당 경로의 intensity)에 대해 max를 찾아서
                // 해당 경로 중 가장 긴 이동 비용을 갖는 intensity를 갱신
                int d = Math.max(dist[cur.key], adjV.cost); 
                if(dist[adjV.key] > d) {
                    // 그렇게 했더니 다음 정점에 대한 최소 intensity를 갱신해야하는 경우라면
                    dist[adjV.key] = d;
                    pq.offer(new Node(adjV.key, d));
                }
            }
            
        }
        
        
        // 결과 반환을 위해
        int[] answer = new int[]{-1, INF};
        Arrays.sort(summits);
        for(int s : summits) {
            if(dist[s] < answer[1]) {
                answer[0] = s;
                answer[1] = dist[s];
            }
        }
        
        return answer; // 산봉우리 번호, intensity 최소값 반환
    }
    
    void initGraph(int n, int[][] paths) {
        // graph를 구성
        graph = new List[n + 1]; // 1'based indexing 
        
        for(int[] path : paths) {
            int i = path[0], j = path[1], w = path[2];
            if(graph[i] == null) {
                graph[i] = new ArrayList<>();
            }
            
            if(graph[j] == null) {
                graph[j] = new ArrayList<>();
            }
            
            graph[i].add(new Node(j, w));
            graph[j].add(new Node(i, w));
        }
    }
    
}