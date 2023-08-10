package brute_force_search;

import java.util.*;
import java.io.*;

public class week3_bj_25760 {

    static int N;
    static int carNum;
    static ArrayList<Integer>[] list;
    static byte[] carPos;
    static boolean[] visited;
    static ArrayDeque<Integer> queue;
    static int cnt;

    static void bfs(){
        queue = new ArrayDeque<>();
        visited = new boolean[N];

        if (carPos[0] == 1){
            carPos[0] = 0;
            carNum--;
        }

        int tmpNum = 0;
        if (tmpNum == carNum) return;

        visited[0] = true;
        queue.offer(0);
        while (!queue.isEmpty()){
            int parent = queue.poll();

            Iterator<Integer> it = list[parent].iterator();
            while (it.hasNext()){
                int i = it.next();
                if (visited[i]) continue;
                visited[i] = true;
                queue.offer(i);
                if (carPos[i] == 1)
                {
                    tmpNum++;
                    if (carPos[parent] == 0){
                        carPos[parent] = 1;
                        carPos[i] = 0;
                    }
                }
            }
            if (tmpNum == carNum) break;
        }
    }

//    static void dfs(int n)
//    {
//        if (n == 0){
//            if (carPos[0] == 1){
//                carPos[0] = 0;
//                carNum--;
//            }
//        }
//
//
//    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        carPos = new byte[N];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            list[a].add(b);
            list[b].add(a);
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            carPos[i] = Byte.parseByte(st.nextToken());
            if (carPos[i] == 1)
                carNum++;
        }

        while (carNum != 0){
            bfs();
            cnt++;
        }

        System.out.println(cnt);
    }
}

/*
bfs 사용
루트 노드 (1) 부터 탐색 시작
visit 한 노드에 차가 있으면 & parent node에 차가 없다면 parent node 로 차를 옮김.
child node 가 비었으니 child node 탐색해서 child->child node 에 차가 있다면 child node 에 차를 옮김.

모든 차가 나올때까지!
 */