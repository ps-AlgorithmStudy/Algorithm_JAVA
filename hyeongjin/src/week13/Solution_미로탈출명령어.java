package week13;

import java.util.*;

class Solution_미로탈출명령어 {
    // 사전순으로 방향 배치
    static int[] dx = {1, 0 ,0,-1};
    static int[] dy = {0,-1, 1, 0};
    static String result;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        result = "";
        StringBuilder sb = new StringBuilder();
        if((Math.abs(k-(Math.abs(r-x) + Math.abs(c-y))) % 2 != 0)) return "impossible";
        DFS(n,m,x,y,r,c,k,0, sb);
        if("".equals(result)) return "impossible";
        return result;
    }
    static boolean isOBB(int n, int m, int x, int y){
        return (x<1 || x> n || y<1 || y>m);
    }
    static int getDistance(int x1, int x2, int y1, int y2){
        return (Math.abs(x1-x2) + Math.abs(y1-y2));
    }
    static char direction(int d){
        switch(d){
            case 0 : return 'd';
            case 1 : return 'l';
            case 2 : return 'r';
            case 3 : return 'u';
            default : return ' ';
        }
    }
    static void DFS(int n , int m, int x, int y, int r, int c, int k, int cnt, StringBuilder sb){
        // 결과(=사전순으로 제일 앞에있는 값)가 들어오면 종료
        if(result.length()==k) return;

        // 현재 남은거리 + 이동거리가 k보다 크면 종료
        if(getDistance(x,r,y,c) + cnt > k) return;

        // k만큼 이동 후
        if(cnt == k){

            // 도착했으면 결과값에 대입
            if(x == r && y == c){
                result = sb.toString();
            }
            return; // 종료
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOBB(n,m,nx,ny)) continue;

            sb.append(direction(d));
            DFS(n, m, nx, ny, r, c, k, cnt+1, sb);
            sb.delete(cnt,cnt+1);
        }
    }
    public static void main(String[] args) {
        Solution_미로탈출명령어 s = new Solution_미로탈출명령어();
        System.out.println(s.solution(3,4,2,3,3,1,5));
        System.out.println(s.solution(2,2,1,1,2,2,2));
        System.out.println(s.solution(3,3,1,2,3,3,4));
    }
}
