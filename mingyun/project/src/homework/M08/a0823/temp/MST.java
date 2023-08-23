package homework.M08.a0823.temp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST {
    static class Edge {
        int from, to, weight;
        Edge(int from, int to,int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static Edge[] edges;
    static int V,E;
    static int[] parents;
    static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edges = new Edge[E];
            for (int i =0; i<E;i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken())-1;
                int to = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to ,weight);
            }
            Arrays.sort(edges, ((o1, o2) -> Integer.compare(o1.weight, o2.weight)));
            make();

            int cnt = 0;
            int result = 0;

            for (Edge e:edges) {
                if (union(e.from, e.to)) {
                    result += e.weight;
                    if (++cnt == V-1) break;
                }
            }
            System.out.println("#" + TC + " " + result);
        }

    }
}
