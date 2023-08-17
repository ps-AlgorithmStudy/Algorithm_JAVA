package week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_25760_귀경길_교통상황을_알려드립니다 {
    //참고 : https://newdeal123.tistory.com/95
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/bj_25760.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = 3;
        for(int tc=1;tc<=T;tc++) {
            solution(br, st);
        }
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {

        int N = Integer.parseInt(br.readLine());
        boolean[] city = new boolean[N];
        ArrayList<Integer>[] road = new ArrayList[N];  // 그래프
        for(int i=0;i<N;i++) road[i] = new ArrayList<>();
        // 지점 개수: N, 도로 개수: N-1 = 트리 구조
        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            road[a].add(b);
            road[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) city[i] = Integer.parseInt(st.nextToken())==1;  // 도시에 차 있으면 true


        int[] distance = new int[N];   // 1번 도시와 각 도시 간의 거리 저장
        bfsLevel(city,road,distance);
        PriorityQueue<int[]> car = new PriorityQueue<>((o1,o2) -> Integer.compare(o2[1],o1[1]));  // 거리를 기준으로 내림차순으로 정렬
        for(int i=0; i<N; i++) {
            if(city[i]) car.add(new int[]{i, distance[i]});
        }
        int waitTime = 1;
        int time = 0;
        while(!car.isEmpty()) {
            int[] cur = car.poll();
            time = Math.max(time, cur[1]+waitTime);   // 어떤 차이든지 마지막에 도착한 차가 걸린 시간이 최종 시간이 된다
            waitTime++;
        }
        System.out.println(time);
    }

    public static void bfsLevel(boolean[] city, ArrayList<Integer>[] road, int[] distance) {
        for(int i=0;i<city.length;i++) distance[i] = -1;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        distance[0] = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int r: road[cur]) {
                if(distance[r] != -1) continue;
                distance[r] = distance[cur]+1;
                q.offer(r);

            }
        }

    }
}
