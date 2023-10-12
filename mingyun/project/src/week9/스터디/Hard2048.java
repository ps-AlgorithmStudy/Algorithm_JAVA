package week9.스터디;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hard2048 {

    static int n;
    static int max=0;
    static int[] merge(int[] target) {
        int p=0;
        int[] result = new int[n];
        boolean[] fixed = new boolean[n];
        for (int a:target) {
            max = Math.max(max, a);
            if (a != 0) {
                if (p==0) result[p++] = a;
                else {
                    if (!fixed[p-1] && result[p-1] == a) {
                        result[p-1] += a;
                        max = Math.max(max, result[p-1]);
                        fixed[p-1] = true;
                    }
                    else result[p++] = a;
                }
            }
        }
        return result;
    }

    static int[] reverse(int[] target) {
        int[] result = new int[n];
        for (int i=0;i<n;i++)
            result[i] = target[n-i-1];
        return result;
    }

    static int[][] left(int[][] map) {
        int[][] result = new int[n][n];
        for (int i=0;i<n;i++) result[i] = merge(map[i]);
        return result;
    }

    static int[][] right(int[][] map) {
        int[][] result = new int[n][n];
        for (int i=0;i<n;i++) {
            int[] target = reverse(map[i]);
            result[i] = reverse(merge(target));
        }
        return result;
    }
    static int[][] top(int[][] map) {
        int[][] result = new int[n][n];
        for (int i=0;i<n;i++) {
            int[] target = new int[n];
            for (int j=0;j<n;j++) target[j] = map[j][i];
            target = merge(target);
            for (int j=0;j<n;j++) result[j][i] = target[j];
        }
        return result;
    }

    static int[][] down(int[][] map) {
        int[][] result = new int[n][n];
        for (int i=0;i<n;i++) {
            int[] target = new int[n];
            for (int j=0;j<n;j++) target[j] = map[j][i];
            target = reverse(merge(reverse(target)));
            for (int j=0;j<n;j++) result[j][i] = target[j];
        }
        return result;
    }

    static boolean match(int[][] target, int[][] map) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (map[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    static void dfs(int[][] map, int cnt) {
        if (cnt!=10) {
            int[][] temp = left(map);
            if (!match(temp, map)) dfs(temp, cnt+1);

            temp = right(map);
            if (!match(temp, map)) dfs(temp, cnt+1);

            temp = top(map);
            if (!match(temp, map)) dfs(temp, cnt+1);

            temp = down(map);
            if (!match(temp, map)) dfs(temp, cnt+1);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/스터디/2048.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(map, 0);
        System.out.println(max);
    }
}
