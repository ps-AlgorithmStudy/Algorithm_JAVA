import java.util.*;

class Solution_미로탈출명령어 {
    
    static class Pair {
        int x;
        int y;
        int k;
        String path;
        
        Pair(int x, int y, int k, String path) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.path = path;
        }
        
        @Override
        public String toString() {
            return "Pair{" + x + ", " + y + ", " + k + ", path[" + path +"]}";
        }
        
    }
    
    private final String FAILED_MSG = "impossible";
    
    // // d - l - r - u 순서
    final int[] dx = {1, 0, 0, -1};
    final int[] dy = {0, -1, 1, 0};
    final char[] SYMBOL = {'d', 'l', 'r', 'u'};
    
    int N, M;
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        
        // BFS 탐색 수행 
        Pair init = new Pair(x, y, k, "");
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(init);
        
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            
            int minDist = getDistance(cur.x, cur.y, r, c);
            int remain = k - cur.path.length();
            
            // 적어도 하나는 남아 있어야 
            boolean notValid = Math.abs(remain - minDist) % 2 == 1;
            if(notValid) {
                continue;
            }
            
            // 종료 조건
            if(cur.x == r && cur.y == c) {
                if(remain == 0) {
                    return cur.path.toString();
                }
            }
            
            // 현재 좌표에서 end 좌표까지 최단 거리보다 남은 k가 적다면 더 이상 탐색 상태를 추가하지 않음
            if(minDist > remain) {
                continue;
            } 
            
            // 우선순위(사전순)대로 방문
            for(int d = 0; d < dx.length; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                
                if(isOutbound(nx, ny)) {
                    continue;
                }
                
                // StringBuilder tempPath = new StringBuilder(cur.path);
                // tempPath.append(SYMBOL[d]);    
            
                String temp = cur.path + SYMBOL[d];
                q.offer(new Pair(nx, ny, cur.k - 1, temp));
            }
            
            
        }
        
        return FAILED_MSG;
    }
    
    int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    boolean isOutbound(int x, int y) {
        return (x < 1 || x > N) || (y < 1 || y > M); // 1's based indexing 
    }
}