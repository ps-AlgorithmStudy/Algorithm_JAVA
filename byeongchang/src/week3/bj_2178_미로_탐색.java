package week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class bj_2178_미로_탐색 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/bj_2178.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 4;
        for(int tc=1; tc<=T; tc++){
            System.out.printf("#%d ", tc);
            solution(br);
        }
    }
    public static void solution(BufferedReader br) throws Exception {
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[][] maze = new int[N][M];
        int[][] visited = new int[N][M];
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                maze[i][j] = line.charAt(j) - '0';
                if(maze[i][j] == 1) visited[i][j] = 99999; // N,M 최대값이 각각 100이라 마지막에 방문해도 10000
            }
        }
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        dfs(N, M, maze, visited, dir);
        System.out.println(visited[N-1][M-1]);
    }
    /*
    스택을 활용한 DFS
    목표 : visited[N-1][M-1] 부분에 최소 칸 수를 저장
    방법 :
    0,0 에서 시작
    DFS를 통해 앞으로 나아가면서 이전 지점 칸 수 + 1 를 방문한 지점에 저장
    참고 : dp로 풀 경우에 실행 시간이 1/10 으로 줄어든다
    최소 칸 수를 i 인덱스, 좌표를 저장하는 j인덱스(2)로 하는 2차원 배열 생성 후 최소 칸 수마다 좌표를 저장해 N-1,M-1 좌표가 저장할 때까지 계산하는 방식
     */
    public static void dfs(int N, int M, int[][] maze, int[][] visited, int[][] dir) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        visited[0][0] = 1;
        while(!stack.isEmpty()) {
            int[] cur = stack.pop();
            if(maze[cur[0]][cur[1]] == 0) continue;  // 벽인 경우
            for(int[] d: dir){
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];
                if(nx<0 || N<=nx || ny<0 || M<=ny) continue;  //  index guard
                if(maze[nx][ny] == 0) continue;  // 벽인 경우
                if(visited[nx][ny] <= visited[cur[0]][cur[1]]+1) continue;  // 방문하려고 하는 지점이 이미 더 빠른 경로로 방문했던 지점일 경우
                visited[nx][ny] = visited[cur[0]][cur[1]]+1;
                stack.push(new int[]{nx,ny});
            }
        }
    }
}
