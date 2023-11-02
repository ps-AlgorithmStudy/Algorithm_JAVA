package week12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트 {
    static int[] arr;
    static boolean[] visited, isCycle;
    static int n, cnt;

    static void dfs(int p) {
        visited[p] = true;
        int next = arr[p];

        if (!visited[next]) {
            dfs(next);
        } else if (!isCycle[next]) {
            // 순환 구조를 발견하면 해당 노드부터 다시 방문하여 순환 노드 수를 계산
            for (int i = next; i != p; i = arr[i]) {
                cnt++;
            }
            cnt++;  // 순환 시작 노드 포함
        }

        // 해당 노드 방문을 마친 후 순환 구조에 속한다고 표시
        isCycle[p] = true;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("mingyun/project/src/week12/텀프로젝트"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            visited = new boolean[n];
            isCycle = new boolean[n];
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken()) - 1;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) dfs(i);
            }
            System.out.println(n - cnt);
        }
    }
}