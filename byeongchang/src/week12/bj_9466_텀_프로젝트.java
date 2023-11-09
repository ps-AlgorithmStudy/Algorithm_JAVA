package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/9466
public class bj_9466_텀_프로젝트 {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/bj_9466.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            sb.append(solution(br,st)).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] students = new int[N+1];
        boolean[] visited = new boolean[N+1];  // true : 이미 확인한 경우
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> tempTeam = new ArrayList<>(N);
        HashSet<Integer> checkCycle = new HashSet<>(N);
        for(int i=1; i<=N; i++) {
            if(visited[i]) continue; // 팀 형성 여부을 이미 확인한 경우
            //tempTeam.clear();
            //checkCycle.clear();
            answer += dfs(i,students,visited,new int[N+1]);
        }
        return answer;
    }
    // 팀 형성 불가능한 인원 수 반환
    public static int dfs(int start, int[] students, boolean[] visited, int[] tempTeam) {
        int prev = start;
        int next = students[prev];
        int index = 0;
        if(prev==next) return 0; // 자기 자신 선택한 경우
        tempTeam[prev] = 0;
        visited[prev] = true;
        // 사이클이 나올 때 까지 반복
        while(visited[next]) {
            tempTeam[next] = tempTeam[prev]+1;
            visited[next] = true;
            prev = next;
            next = students[prev];
        }
        // 반복문이 끝났다는 것은 사이클이 생겼다는 것
        // 사이클 시작점을 알기 위해 tempTeam의 인덱스 확인
        int idx = tempTeam[next];
        // 인덱스 이전은 사이클이 아니므로 팀 형성 불가능
        /*test*/
//        System.out.println("start: "+start);
//        System.out.println("next: "+next);
//        System.out.println("tempTeam: "+Arrays.toString(tempTeam));
//        System.out.println("checkCycle: "+checkCycle);
//        System.out.println("idx: "+idx);

        return idx;
    }
    // 팀 형성 불가능한 인원 수 반환
    public static int dfs2(int start, int[] students, boolean[] visited, ArrayList<Integer> tempTeam, HashSet<Integer> checkCycle) {
        int prev = start;
        int next = students[prev];
        if(prev==next) return 0; // 자기 자신 선택한 경우
        tempTeam.add(prev);
        checkCycle.add(prev);
        visited[prev] = true;
        // 사이클이 나올 때 까지 반복
        while(!checkCycle.contains(next)) {
            tempTeam.add(next);
            if(visited[next]) break;  // 이미 확인 한 경우 건너뜀
            checkCycle.add(next);
            visited[next] = true;
            prev = next;
            next = students[prev];
        }
        // 반복문이 끝났다는 것은 사이클이 생겼다는 것
        // 사이클 시작점을 알기 위해 tempTeam의 인덱스 확인
        int idx = tempTeam.indexOf(next);
        // 인덱스 이전은 사이클이 아니므로 팀 형성 불가능
        /*test*/
//        System.out.println("start: "+start);
//        System.out.println("next: "+next);
//        System.out.println("tempTeam: "+tempTeam);
//        System.out.println("checkCycle: "+checkCycle);
//        System.out.println("idx: "+idx);
        return idx;
    }
}
