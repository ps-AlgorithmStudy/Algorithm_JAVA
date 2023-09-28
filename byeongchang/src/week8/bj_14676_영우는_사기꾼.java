package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_14676_영우는_사기꾼 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        if(solution(br, st)) System.out.println("King-God-Emperor");
        else System.out.println("Lier!");
        br.close();
    }
    public static boolean solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] needs = new ArrayList[N+1];  // 건물을 지을 수 있는 조건 모음
        ArrayList<Integer>[] available = new ArrayList[N+1];  // 건물 건설에 영향을 주는 건물 모음
        for(int i=0; i<=N; i++) {  // ArrayList 초기화
            needs[i] = new ArrayList<>();
            available[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            needs[to].add(from);
            available[from].add(to);
        }
        int[] buildLog = new int[N+1];  // 건물 개수 저장
        boolean[] buildAvailable = new boolean[N+1];  // 건물 가능 여부 저장
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int buildingNum = Integer.parseInt(st.nextToken());
            if(order==1){
                if(!buildAvailable[buildingNum]){ // 건물을 지을 수 없다면
                    for(int need: needs[buildingNum]) {  // 건물을 지을 수 있는 조건을 만족하는지 확인
                        if(buildLog[need]<=0) return false;  // 만족하지 않았는데도 건물을 지은 로그가 있다면 false 리턴
                    }
                    buildAvailable[buildingNum] = true;  // 조건을 만족하므로 true로 변경
                }
                buildLog[buildingNum]++;  // 해당 건물 개수 증가
            }
            else {
                if(buildLog[buildingNum]<=0) return false;  // 건물이 없는데 파괴하면 false 리턴
                if(--buildLog[buildingNum]==0) {  // 건물이 다 파괴되면
                    for(int avail: available[buildingNum]) buildAvailable[avail] = false; // 영향을 주는 건물들에게 건설 가능 여부를 false로 변경
                }
            }
        }
        return true;
    }
}
