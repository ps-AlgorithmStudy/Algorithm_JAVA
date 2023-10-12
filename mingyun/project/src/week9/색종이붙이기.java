package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 색종이붙이기 {
    static int[][] map = new int[10][10];
    static int[] papers = new int[5];
    static int result;

    static boolean inRange(int i, int j) {
        return 0<=i && i<10 && 0<= j && j<10;
    }

    static boolean isValid(int pi, int pj, int range) {
        for (int i=pi; i<pi+range; i++) {
            for (int j=pj; j<pj+range; j++) {
                if (!inRange(i,j) || map[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void coverMap(int pi, int pj, int range, int d) {
        for (int i=pi; i<pi+range; i++) {
            for (int j=pj; j<pj+range; j++) {
                map[i][j] = d;
            }
        }
    }

    static void dfs(int i, int j, int cnt) {
        if (cnt >= result) {
            return;
        }
        if (i >= 9 && j > 9) {
            result = Math.min(result, cnt);
        }
        else if (j>9) {
            dfs(i+1, 0,cnt);
        }
        else {
            if (map[i][j] == 1) {
                for (int d=4;d>=0;d--) {
                    if (papers[d] > 0 && isValid(i,j,d+1)) {
                        papers[d]--;
                        coverMap(i,j,d+1,0);
                        dfs(i,j+1, cnt+1);
                        coverMap(i,j,d+1,1);
                        papers[d]++;
                    }
                }
            }
            else {
                dfs(i,j+1, cnt);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/색종이붙이기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0;i<10;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<10;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(papers, 5);
        result = Integer.MAX_VALUE;

        dfs(0,0,0);
        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }
}
