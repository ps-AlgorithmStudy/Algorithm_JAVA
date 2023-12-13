package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week16_bj_12893_적의적 {

    static int N, M;
    static int v[];
    static ArrayList<Integer>[] g;

    static boolean dfs(int n, int depth){
        v[n] = depth;
        boolean res = true;

        for(int i : g[n]){
            if (v[i] == 0){ // 첫 방문일 경우
                res = dfs(i, depth + 1);
            }
            else{ // 이미 방문한 곳
                if (v[i] % 2  == depth % 2){ // 적의적관계가 이루어지지 않는다면
                    return false;
                }
            }
            if (!res) return res;
        }
        return res;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N];
        v = new int[N];

        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            g[a].add(b);
            g[b].add(a);
        }

        int result = 1;
        for (int i = 0; i < N; i++) {
            if (v[i] == 0) {
                if (!dfs(i, 1)){
                    result = 0;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}


//11:13
//1t : 11:35
//2t : 11:53
