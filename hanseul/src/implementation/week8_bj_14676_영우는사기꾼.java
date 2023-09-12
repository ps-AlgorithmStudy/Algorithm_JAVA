package implementation;

import java.io.*;
import java.util.*;
public class week8_bj_14676_영우는사기꾼 {

    static int N, M, K;
    static ArrayList<Integer>[] build;
    static Set<Integer>[] checkBuild;
    static int[] curbuildings;
    static int[] indegrees;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        build = new ArrayList[N];
        checkBuild = new Set[N];
        curbuildings = new int[N];
        indegrees = new int[N];

        for (int i = 0; i < N; i++) {
            build[i] = new ArrayList<>();
            checkBuild[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            build[from].add(to);
            indegrees[to]++;
        }


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int mode = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken()) - 1;

            if (mode == 1){
                if (indegrees[building] == checkBuild[building].size()){
                    curbuildings[building]++;
                    for(int b : build[building]){
                        checkBuild[b].add(building);
                    }
                }else{
                    System.out.println("Lier!");
                    return;
                }
            }else{
                if (curbuildings[building] == 0)
                {
                    System.out.println("Lier!");
                    return;
                }
                else {
                    curbuildings[building]--;
                    if (curbuildings[building] == 0){
                        for(int b : build[building]){
                            checkBuild[b].remove(building);
                        }
                    }
                }
            }
        }

        System.out.println("King-God-Emperor");
    }
}
