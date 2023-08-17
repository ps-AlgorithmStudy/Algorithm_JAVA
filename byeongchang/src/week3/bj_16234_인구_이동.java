package week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_16234_인구_이동 {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/bj_16234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int scNum = 5;
        for(int tc=1;tc<=scNum;tc++){
            solution(br, st);
        }
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 배열 크기
        int L = Integer.parseInt(st.nextToken()); // 인구수 차이 최소값
        int R = Integer.parseInt(st.nextToken()); // 인구수 차이 최대값
        int[][] country = new int[N][N];
        // 나라 인구수 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][N];
        ArrayList<int[]> moveList = new ArrayList<>();
        int day = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            visited = new boolean[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(visited[i][j]) continue;
                    if(bfs(i, j, L, R, country, visited, moveList)) flag = true;
                    moveList.clear();
                }
            }
            if (flag) day++;
        }
        System.out.println(day);



    }
    public static boolean bfs(int x, int y, int L, int R, int[][] country, boolean[][] visited, ArrayList<int[]> moveList) {
        if(visited[x][y]) return false;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        moveList.add(new int[] {x, y});
        visited[x][y] = true;
        boolean flag = false;
        int sum = country[x][y];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= country.length || ny < 0 || ny >= country.length) continue;
                if (visited[nx][ny]) continue;
                if (Math.abs(country[cur[0]][cur[1]] - country[nx][ny]) < L || Math.abs(country[cur[0]][cur[1]] - country[nx][ny]) > R)
                    continue;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
                moveList.add(new int[]{nx, ny});
                sum += country[nx][ny];
                flag = true;
            }

        }
        int avg = sum / moveList.size();
        for(int[] pos : moveList) {
            country[pos[0]][pos[1]] = avg;
        }
        return flag;
    }
}
