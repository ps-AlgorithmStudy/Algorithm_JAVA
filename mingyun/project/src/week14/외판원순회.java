package week14;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 외판원순회 {
    static int n, INF = 987654321;
    static int[][] map;
    static HashMap<Integer, Integer>[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week14/외판원순회"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new HashMap[n];

        for (int i=0; i<n;i++) {
            dp[i] = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0,1));
    }
    public static int dfs(int pos, int visit) {
        if (visit == (1 << n) - 1) {
            return map[pos][0] != 0 ? map[pos][0] : INF;
        }

        if (dp[pos].containsKey(visit)) {
            return dp[pos].get(visit);
        }

        int min = INF;
        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) == 0 && map[pos][i] != 0) {
                int cost = dfs(i, visit | (1 << i)) + map[pos][i];
                min = Math.min(min, cost);
            }
        }

        dp[pos].put(visit, min);
        return min;
    }


}
