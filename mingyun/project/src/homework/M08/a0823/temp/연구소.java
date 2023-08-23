package homework.M08.a0823.temp;

import java.io.*;
import java.util.*;

public class 연구소 {

    static int[][] map;
    static ArrayList<Point> points = new ArrayList<>(1000);
    static ArrayList<Point> virus = new ArrayList<>(1000);
    static int n,m;
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};
    static int result = 0;
    static class Point {
        int i; int j;
        Point(int a, int b) {
            i = a; j = b;
        }
    }

    static boolean inRange(int i, int j) {
        return 0<=i && i<n && 0<=j && j<m;
    }

    static void check() {
        int[][] tempMap = new int[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) tempMap[i][j] = map[i][j];
        }

        for (Point p:virus) {
            ArrayDeque<Point> deque = new ArrayDeque<>(100);
            deque.add(p);
            while (!deque.isEmpty()) {
                Point now = deque.removeFirst();
                for (int d=0;d<4;d++) {
                    int mi = now.i + di[d];
                    int mj = now.j + dj[d];
                    if (inRange(mi,mj) && tempMap[mi][mj]==0) {
                        tempMap[mi][mj] = 2;
                        deque.addLast(new Point(mi,mj));
                    }
                }
            }
        }

        int r = 0;
        for (int[] t:tempMap) {
            for (int a:t) {
                if (a==0) r++;
            }
        }
        result = Math.max(result,r);
    }

    static void permutation(int p, int cnt, Point[] choosed) {
        if (cnt == 3) {
            for (Point c:choosed) map[c.i][c.j] = 1;
            check();
            for (Point c:choosed) map[c.i][c.j] = 0;
            return;
        }
        for (int i=p+1; i<points.size();i++) {
            choosed[cnt] = points.get(i);
            permutation(i,cnt+1, choosed);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0823/res/연구소.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==0) points.add(new Point(i,j));
                if (map[i][j]==2) virus.add(new Point(i,j));
            }
        }

        permutation(-1,0, new Point[3]);
        System.out.println(result);
    }
}
