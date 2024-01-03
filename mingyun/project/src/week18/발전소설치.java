package week18;

import java.io.*;
import java.util.*;

public class 발전소설치 {

    static final int INF = Integer.MAX_VALUE;
    static int n, w;
    static double m;
    static int[][] pos;
    static ArrayList<ArrayList<Node>> nodes;

    static class Node {
        int id;
        double cost;

        public Node(int id, double cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        pos = new int[n + 1][2];
        m = Double.parseDouble(br.readLine().trim());
        nodes = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double dist = getDistance(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
                if (dist <= m) {
                    nodes.get(i).add(new Node(j, dist));
                    nodes.get(j).add(new Node(i, dist));
                }
            }
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes.get(a).add(new Node(b, 0));
            nodes.get(b).add(new Node(a, 0));
        }

        System.out.println(Dijkstra());
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static int Dijkstra() {
        double[] distances = new double[n + 1];
        Arrays.fill(distances, INF);
        distances[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distances[current.id] < current.cost) continue;

            for (Node next : nodes.get(current.id)) {
                if (distances[next.id] > current.cost + next.cost) {
                    distances[next.id] = current.cost + next.cost;
                    pq.add(new Node(next.id, distances[next.id]));
                }
            }
        }
        return (int) (distances[n] * 1000);
    }
}
