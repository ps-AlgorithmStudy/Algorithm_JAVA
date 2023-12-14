package week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12893
public class bj_12893_적의_적 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br , StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if(M == 0) return 1;  // 적대관계가 없는 경우
        boolean[][] enemy = new boolean[N+1][N+1];   // 적대관계 저장 배열(배열 크기가 최대 2백만, 적대 관계 개수가 최대 백만이므로 인접행렬 사용)
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            enemy[A][B] = true;
            enemy[B][A] = true;
        }

        int[] group = new int[N+1];
        for(int i=1; i<=N; i++) {
            if(group[i] == 0) {
                if(!binaryGraph(i, 1, group, enemy)) return 0;
            }
        }
        return 1;
    }
    public static boolean binaryGraph(int num, int team, int[] group, boolean[][] enemy) {
        group[num] = team;  // 팀 저장
        for(int i=1; i<group.length; i++) {
            if(enemy[num][i]) {
                if(group[i] == team) return false;  // 적대 관계인데 같은 팀으로 저장되어 있는 경우
                else if(group[i] == 3-team) continue;
                else {
                    // 적대 관계인 경우 다른 팀으로 저장 시도
                    if(!binaryGraph(i, 3-team, group, enemy)) return false;
                }
            }
        }
        return true;
    }
}
