package homework.M08.a0823;

import java.io.*;
import java.util.*;

public class 다리만들기2 {
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return from + " " + to + " " + weight;
        }
    }
    static class EdgeMaker {
        int[] di = {0,1,0,-1};
        int[] dj = {1,0,-1,0};
        int n,m;
        boolean[][] map;
        boolean inRange(int i, int j) {
            return 0<=i && i<n && 0<=j && j<m;
        }
        int[][] section;
        void getSection(int i, int j, int sectionNum) {
            ArrayDeque<int[]> deque = new ArrayDeque<>();
            deque.add(new int[]{i,j});
            section[i][j] = sectionNum;
            while (!deque.isEmpty()) {
                int[] now = deque.removeFirst();
                for (int d=0;d<4;d++) {
                    int mi = now[0] + di[d];
                    int mj = now[1] + dj[d];
                    if (inRange(mi, mj) && map[mi][mj] && section[mi][mj]==0) {
                        section[mi][mj] = sectionNum;
                        deque.add(new int[]{mi,mj});
                    }
                }
            }

        }
        int sectionNum=0;
        int[][] relationMap;
        void mapping() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0823/res/다리만들기2.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
            map = new boolean[n][m];

            for (int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++)
                    map[i][j] = Integer.parseInt(st.nextToken())==1;
            }

            section = new int[n][m];
            for (int i=0;i<n;i++) {
                for (int j=0;j<m;j++) {
                    if (section[i][j]==0 && map[i][j]) {
                        getSection(i,j,++sectionNum);
                    }
                }
            }
        }
        void getLength(int i, int j) {
            for (int d=0;d<4;d++) {
                int cnt = 0;
                int mi = i + di[d];
                int mj = j + dj[d];
                if (inRange(mi,mj) && !map[mi][mj]) {
                    while(inRange(mi,mj)) {
                        if (map[mi][mj]) {
                            int x = section[mi][mj] - 1;
                            int y = section[i][j] - 1;
                            if (cnt >=2 && relationMap[x][y] > cnt) {
                                relationMap[x][y] = cnt;
                                relationMap[y][x] = cnt;
                            }
                            break;
                        }
                        cnt++;
                        mi += di[d];
                        mj += dj[d];
                    }
                }
            }
        }
        void getNode() {
            relationMap = new int[sectionNum][sectionNum];
            for (int i=0;i<sectionNum;i++) {
                for (int j=0;j<sectionNum;j++) {
                    relationMap[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i=0;i<n;i++) {
                for (int j=0;j<m;j++) {
                    if (map[i][j]) {
                        getLength(i,j);
                    }
                }
            }
        }

        ArrayList<Edge> run() throws Exception {
            mapping();
            getNode();
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i=0;i<sectionNum;i++) {
                for (int j=i;j<sectionNum;j++) {
                    if (relationMap[i][j]!=Integer.MAX_VALUE) {
                        edges.add(new Edge(i,j,relationMap[i][j]));
                    }
                }
            }
            return edges;
        }
    }
    static class Mst {
        int V;
        int[] parents;

        void make() {
            parents = new int[V];
            for (int i=0;i<V;i++) parents[i] = i;
        }

        int find(int a) {
            if (parents[a]==a) return a;
            return parents[a] = find(parents[a]);
        }

        boolean union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);
            if (aRoot == bRoot) return false;
            parents[bRoot] = aRoot;
            return true;
        }

        Mst(int V,ArrayList<Edge> edges) {
            this.V = V;
            edges.sort(((o1, o2) -> Integer.compare(o1.weight, o2.weight)));
            make();

            int cnt=0;
            int result=0;
            boolean flag = false;
            for (Edge e:edges) {
                if (union(e.from, e.to)) {
                    result += e.weight;
                    if (++cnt == V-1) {
                        System.out.println(result);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) System.out.println(-1);
        }
    }
    public static void main(String[] args) throws Exception {
        EdgeMaker nodeMaker = new EdgeMaker();
        ArrayList<Edge> edges = nodeMaker.run();
        int sectionNum = nodeMaker.sectionNum;
        Mst mst = new Mst(sectionNum, edges);
    }
}
