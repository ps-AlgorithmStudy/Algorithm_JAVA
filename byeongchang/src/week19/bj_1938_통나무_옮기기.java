package week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// https://www.acmicpc.net/problem/1938
public class bj_1938_통나무_옮기기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br));
        br.close();
    }

    public static int solution(BufferedReader br) throws Exception {
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        int[][][] visited = new int[N][N][2];   // 가로, 세로, 모양(0: 가로, 1: 세로)
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}}; // 상하좌우회전
        Tree end = new Tree();    // 도착점
        Tree start = new Tree();    // 시작점
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 'B') {
                    checkStartEnd(start, i, j, 'B', dir, map);    // 맵에서 시작점 확인하고 Tree 인스턴스로 변환
                }
                else if (map[i][j] == 'E') {
                    checkStartEnd(end, i, j, 'E', dir, map);    // 맵에서 도착점 확인하고 Tree 인스턴스로 변환
                }
            }
        }

        ArrayDeque<Tree> q = new ArrayDeque<>();
        q.add(start);
        return bfs(end, q, dir, visited, map);

    }

    private static void checkStartEnd(Tree node, int x, int y, char flag, int[][] dir, char[][] map) {
        node.shape = -1;
        for (int k=0; k<4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if (nx<0 || nx>=map.length || ny<0 || ny>=map.length || map[nx][ny] != flag) continue;
            if (node.shape==-1 && (k==0 || k==1)) node.shape = 1;
            else if (node.shape==-1 && (k==2 || k==3)) node.shape = 0;
            else if (node.shape==1 && (k==0 || k==1)) {
                node.x = x;
                node.y = y;
                node.cnt = 0;
                map[x][y] = map[x-1][y] = map[x+1][y] = '0';
                break;
            }
            else if (node.shape==0 && (k==2 || k==3)) {
                node.x = x;
                node.y = y;
                node.cnt = 0;
                map[x][y] = map[x][y-1] = map[x][y+1] = '0';
                break;
            }
        }
    }

    public static int bfs(Tree end, ArrayDeque<Tree> q, int[][] dir, int[][][] visited, char[][] map) {
        int[][] rotateDir = {{1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}, {0,1}, {0,-1}};
        while (!q.isEmpty()) {
            Tree cur = q.poll();
            if (cur.x == end.x && cur.y == end.y && cur.shape == end.shape) return cur.cnt;    // 도착점에 도착하면 이동횟수 반환
            if (visited[cur.x][cur.y][cur.shape] == 1) continue;    // 이미 방문한 곳이면 패스
            visited[cur.x][cur.y][cur.shape] = 1;    // 방문처리
            for (int i=0; i<dir.length; i++) {
                if (i==4) {   // 회전 동작인 경우
                    if (cur.x-1<0 || cur.x+1>=map.length || cur.y-1<0 || cur.y+1>=map.length) continue;  // 범위를 벗어나면 패스
                    boolean flag = true;
                    int shape = cur.shape;
                    int s= 0;
                    int e = rotateDir.length;
                    if (cur.shape == 0) {   // 가로
                        if (visited[cur.x][cur.y][1] == 1) continue;
                        e = rotateDir.length-2;
                        shape = 1;
                    }
                    else {  // 세로
                        if (visited[cur.x][cur.y][0] == 1) continue;
                        s = 2;
                        shape = 0;
                    }
                    for (int j=s; j<e; j++) {    // 회전 가능한지 주변 확인
                        int rx = cur.x + rotateDir[j][0];
                        int ry = cur.y + rotateDir[j][1];
                        if (map[rx][ry] == '1') {    // 주변에 장애물 있어 회전 불가
                            flag = false;
                            break;
                        }
                    }
                    if (flag) q.add(new Tree(cur.x, cur.y, shape, cur.cnt+1));
                    continue;
                }
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if (nx<0 || nx>=map.length || ny<0 || ny>=map.length || map[nx][ny] == '1') continue;    // 범위를 벗어나거나, 장애물 있으면 패스
                if (visited[nx][ny][cur.shape] == 1) continue;
                if (cur.shape == 0) {    // 가로 확인
                    if(ny-1<0 || ny+1>=map.length || map[nx][ny-1]=='1' || map[nx][ny+1]=='1') continue;
                    q.add(new Tree(nx, ny, cur.shape, cur.cnt+1));
                }
                else {    // 세로 확인
                    if(nx-1<0 || nx+1>=map.length || map[nx-1][ny]=='1' || map[nx+1][ny]=='1') continue;
                    q.add(new Tree(nx, ny, cur.shape, cur.cnt+1));
                }

            }
        }
        return 0;    // 도착점에 도착하지 못하면 0 반환
    }




    static class Tree {
        int x;
        int y;
        int shape;   // 모양 0: 가로, 1: 세로
        int cnt;   // 이동횟수

        public Tree() {
        }

        public Tree(int x, int y, int shape, int cnt) {
            this.x = x;
            this.y = y;
            this.shape = shape;
            this.cnt = cnt;
        }
    }
}
