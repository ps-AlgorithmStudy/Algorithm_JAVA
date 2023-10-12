package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14442
// https://hongjw1938.tistory.com/163
public class bj_14442_벽_부수고_이동하기_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));

        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());  // 부술 수 있는 벽 개수
        int[][] map = new int[N][M];  // 0 : 빈 공간, 1 : 벽
        for(int i=0; i<N; i++) {
            String tmp = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = tmp.charAt(j)-'0';
            }
        }

        int[][][] visited = new int[2][N][M];  //  0: 거리, 1: 부순 벽 개수, x, y
        int[][] dist = {{-1,0},{0,-1},{1,0},{0,1}};  // 상, 좌, 하, 우
        for(int i=0; i<N; i++) Arrays.fill(visited[1][i], Integer.MAX_VALUE);  // 나중에 부순 벽 개수 비교하기 위해서 미리 초기화

        return bfs1(0,0,N-1,M-1,K,map,visited,dist);
    }

    private static int bfs1(int x, int y, int N, int M, int K, int[][] map, int[][][] visited, int[][] dist) {
        ArrayDeque<int[]> q = new ArrayDeque<>((N+1)*(M+1)+1);
        visited[0][x][y] = 1;
        visited[1][x][y] = 0;
        q.offer(new int[]{x,y,visited[0][x][y],visited[1][x][y]});  // x,y,거리,부순 벽 개수

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int[] d: dist) {
                int nx = cur[0]+d[0];
                int ny = cur[1]+d[1];
                int prevMove = cur[2];
                int prevBreak = cur[3];

                if(prevMove>(N+1)*(M+1)) return -1;
                if(visited[0][N][M]!=0) break;

                // 맵 밖을 벗어나거나 이미 방문한 지점의 부순 벽 개수보다 많이 부순 경우
                if(nx<0 || map.length<=nx || ny<0 || map[0].length<=ny || visited[1][nx][ny]<=prevBreak) continue;

                // 벽이고 아직 부술 수 있고 도착한 지점의 부순 벽보다 작을 경우
                if(map[nx][ny]==1 && prevBreak<K && prevBreak+1<visited[1][nx][ny]) {
                    visited[0][nx][ny] = prevMove+1;
                    visited[1][nx][ny] = prevBreak+1;
                    q.offer(new int[]{nx,ny,visited[0][nx][ny],visited[1][nx][ny]});
                }
                // 빈 공간이면
                else if(map[nx][ny]==0) {
                    visited[0][nx][ny] = prevMove+1;
                    visited[1][nx][ny] = prevBreak;
                    q.offer(new int[]{nx,ny,visited[0][nx][ny],visited[1][nx][ny]});
                }
            }

        }
        return visited[0][N][M]==0?-1:visited[0][N][M];
    }
}
