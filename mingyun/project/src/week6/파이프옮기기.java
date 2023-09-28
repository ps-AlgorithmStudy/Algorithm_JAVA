package project.src.week6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파이프옮기기 {
    static class Work {
        boolean[][] map;
        int n;
        Work() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/week6/res/부분합.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            map = new boolean[n][n];
            for (int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<n;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) == 1;
                }
            }
        }

        int[] di = {0, 1, 1};
        int[] dj = {1, 1, 0};
        int result = 0;

        int getResult() {
            dfs(0,1, 0);
            return result;
        }

        boolean inRange(int i, int j) {
            return 0<=i && i<n && 0<=j && j<n;
        }

        boolean check(int i, int j) {
            for (int d=0;d<3;d++) {
                int mi = i + di[d], mj = j + dj[d];
                if (map[mi][mj]) return false;
                if (!inRange(mi,mj)) return false;
            }
            return true;
        }

        void dfs(int i, int j, int shape) {
            if (i==n-1 &&j==n-1) {
                result++;
            }
            else {
                for (int d=0;d<3;d++) {
                    int mi = i + di[d], mj = j + dj[d];
                    if (Math.abs(shape - d)<=1) {
                        if (inRange(mi,mj) && !map[mi][mj]) {
                            boolean flag = true;
                            if (d==1) {
                                flag = check(i,j);
                            }
                            if (flag) dfs(mi,mj,d);
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Work work = new Work();
        System.out.println(work.getResult());
    }
}