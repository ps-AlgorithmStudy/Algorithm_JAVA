package week2;

import java.io.*;
import java.util.*;

public class BJ_실버1_2615_오목 {
	static final int N = 19;

    static void solution(int[][] maps) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;

        for(int i = 0; i < N; i++) {
        	// 둘다 이기는 경우는 입력되지 않아서 굳이 필요하진 않을 순 있지만... 
        	// 그래도 최적화 목적
            if(isFound) {
                break;
            }

            for(int j = 0; j < N; j++) {
                int value = maps[i][j];
                if(value == 0) {
                    continue;
                }

                if(hasWon(maps, i, j)) {
                    // 출력시 idx + 1 주의;
                    sb.append(value).append("\n").append(i + 1).append(" ").append(j + 1);
                    isFound = true;
                    break;
                }
            }
        }

        if(!isFound) {
            sb.append(0);
        }

        // 행 렬 순
        System.out.print(sb);
    }

    // 조건을 만족하는 제일 첫번째 위치를 반환하는 것이 목적이므로
    // 4가지 방향{아래, 오른쪽, 대각선 위, 대각선 아래} 에 대해서만 5번 탐색 수행
    // 승리 조건을 발견하면 '육목' 검사 후 결과 반환 또는 다른 방향에 대해 탐색 수행
    static boolean hasWon(int[][] maps, int x, int y) {
        int[] dx = {0, 1, -1, 1};
        int[] dy = {1, 0, 1, 1};
        int value = maps[x][y];

        for(int d = 0; d < dx.length; d++) {
            int matchCnt = 1;
            int nx = x;
            int ny = y;
            
            // 일단은 4번 더 탐색
            for(int count = 1; count < 5; count++) {
                nx = nx + dx[d];
                ny = ny + dy[d];

                if(isOutbound(nx, ny)) {
                    break;
                }

                if(maps[nx][ny] == value) {
                    matchCnt++;
                }
            }

            if(matchCnt == 5) {
                // 현재 위치의 반대 방향, 정방향에 대해서
                // 육목 확인 후 반환해주기
                int leftX = x - dx[d], leftY = y - dy[d];
                int rightX = nx + dx[d], rightY = ny + dy[d];

                if(!isOutbound(leftX, leftY) && maps[leftX][leftY] == value) {
                    continue;
                }

                if(!isOutbound(rightX, rightY) && maps[rightX][rightY] == value) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] maps = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(maps);
        br.close();
    }
}