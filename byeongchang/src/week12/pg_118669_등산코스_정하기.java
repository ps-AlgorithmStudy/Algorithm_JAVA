package week12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/118669
public class pg_118669_등산코스_정하기 {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;  // 최소 intensity를 구하기 위한 초기화
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        HashSet<Integer> summit = new HashSet<>();
        for(int s: summits) summit.add(s);
        HashSet<Integer> gate = new HashSet<>();
        for(int g: gates) gate.add(g);
        for(int[] p: paths){
            // 출입구와 산봉우리는 단방향
            if(gate.contains(p[0]) || summit.contains(p[1])) {
                graph[p[0]].add(new int[]{p[1],p[2]});
            }
            else if(gate.contains(p[1]) || summit.contains(p[0])) {
                graph[p[1]].add(new int[]{p[0],p[2]});
            }
            // 나머지는 양방향
            else {
                graph[p[1]].add(new int[]{p[0],p[2]});
                graph[p[0]].add(new int[]{p[1],p[2]});
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));   // node, intensity
        pq.clear();
        int[] intensity = dijkstra(gates,graph,new boolean[n+1],pq);
        Arrays.sort(summits);
        for(int s: summits) {
            if(intensity[s]<answer[1]) {
                answer[1] = intensity[s];
                answer[0] = s;
            }
        }
        return answer;
    }

    // max intensity를 저장하는 다익스트라 알고리즘
    public int[] dijkstra(int[] gates, ArrayList<int[]>[] graph, boolean[] visited, PriorityQueue<int[]> pq) {
        int[] intensity = new int[visited.length];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int g: gates) {
            pq.offer(new int[]{g, 0});
            intensity[g] = 0;
        }
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            visited[node] = true;
            for(int[] g: graph[node]) {
                int next = g[0];
                if(visited[next]) continue;  // 이미 방문한 지역이면 건너뜀
                if(intensity[next] > Math.max(intensity[node], g[1])) {
                    intensity[next] = Math.max(intensity[node], g[1]);
                    pq.offer(new int[]{next, intensity[next]});
                }
            }
        }
        return intensity;
    }


    public static void main(String[] args) throws Exception {
        TestCase tc1 = new TestCase(6,
                                    new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                                    new int[]{1, 3},
                                    new int[]{5},
                                    new int[]{5, 3});
        TestCase tc2 = new TestCase(7,
                                    new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                                    new int[]{1},
                                    new int[]{2, 3, 4},
                                    new int[]{3, 4});
        TestCase tc3 = new TestCase(7,
                                    new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}},
                                    new int[]{3, 7},
                                    new int[]{1, 5},
                                    new int[]{5, 1});
        TestCase tc4 = new TestCase(5,
                                    new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}},
                                    new int[]{1, 2},
                                    new int[]{5},
                                    new int[]{5, 6});
        pg_118669_등산코스_정하기 s = new pg_118669_등산코스_정하기();
        for(TestCase tc: new TestCase[]{tc1,tc2,tc3,tc4}) {
            System.out.println("sol: "+Arrays.toString(s.solution(tc.n,tc.paths,tc.gates,tc.summits))+", res: "+Arrays.toString(tc.result));
        }
    }

    static class TestCase {
        int n;
        int[][] paths;
        int[] gates;
        int[] summits;
        int[] result;

        public TestCase() {
        }

        public TestCase(int n, int[][] paths, int[] gates, int[] summits, int[] result) {
            this.n = n;
            this.paths = paths;
            this.gates = gates;
            this.summits = summits;
            this.result = result;
        }

    }
}
