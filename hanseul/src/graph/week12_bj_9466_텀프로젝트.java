package graph;
import java.io.*;
import java.util.*;

public class week12_bj_9466_텀프로젝트 {
    static int num;
    static int[] arr;
    static boolean[] v;
    static int[] isCircle;
    static ArrayDeque<Integer> q;
    static int cnt;
    public static int dfs(int idx) {
        v[idx] = true;
        int next = arr[idx];
        if (!v[next]){
            dfs(next);
        }
        else if (isCircle[next] == 0){
            for (int i = next; i != idx ; i = arr[i]) {
                cnt++;
            }
            cnt++;
        }
        isCircle[idx] = 1;
        return 0;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            arr = new int[num + 1];
            isCircle = new int[num + 1];
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= num; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            cnt = 0;
            v = new boolean[num + 1];
            for (int j = 1; j <= num; j++) {
                dfs(j);
            }
            sb.append(num - cnt).append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
4
3
2 3 3
5
2 3 4 5 4
4
2 3 4 2
5
1 1 1 1 1
 */

