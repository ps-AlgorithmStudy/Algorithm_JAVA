package homework.M08.a0824;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 최단경로 {

    static class Edge {
        int end, value;
        Edge(int b, int c) {
            end = b; value = c;
        }
    }

    static class Input {
        int V, E, start;
        ArrayList<Edge>[] node;
        int[] distance;
        Input() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0824/res/최단경로.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(br.readLine());

            node = new ArrayList[V+1];
            distance = new int[V+1];

            for (int i=0;i<=V;i++) {
                node[i] = new ArrayList<>(100);
                distance[i] = Integer.MAX_VALUE;
            }

            for (int i=0;i<E;i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                node[start].add( new Edge(end, value));
            }
            br.close();
        }
    }

    static class Work {
        Input input;

        public void dijkstra() {
            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.value, o2.value));
            input.distance[input.start] = 0;
            pq.offer(new Edge(input.start, 0));

            while(!pq.isEmpty()) {
                Edge cur = pq.remove();
                int curNode = cur.end;
                int curDist = cur.value;

                if(input.distance[curNode] < curDist) continue;

                for(Edge edge : input.node[curNode]) {
                    int nextNode = edge.end;
                    int nextDist = edge.value;

                    if(input.distance[nextNode] > curDist + nextDist) {
                        input.distance[nextNode] = curDist + nextDist;
                        pq.offer(new Edge(nextNode, input.distance[nextNode]));
                    }
                }
            }
            for (int i=1;i<= input.V;i++) {
                if (input.distance[i] != Integer.MAX_VALUE) {
                    System.out.println(input.distance[i]);
                }
                else {
                    System.out.println("INF");
                }
            }
        }
        Work(Input input) {
            this.input = input;
        }
    }
    public static void main(String[] args) throws Exception {
        Work work = new Work(new Input());
        work.dijkstra();
    }
}