package week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1525
public class bj_1525_퍼즐 {
    final static int[][] ORANIZED_STATE = {{1,2,3},{4,5,6},{7,8,0}}; // 정리된 상태
    final static String ANSWER = "123456780";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int move = 0;   // 최소 이동 횟수
        char[] table = new char[9]; // 표
        HashMap<String, Integer> map = new HashMap<>();    // 이동횟수를 저장할 HashMap, 키: 표, 값: 이동횟수
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                table[i*3+j] = st.nextToken().charAt(0);
            }
        }

        if(!isSolvable(table)) return -1;  // 이동해도 초기 상태로 만들 수 없는 경우

        String puzzle = new String(table);
        map.put(puzzle, 0);

        move = bfs(puzzle, map);

        return move;
    }

    public static int bfs(String puzzle, HashMap<String, Integer> map) {
        final int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
        ArrayDeque<String> q = new ArrayDeque<>();
        q.offer(puzzle);
        while(!q.isEmpty()) {
            String cur = q.poll();
            if(cur.equals(ANSWER)) return map.get(cur);
            int zeroPos = cur.indexOf('0');
            int x = zeroPos / 3;
            int y = zeroPos % 3;
            for(int[] d: direction) {
                int nx = x + d[0];
                int ny = y + d[1];
                if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;
                String next = swap(cur, zeroPos, nx*3+ny);
                if(map.containsKey(next)) continue;
                map.put(next, map.get(cur)+1);
                q.offer(next);
            }
        }
        return -1;
    }
    public static String swap(String cur, int prev, int next) {
        char[] table = cur.toCharArray();
        char temp = table[prev];
        table[prev] = table[next];
        table[next] = temp;
        return new String(table);
    }

    /**
     * 이동해서 초기 상태로 만들 수 있는지 여부 확인 ( 표 길이가 홀수 이므로 역전 개수가 짝수면 해결 가능 )
     * <a href="https://natejin.tistory.com/m/22"> 참고 링크 </a>
     * @param table 표를 1차원으로 표현한 char 배열
     * @return 해결 가능 여부
     */
    public static boolean isSolvable(char[] table){
        int inversion = 0;  // 역전의 개수, 짝수면 해결 가능
        char max = '0';   // 탐색한 구간의 최대값
        for(int i=0; i<table.length; i++) {
            if(table[i] == '0') continue;   // 0은 제외
            // 이미 탐색한 구간의 최대값보다 i번째 원소가 크면 역전 없음
            if(table[i] > max) {
                max = table[i];
            }
            else {
                for(int j=0; j<i; j++) {
                    if(table[j] == '0') continue;   // 0은 제외
                    // i번째 원소보다 큰 원소가 앞에 있으면 역전 발생
                    if(table[j] > table[i]) {
                        inversion++;
                    }
                }
            }
        }
        // System.out.println("inversion: "+inversion);
        return inversion % 2 == 0;
    }


    // TODO: A* 알고리즘 활용해보기
    /* A* 알고리즘용 */
    class State implements Comparable<State>{
        int[][] table;
        int f;   // g + h
        int g;   // 현재까지 이동한 횟수
        int h;   // 제자리에 있지 않은 퍼즐 개수

        public State(int[][] table, int g){
            this.table = arrayCopy(table);
            this.g = g;
            this.h = getH();
            this.f = this.g + this.h;
        }

        public State(int[][] table) {
            this.table = table;
            this.g = 0;
            this.h = getH();
            this.f = this.g + this.h;
        }

        private int[][] arrayCopy(int[][] src) {
            int[][] dest = new int[src.length][];
            for (int i = 0; i < src.length; i++) {
                dest[i] = src[i].clone();
            }
            return dest;
        }
        private int getH() {
            int h = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(table[i][j] != ORANIZED_STATE[i][j]) h++;
                }
            }
            return h;
        }

        @Override
        public int compareTo(State o) {
            if(this.f == o.f) return Integer.compare(this.g, o.g);
            else return Integer.compare(this.f, o.f);
        }
    }
}
