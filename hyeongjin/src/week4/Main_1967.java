package week4;

import java.util.*;
import java.io.*;

public class Main_1967 {
    static int Max;
    static boolean isInit = true;
    static void solve(List<int[]>[] tree, boolean[] v,int idx,int dist) {
        // idx : 부모 index 4
        v[idx] = true;
        if(!isInit &&tree[idx].size()==1) {
            // 리프노드일 때
//            System.out.printf("종료노드 %d, 거리 : %d\n",idx,dist);
            Max = Math.max(Max, dist);
            return;
        }
        for (int i = 0; i < tree[idx].size(); i++) {
            // ij : 자식노드 4 : (2,5) (7,1), (8,7)
            int[] ij = tree[idx].get(i);
            if(!v[ij[0]]){ // 2, 7, 8 방문했는지 CHECK, 7은 방문
                // 방문처리 후
                v[ij[0]] = true;
                // 2번으로
                // tree, v,
                isInit = false;
                solve(tree,v,ij[0],dist + ij[1]);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        List<int[]>[] tree = new ArrayList[T+1];
        boolean[] v;
        for (int i = 0; i < T+1; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < T-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree[a].add(new int[] {b,w});
            tree[b].add(new int[] {a,w});
        }
        for (int i = 1; i < tree.length; i++) {
            if(tree[i].size()==1) {
                // 7을 방문처리
                isInit = true;
                v = new boolean[T+1];
                v[i] = true;
//                System.out.println("---출발노드 : "+i+"---");
                // tree, v, 4, 1
                solve(tree,v,tree[i].get(0)[0],tree[i].get(0)[1]);
                v[i] = false;
            }
        }
        System.out.println(Max);
        br.close();
    }
}

