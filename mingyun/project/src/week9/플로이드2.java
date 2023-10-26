package week9;

import java.io.FileInputStream;
import java.util.*;

public class 플로이드2 {

    public static int n;
    public static int m;
    public static int cost[][] = new int[101][101];
    public static int route[][] = new int[101][101];

    public static void path(int start, int n) {
        for (int end = 1; end <= n; end++) {
            if (start == end)
                System.out.println(0);
            else {
                Stack<Integer> st = new Stack<>();
                int idx = route[start][end];
                while (idx != 0) {
                    st.push(idx);
                    idx = route[start][idx];
                }

                if(st.empty()) { // start에서 end로 갈 수 없는 경우
                    System.out.println(0);
                }
                else { // 경로 출력
                    System.out.print(st.size() + 1 + " ");
                    while (!st.empty()) {
                        System.out.print(st.peek() + " ");
                        st.pop();
                    }
                    System.out.println(end);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/플로이드.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j)
                    cost[i][j] = 1000001;
            }
        }

        for (int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            cost[x][y] = Math.min(cost[x][y], sc.nextInt());
            route[x][y] = x;

        }

        // 플로이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        route[i][j] = route[k][j];
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(cost[i][j] <= 100000) {
                    System.out.print(cost[i][j] + " ");
                }else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            path(i, n);
        }

    }
}