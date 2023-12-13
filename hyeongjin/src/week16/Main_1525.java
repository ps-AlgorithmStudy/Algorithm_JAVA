package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1525 {
    // 방문 확인 + depth 확인
    static Map<String,Integer> check = new HashMap<>();
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = { 0, 1, 0,-1};
    static final String answer = "123456780";
    static int bfs(String init){
        Queue<String> q = new ArrayDeque<>();
        q.offer(init);

        while(!q.isEmpty()){
            String map = q.poll();
            int depth = check.get(map);  // 해당 퍼즐의 depth
            int zero = map.indexOf('0'); // 현재 0(빈곳)의 위치

            // 문자열 index => 좌표로 변환
            int i = zero%3;
            int j = zero/3;

            // 완성된 퍼즐은 바로 return
            if(map.equals(answer)) return depth;

            for(int d = 0; d <4 ; d++){
                int ni = i + di[d];
                int nj = j + dj[d];

                if(ni < 0 || ni >2 || nj < 0 || nj > 2) continue; // 범위 체크

                // 좌표 => 문자열 index로 변환
                int idx = ni + nj * 3;
                char next = map.charAt(idx);

                // 문자를 교환
                String current_map = map.replace(next,'n');  // 바꿀 위치를 'n'으로 표시
                current_map = current_map.replace('0', next);
                current_map = current_map.replace('n','0');

                if(check.get(current_map) == null){   // 해시맵에 저장되지 않은 퍼즐일 경우
                    check.put(current_map, depth +1); // {만들어진 퍼즐, depth +1} 을 저장
                    q.offer(current_map);             // 그리고 queue에 넣는다
                }
            }
        }
        // 퍼즐이 결국엔 완성되지 않았을 때
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String map = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int current = Integer.parseInt(st.nextToken());
                map += current;
            }
        }
        // {퍼즐의 초기값, depth} 를 저장
        check.put(map, 0);
        System.out.println(bfs(map));
    }
}
