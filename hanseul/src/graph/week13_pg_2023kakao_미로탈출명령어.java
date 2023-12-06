package graph;
import java.util.*;

public class week13_pg_2023kakao_미로탈출명령어 {
    class Solution {
        int dx[] = {1, 0, 0, -1}; // d l r u
        int dy[] = {0, -1, 1, 0};
        char ch[] = {'d', 'l', 'r', 'u'};
        String answer = "";

        boolean isSuccess(int x, int y, int r, int c, int k){
            int val = k - (Math.abs(x - r) + Math.abs(y - c));
            //        이동횟수 - 최단이동횟수
            if (val < 0 || val % 2 == 1) return false;
            else return true;
        }

        void dfs(int n, int m, int x, int y, int r, int c, int k, String ans){
            if (!answer.equals("")) return; // 빠름
            if (k == 0 && x == r && y == c) {
                answer = ans;
                return;
            }
            for(int i = 0;i < 4; i++){
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 1 || tx > n || ty < 1 || ty > m) continue; // 범위 체크
                if (!isSuccess(tx, ty, r, c, k - 1)) continue; // 가능성 체크
                // if (!answer.equals("")) continue; // 느림
                dfs(n, m, tx, ty, r, c, k - 1, ans + ch[i]);
            }
        }
        public String solution(int n, int m, int x, int y, int r, int c, int k)     {
            if (!isSuccess(x, y, r, c, k))
                return "impossible";
            else{
                dfs(n,m,x,y,r,c,k,"");
                return answer;
            }
        }
    }
}
