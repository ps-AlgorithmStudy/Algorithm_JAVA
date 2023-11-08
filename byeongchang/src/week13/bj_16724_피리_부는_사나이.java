package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/16724
public class bj_16724_피리_부는_사나이 {
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
        char[][] map = new char[N][M];
        int[][] visited = new int[N][M]; // 방문 정보 저장
        int answer = 0;  // safe zone 개수 저장
        HashMap<Character, int[]> direc = new HashMap<>();  // 방향 저장
        direc.put('D', new int[]{1,0});
        direc.put('L', new int[]{0,-1});
        direc.put('U', new int[]{-1,0});
        direc.put('R', new int[]{0,1});

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int turn = 1;  // 방문정보 저장에 사용해 사이클 탐지
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] != 0) continue; // 이미 방문했으면 패스
                answer += move(i,j,turn++,map,visited,direc);
            }
        }
        return answer;
    }

    public static int move(int x, int y, int turn, char[][] map, int[][] visited, HashMap<Character, int[]> direc) {
        while(true) {
            visited[x][y] = turn;
            int[] d = direc.get(map[x][y]); // 방향 확인
            int nx = x+d[0];  // 다음 좌표 계산
            int ny = y+d[1];  // 다음 좌표 계산
            if(visited[nx][ny] == turn) break;  // 사이클 형성되면 safe zone이 하나 더 필요함
            if(visited[nx][ny] != 0) return 0;  // 기존 방문지역으로 가면 만들어둔 safe zone 활용하면 됨
            x = nx;
            y = ny;
        }
        return 1;  // 사이클 생김
    }
}
