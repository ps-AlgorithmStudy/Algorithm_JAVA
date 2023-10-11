package implementation;

import java.util.*;
public class week10_pg_디스크컨트롤러 {

    static int[][] job = {{0, 3},{1, 9},{2, 6}};
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = solution(job);
        System.out.println(result);
    }

    public static int solution(int[][] jobs) {
        int answer = 0;

//        ArrayList<int[]> list = new ArrayList<>(Arrays.stream(jobs).toList());

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            list.add(new int[] {jobs[i][0], jobs[i][1]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int curState = 0;

        while (!list.isEmpty() || !pq.isEmpty()){

            // 현재 curState 와 비교해서 실행시킬수 있는 프로세스 넣기
            Iterator iter = list.iterator();
            while(iter.hasNext()){
                int cur[] = (int[]) iter.next();
                if (curState >= cur[0]) {
                    pq.offer(cur);
                    iter.remove();
                }
            }

            // 현재 진행할 수 있는 프로세스가 있다면 -> pq 에서 하나 빼서 계산
            if (!pq.isEmpty()){
                int cur[] = pq.poll();

                answer += curState + cur[1] - cur[0];
                curState = curState + cur[1];
            }
            // 현재 진행할 수 있는 프로세스가 없다면 -> 그냥 curState ++ 하고 돌리기
            else{
                Collections.sort(list, (o1, o2) -> {
                    if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                    return Integer.compare(o1[0], o2[0]);
                });
                pq.offer(list.get(0));
                curState = list.get(0)[0];
                list.remove(0);
            }
        }

        return answer /= jobs.length;
    }
}
