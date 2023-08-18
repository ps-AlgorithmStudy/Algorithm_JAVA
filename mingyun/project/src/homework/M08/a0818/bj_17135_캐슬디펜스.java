package homework.M08.a0818;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_17135_캐슬디펜스 {

    static int N,M,D;

    static class Obj {
        int i, j;
        Obj(int a, int b) {i=a;j=b;}
    }

    static boolean inRange(int i, int j) {
        return 0<=i && i<N && 0<=j && j<M;
    }

    static int[] di = {0,-1,0};
    static int[] dj = {-1,0,1};
    static Obj bfs(int[][] map, int p, int now) {
        Obj archer = new Obj(N - now,p);
        ArrayDeque<Obj> deque = new ArrayDeque<>();
        deque.addLast(new Obj(N-1-now,p));
        boolean[][] v = new boolean[N][M];

        if (map[N-1-now][p] == 1) return new Obj(N-1-now,p);
        v[N-1-now][p] = true;

        PriorityQueue<Obj> pq = new PriorityQueue<>((o1, o2) -> {
            int dist1 = Math.abs(archer.i - o1.i) + Math.abs(archer.j - o1.j);
            int dist2 = Math.abs(archer.i - o2.i) + Math.abs(archer.j - o2.j);
            if (dist1 == dist2) {
                return Integer.compare(o1.j, o2.j); // 같은 거리에 있는 적이라면, 가장 왼쪽 적 반환
            }
            return Integer.compare(dist1, dist2); // 가장 가까운 거리의 적 반환
        });

        while (!deque.isEmpty()) {
            Obj checkPoint = deque.removeFirst();
            for (int d=0;d<3;d++) {
                int i = checkPoint.i + di[d]; int j = checkPoint.j + dj[d];
                int val = Math.abs(archer.i - i) + Math.abs(archer.j - j);
                if (inRange(i,j) && !v[i][j] &&val <= D) {
                    deque.addLast(new Obj(i,j));
                    v[i][j] = true;
                    if (map[i][j] == 1)pq.add(new Obj(i,j));
                }
            }
        }
        if (pq.isEmpty()) return null;
        return pq.poll();
    }

    static void simulation(int[] archer, int[][] map) {
        int sum = 0;
        for (int day=0; day<N; day++) {
            ArrayList<Obj> targets = new ArrayList<>();
            for (int a : archer) {
                Obj target = bfs(map, a, day);
                if (target != null) {
                    targets.add(target);
                }
            }
            for (Obj t : targets) {
                if (map[t.i][t.j] == 1) {
                    map[t.i][t.j] = 0;
                    sum++;
                }
            }
        }
        max = Math.max(max, sum);
    }

    static int max = 0;
    static void permutation(int cnt, int p, int[] archer, int[][] map) {
        if (cnt==3) {
            int[][] tempMap = new int[N][M];
            for (int i=0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    tempMap[i][j] = map[i][j];
                }
            }
            simulation(archer, tempMap);
            return;
        }
        for (int i=p;i<M;i++) {
            archer[cnt] = i;
            permutation(cnt+1,i+1, archer, map);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0818/res/input_17135.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); D = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        permutation(0,0,new int[3], arr);
        System.out.println(max);
    }
}
