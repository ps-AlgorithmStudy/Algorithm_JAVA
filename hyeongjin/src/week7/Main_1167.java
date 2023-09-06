import java.io.*;
import java.util.*;

class NodeA{
    int next;
    int dist;
    NodeA(int next, int dist){
        this.next = next;
        this.dist = dist;
    }
}
public class Main_1167 {
    static int ans;
    static int far;
    static int V;
    static ArrayList<NodeA>[] tree;
    static boolean[] visited;
    static void DFS(int node, int dist){
        visited[node] = true;
        if(ans < dist){
            ans = dist;
            far = node;
        }
        for (int i = 0; i < tree[node].size(); i++) {
            NodeA connected = tree[node].get(i);
            if(!visited[connected.next]){
                DFS(connected.next, dist + connected.dist);
            }
        }
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V+1];

        visited = new boolean[V+1];
        for (int i = 0; i <=V; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i <=V; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());
                if(end ==-1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[start].add(new NodeA(end, cost));
            }
        }
        DFS(1,0);
        visited = new boolean[V+1];
        DFS(far,0);
        System.out.println(ans);
    }
}