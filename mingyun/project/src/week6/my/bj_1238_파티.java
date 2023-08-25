package week6.my;

import java.io.*;
import java.util.*;

public class bj_1238_파티 {
    static class Work  {

        class Edge {
            int end, value;
            Edge (int a, int b) {
                end = a; value= b;
            }
        }

        int n, m, x;
        ArrayList<ArrayList<Edge>> node;

        Work() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/week6/res/input_bj_1238.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken()); x = Integer.parseInt(st.nextToken());

            node = new ArrayList<>(n+1);
            for (int i=0;i<=n;i++) {
                node.add(new ArrayList<>());
            }

            for (int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                node.get(Integer.parseInt(st.nextToken()))
                        .add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
            }
        }

        int run() {
            ArrayList<int[]> arrays = new ArrayList<>(100);
            arrays.add(null);
            for (int d=1;d<=n;d++) {
                int[] distance = new int[n+1];
                for (int i=0;i<=n;i++) distance[i] = Integer.MAX_VALUE;
                dijkstra(distance, d);
                arrays.add(distance);
            }
            int result = 0;
            for (int d=1;d<=n;d++) {
                result = Math.max(result,arrays.get(d)[x] + arrays.get(x)[d]);
            }
            return result;
        }

        void dijkstra(int[] distance, int startNode) {
            PriorityQueue<Edge> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.value)));
            boolean[] v = new boolean[n+1];
            distance[startNode] = 0;
            v[0] = true;
            pq.add(new Edge(startNode,0));

            while (!pq.isEmpty()) {
                Edge now = pq.remove();
                int targetNode = now.end;
                int targetDistance = now.value;

                if (v[targetNode]) continue;
                v[targetNode] = true;

                for (Edge edge : node.get(targetNode)) {
                    int nextNode = edge.end;
                    int nextDistance = edge.value;
                    if (!v[nextNode] && distance[nextNode] > targetDistance + nextDistance) {
                        distance[nextNode] = targetDistance + nextDistance;
                        pq.add(new Edge(nextNode, distance[nextNode]));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Work work = new Work();
        System.out.println(work.run());
    }
}