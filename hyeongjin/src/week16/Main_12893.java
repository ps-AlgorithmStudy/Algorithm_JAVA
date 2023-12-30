package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_12893 {
    static int N, M ;
    static List<Integer>[] adj;
    static int[] v;
    static boolean isBipartite;
    static void dfs(int start, int check){
        v[start] = check;
        for(int a : adj[start]){
            if(v[a] == check){
                isBipartite = false;
                return;
            }

            if(v[a] == 0){
                dfs(a,-check);
            }
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new List[N+1];
        v = new int[N+1];
        Arrays.fill(v, 0);

        isBipartite = true;
        for (int i = 0; i <= N ; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 1; i <=N ; i++) {
            if(!isBipartite) break;

            if(v[i] == 0) dfs(i, 1);
        }

        System.out.println(isBipartite ? 1 : 0);
    }
}
