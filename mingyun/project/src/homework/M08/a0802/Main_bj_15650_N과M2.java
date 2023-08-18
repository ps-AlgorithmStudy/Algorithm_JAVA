package homework.M08.a0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_15650_N과M2 {
    static int n;
    static int mCnt;
    static StringBuilder sb = new StringBuilder();
    static void dfs(int cnt, int s, int[] arr) {
        if (cnt==mCnt) {
            for(int i:arr) sb.append(i).append(" ");
            sb.append("\n");
        }
        else {
            for (int i=s+1;i<=n;i++) {
                arr[cnt] = i;
                dfs(cnt+1, i, arr);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        mCnt = Integer.parseInt(st.nextToken());

        dfs(0, 0, new int[mCnt]);
        System.out.println(sb);
    }
}
