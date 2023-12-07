import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_bj_3089_FindClover {

    static int N, M; // 네잎 클로버 개수; 명령어 개수;
    /*static boolean[][] maps;
    static final int MAX_SIZE = 100_000;*/
    static Set<String> set;

    static final int MAX_RANGE = 100_000;
    static final int MIN_RANGE = -100_000;

    static void solution(String cmd) {
        int x = 0, y = 0;

        for(char c : cmd.toCharArray()) {
            if(c == 'L') {
                // 현재 행 고정
                // 탐색 범위 [y, 최소 열 MIN_RANGE]
            } else if (c == 'R') {
                // 현재 행 고정
                // 탐색 범위 [y, 최대 열 MAX_RANGE]
            } else if ( c == 'U') {
                // 현재 열 고정
                // 탐색 범위 [x, 최대 행 MAX_RANGE]
            } else if( c == 'D') {
                // 현재 열 고정
                // 탐색 범위 [x, 최소 행 MIN_RANGE]
            }
        }
    }

    // 해당 공간에 존재하는 네잎 클로버 중 가장 왼쪽에 있는 네잎 클로버 반환
    static void binarySearch(int fixedPos, int start, int end) {

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        set = new HashSet<>();


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        String cmd = br.readLine();
        solution(cmd);
    }
}
