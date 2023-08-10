package brute_force_search;

import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class week3_bj_15661 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int[] team1;
    static int[] team2;
    static int min;

    // combination
    static void comb(int n, int M, int i){
        // M 명 만큼 뽑았다면 경험치 계산 시작
        if (n == M)
        {
            // visited 되지 않은 player을 team2에 저장
            int idx = 0;
            team2 = new int[N - M];
            for (int j = 0; j < N; j++) {
                if (!visited[j]){
                    team2[idx++] = j + 1;
                }
            }

            // exp 계산
            int exp1 = calExp(team1);
            int exp2 = calExp(team2);
            if (min == -1) min = abs(exp1 - exp2);
            min = (min < abs(exp1 - exp2)) ? min : abs(exp1 - exp2);
            return ;
        }

        // combination
        for (; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            team1[n] = i + 1;
            comb(n + 1, M, i);
            visited[i] = false;
        }
    }

    static int calExp(int[] player){
        int result = 0;
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player.length; j++) {
                result += arr[player[i] - 1][player[j] - 1];
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // team 의 인원을 정함.
        min = -1;
        for (int i = 1; i <= N / 2; i++) {
            team1 = new int[i];
            comb(0, i, 0);
            if (min == 0) break;
        }
        System.out.println(min);
    }
}
