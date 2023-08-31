package SSA;

import java.util.*;
import java.io.*;

public class week6_bj_17142_연구소3 {

    static int N, M;
    static int[][] arr;
    static int[] b;
    static int room = 0;

    static int[] dx = {0, 0, -1 ,1};
    static int[] dy= {1, -1, 0, 0};
    static ArrayList<int[]> virusList = new ArrayList<>(); // virus 위치가 될 수 있는 position list
    static int min = -1;
    static void comb(int cnt, int start){
        if (cnt == M){ // M 개를 뽑았을 때
            int time = bfs();
            if (min == -1) min = time;
            if (time != -1) min = (min < time) ? min : time;
            return;
        }
        for (int i = start; i < virusList.size(); i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static int bfs(){
        int depth = 0;
        int virus = 0; // virus 로 변한 방의 개수
        boolean[][] v = new boolean[N][N];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < M; i++) { // M 개를 뽑은 바이러스의 위치를 큐에 저장
            v[virusList.get(b[i])[0]][virusList.get(b[i])[1]] = true;
            queue.offer(new int[] { virusList.get(b[i])[0],virusList.get(b[i])[1],0});
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if (arr[cur[0]][cur[1]] == 0)
                depth = (depth > cur[2]) ? depth : cur[2]; // depth 를 max depth 값으로 갱신
            for (int i = 0; i < 4; i++) { // 4방탐색
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;// range 처리
                if (v[tx][ty]) continue; // visited 처리
                if (arr[tx][ty] == 1) continue;// 벽 처리
                if (arr[tx][ty] == 0) virus++;// 바이러스로 변한 방의 개수++ 비활성 바이러스는 굳이 채우지 않아도 됨.
                v[tx][ty] = true;
                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
        }

        if (virus == room) return depth; // 모든 방이 바이러스로 바꼈다면 return depth
        return -1; // 아니라면 -1 return
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        b = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0)
                    room++; // 0인 방의 개수를 카운트
                if (arr[i][j] == 2){ // 바이러스가 놓일 수 있는 위치일때
                    virusList.add(new int[] {i, j}); // list 에 위치를 저장
                }
            }
        }
        comb(0, 0);
        System.out.println(min);
    }
}
/*

4 1
1 1 1 1
1 1 1 1
1 1 1 1
0 2 2 0

2

4 1
1 1 1 1
1 1 1 1
1 1 1 1
2 0 0 2

2

4 1
2 1 1 2
1 1 1 1
1 1 1 1
1 1 1 1

0

5 1
0 2 2 2 2
0 1 2 2 2
0 1 2 2 2
0 1 2 2 2
0 1 2 2 1

5


4 4
1 1 1 1
1 2 2 1
1 2 2 1
1 1 1 1

0

11 2
1 1 0 1 1 1 1 1 0 1 1
1 1 2 1 1 1 1 1 2 1 1
0 1 2 1 1 1 0 1 2 1 1
0 1 0 1 1 1 0 1 0 1 1
0 0 2 0 0 1 0 0 2 0 0
1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1

4

5 1
1 1 0 1 1
1 1 2 1 1
1 1 2 1 1
1 1 0 1 1
1 1 2 1 1


 */