package implementation;

import java.io.*;
import java.util.*;
public class week18_bj_1277_발전소설치 {
    static int N, W;
    static double M;
    static int[][] plant;
    static Double[][] g;

    static double dSquare(int[] p1, int[] p2) {
        double d = (double) (p1[0] - p2[0]) * (p1[0] - p2[0]);
        d += (double) (p1[1] - p2[1]) * (p1[1] - p2[1]);
        return d;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        plant = new int[N][2];
        g = new Double[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            plant[i][0] = Integer.parseInt(st.nextToken());
            plant[i][1] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                double d = dSquare(plant[i], plant[j]);
                if (d <= M * M) g[i][j] = g[j][i] = Math.sqrt(d);
            }
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            g[start][end] = g[end][start] = 0.0;
        }

        double[] minDist = new double[plant.length];
        for (int i = 0; i < plant.length; i++) {
            minDist[i] = Double.MAX_VALUE;
        }

        PriorityQueue<Double[]> pq = new PriorityQueue<>(
                (o1, o2) -> (Double.compare(o1[1],o2[1]))
        );
        pq.add(new Double[] {0.0, 0.0});

        while(!pq.isEmpty()){
            Double[] cur = pq.poll();
            int curNo = cur[0].intValue();
            if (minDist[curNo] < cur[1]) continue;
            for (int i = 0; i < plant.length; i++) {
                if (g[curNo][i] == null) continue;
                if (cur[1] + g[curNo][i] < minDist[i]){
                    minDist[i] = cur[1] + g[curNo][i];
                    pq.add(new Double[] { (double)i, minDist[i]});
                }
            }
        }

        System.out.println((int)(minDist[plant.length - 1] * 1000));
    }
}
