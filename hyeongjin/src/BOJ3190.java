package week5;

import java.io.*;
import java.util.*;

public class BOJ3190 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());


        int[][] apples = new int[K][2];
        // 사과 위치 입력
        for (int i = 0; i < K; i++) {
            String[] parts = br.readLine().split(" ");
            apples[i][0] = Integer.parseInt(parts[0]);
            apples[i][1] = Integer.parseInt(parts[1]);
        }

        // 방향 전환 정보를 저장할 맵
        int L = Integer.parseInt(br.readLine());
        Map<Integer, Integer> rotations = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String[] parts = br.readLine().split(" ");
            int time = Integer.parseInt(parts[0]);
            char direction = parts[1].charAt(0);
            rotations.put(time, direction == 'L' ? -1 : 1);
        }

        int[][] board = new int[N][N];
        for (int[] apple : apples) {
            board[apple[0] - 1][apple[1] - 1] = 2; // 사과는 2로 표시
        }

        int[] di = {0, 1, 0, -1};
        int[] dj = {1, 0, -1, 0};

        LinkedList<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});
        board[0][0] = 1; // 뱀은 1로 표시
        int direction = 0;
        int time = 0;

        while (true) {
            time++; // 시간 증가
            int[] head = snake.getLast(); // 머리위치
            int ni = head[0] + di[direction];
            int nj = head[1] + dj[direction];

            // 종료조건(몸통과 부딪히거나 맵 밖으로)
            if (ni < 0 || ni >= N || nj < 0 || nj >= N || board[ni][nj] == 1) {
                break;
            }

            // 사과가 없을떄
            if (board[ni][nj] != 2) {
                int[] tail = snake.removeFirst();
                board[tail[0]][tail[1]] = 0;
            }

            // 이동
            snake.add(new int[]{ni, nj});
            board[ni][nj] = 1;

            // 방향전환
            if (rotations.containsKey(time)) {
                direction = (direction + 4 + rotations.get(time)) % 4;
            }
        }

        System.out.println(time);
    }
}
