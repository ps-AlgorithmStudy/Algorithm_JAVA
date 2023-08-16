package homework.a0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_3109_빵집 {
    static int n,m;
    static int[] mv = {-1,0,1};

    static boolean inRange(int i) {
        return 0<=i && i<n;
    }

    static int dfs(int top, int move, char[][] map, boolean[][] v) {
        v[top][move] = true;
        if (move==m-1) {
            return 1;
        }

        for (int d = 0; d < 3; d++) {
            int tempTop = top + mv[d];
            if (inRange(tempTop) && map[tempTop][move+1]=='.' && !v[tempTop][move+1]) {
                if (dfs(tempTop, move+1, map, v)==1) {
                    return 1;
                }
            }
        }
        return 0;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0816/res/input_3109.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        boolean[][] v = new boolean[n][m];
        int sum = 0;

        for (int i=0;i<n;i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i=0;i<n;i++) {
            sum += dfs(i,0, map, v);
        }
        //for (boolean[] a:v) System.out.println(Arrays.toString(a));
        System.out.println(sum);
    }
}
