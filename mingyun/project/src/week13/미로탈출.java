package week13;

class Solution {
    //d l r u
    int[] di = {1,0,0,-1};
    int[] dj = {0,-1,1,0};
    int[] goal;
    int n, m, k;
    String answer;
    char[] resultBuilder = {'d','l','r','u'};
    boolean stop = false;

    boolean inRange(int i, int j) {
        return 0<i && i <= n && 0 < j && j <= m;
    }

    boolean isValid(int i, int j, int moveAble) {
        int distance = Math.abs(goal[0]-i) + Math.abs(goal[1]-j);
        return distance <= moveAble && (distance - (moveAble)) % 2 != 1;
    }

    void dfs(int i, int j, int c, String str) {
        if (stop) return;
        if (c == k && i == goal[0] && j == goal[1]) {
            answer = str;
            stop = true;
        }
        else if (c<k){
            for (int d=0;d<4;d++) {
                int mi = di[d] + i; int mj = dj[d] + j;
                if (stop) return;
                if (isValid(mi, mj, k - c)) {
                    if (inRange(mi, mj)) {
                        dfs(mi, mj, c+1, str + resultBuilder[d]);
                    }
                }
            }
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";
        this.n = n; this.m = m; this.k = k;
        goal = new int[] {r,c};
        if (isValid(x,y, k)) dfs(x, y, 0, "");
        return answer;
    }
}
public class 미로탈출 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(3, 3, 1, 2, 3, 3, 4));
    }
}
