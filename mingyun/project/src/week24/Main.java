package week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int result;
    static List<int[][]> spreadMap = new ArrayList<>();
    static int mid;
    static int n;
    static int[] mi = {0, 1, 0, -1};
    static int[] mj = {-1, 0, 1, 0};

    public static int[][] rotate(int[][] arr) {
        int[][] r = new int[5][];
        for (int i = 0; i < 5; i++) {
            int[] temp = new int[5];
            for (int j = 0; j < 5; j++) {
                temp[j] = arr[j][4 - i];
            }
            r[i] = temp;
        }
        return r;
    }

    public static void spread(int[][] map, int si, int sj, int d) {
        int[][] targetSpreadMap = spreadMap.get(d);
        int value = map[si][sj];
        int sum = 0;
        int fi = si - mid + (n - 3) / 2 - 1;
        int fj = sj - mid + (n - 3) / 2 - 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sum += value * targetSpreadMap[i][j] / 100;
                try {
                    map[fi + i][fj + j] = map[fi + i][fj + j] + (value * targetSpreadMap[i][j] / 100);
                } catch (Exception ignore) {
                    result += value * targetSpreadMap[i][j] / 100;
                }
            }
        }

        try {
            map[si + mi[d]][sj + mj[d]] = map[si + mi[d]][sj + mj[d]] + value - sum;
        } catch (Exception ignore) {
            result += value - sum;
        }
        map[si][sj] = 0;
    }

    public static void main(String[] args) throws Exception {
        spreadMap.add(new int[][]{{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, 0, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}});
        for (int i = 0; i < 3; i++) {
            spreadMap.add(rotate(spreadMap.get(i)));
        }
        result = 0;

        System.setIn(Files.newInputStream(Paths.get("mingyun/project/src/week24/input")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        mid = n / 2;

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int d = 0;
        int i = mid;
        int j = mid;
        int max = 1;
        int moveCount = 0;
        int cnt = 0;
        while (++cnt < n * n) {
            moveCount++;
            i += mi[d];
            j += mj[d];
            spread(map, i, j, d);
            if (moveCount == max) {
                moveCount = 0;
                d++;
                if (d == 4) {
                    max++;
                    d = 0;
                }
                if (d == 2) {
                    max++;
                }
            }
        }

        System.out.println(result);
    }
}
