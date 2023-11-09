package week13;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/150365
public class pg_150365_미로_탈출_명령어 {

    // n: 세로 m: 가로 x: 출발x y: 출발y r: 탈출x c: 탈출y k: 이동거리
    // d l r u
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        char[] route = new char[k];
        char[] direcPriority = {'d', 'l', 'r', 'u'};
        HashMap<Character, int[]> direc = new HashMap<>();
        direc.put('d', new int[]{1,0});
        direc.put('l', new int[]{0,-1});
        direc.put('r', new int[]{0,1});
        direc.put('u', new int[]{-1,0});
        // 출발지와 목적지 사이의 최소 좌표 거리 계산
        int absDist = Math.abs(x - r) + Math.abs(y - c);
        // 남은 k개가 홀수거나 전체 k로 도달할 수 없는 경우: impossible
        if (k < absDist || Math.abs(absDist-k)%2!=0) return "impossible";

        dfs(x-1,y-1,r-1,c-1,k,n,m,0,false,new char[k],route,direcPriority, direc);

        return new StringBuilder().append(route).toString();
    }
    public boolean dfs(int x, int y, int r, int c, int k, int n, int m, int cnt, boolean flag, char[] candidate, char[] route, char[] direcPriority, HashMap<Character, int[]> direc) {

        // 가지치기
        // 이미 한번 도착한 경우
        if(flag) return flag;
        // 혹시 모르는 가지치기
        if (k-cnt < 0) return flag;
        // 남은 거리로는 도착하는 것이 불가능할 때
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if((k - cnt) < dist) return flag;
        //재귀 탈출
        if (k == cnt) {
            if(x==r && y==c) {
                for(int i=0; i<k; i++){
                    route[i] = candidate[i];
                }
                flag = true;
            }
            return flag;
        }

        //방향 우선순위에 맞게 재귀
        for(char di: direcPriority) {
            // candidate에 추가
            candidate[cnt] = di;
            int[] d = direc.get(di);
            int nx = x + d[0];
            int ny = y + d[1];
            // 미로 밖을 벗어나는 지 확인
            if(nx<0 || n<=nx || ny<0 || m<=ny) continue;
            // 재귀
            flag = dfs(nx,ny,r,c,k,n,m,cnt+1,flag,candidate,route,direcPriority,direc);
            // 재귀 탈출 못했으니깐 원복
            candidate[cnt] = '0';

        }
        return flag;
    }





    public static void main(String[] args) {
        TestCase tc1 = new TestCase(3, 4, 2, 3, 3, 1, 5, "dllrl");
        TestCase tc2 = new TestCase(2, 2, 1, 1, 2, 2, 2, "dr");
        TestCase tc3 = new TestCase(3, 3, 1, 2, 3, 3, 4, "impossible");
        pg_150365_미로_탈출_명령어 s = new pg_150365_미로_탈출_명령어();
        for(TestCase tc: new TestCase[]{tc1,tc2,tc3}){
            System.out.println("sol: "+s.solution(tc.n, tc.m, tc.x, tc.y, tc.r, tc.c, tc.k)+", res: "+tc.result);
        }
    }
    static class TestCase {
        int n;
        int m;
        int x;
        int y;
        int r;
        int c;
        int k;
        String result;

        public TestCase(int n, int m, int x, int y, int r, int c, int k, String result) {
            this.n = n;
            this.m = m;
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
            this.k = k;
            this.result = result;
        }
    }
}
