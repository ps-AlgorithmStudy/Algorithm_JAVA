package week5.my;

import java.io.*;
import java.util.*;

public class bj_13023_ABCDE {
    static int n,m;

    static class Relation {
        int A, B;
        Relation(int a, int b) {
            A = a; B = b;
        }
    }

    public static boolean dfs(int p, int cnt, ArrayList<Relation>[] node, int[] v) {
        if (cnt==4) return true;
        for (Relation relation:node[p]) {
            int dir = relation.A==p?relation.B: relation.A;
            if (v[dir]==0) {
                v[dir] =1;
                if (dfs(dir, cnt+1, node, v)) return true;
                v[dir] = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_13023.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

        ArrayList<Relation>[] node = new ArrayList[n];
        for (int i=0;i<n;i++) node[i] = new ArrayList<>();
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Relation relation = new Relation(a,b);
            node[a].add(relation);
            node[b].add(relation);
        }

        for (int i=0;i<n;i++) {
            int[] v = new int[n];
            v[i] = 1;
            if (dfs(i,0,node, v)) {
                System.out.println(1);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

}