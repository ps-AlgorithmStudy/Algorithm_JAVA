package homework.M08.a0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_15683_감시 {
    static int n, m;
    static class Camera {
        int i; int j; int num;
        Camera(int a, int b, int c) {
            i = a; j= b; num = c;
        }
    }

    static int[] mi = {0,-1,0,1};
    static int[] mj = {-1,0,1,0};

    static int[][] cameraRange = {{0},{0,2},{0,1},{0,1,2},{0,1,2,3}};
    static int[] loop = {4,2,4,4,1};
    static int min = Integer.MAX_VALUE;
    public static boolean inRange(int i, int j) {
        return 0<=i && i<n && 0<=j && j<m;
    }

    public static void run(int p, int[][] map, ArrayList<Camera> cameras) {
        if (p==cameras.size()) {
            int cnt = 0;
            for (int i=0;i<n;i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] ==0) cnt++;
                }
            }
            min = Math.min(min,cnt);
            return;
        }

        Camera now = cameras.get(p);
        for (int l=0;l<loop[now.num];l++) {
            int[][] tempMap = new int[n][m];
            for (int i=0;i<n;i++) {
                for (int j = 0; j < m; j++) tempMap[i][j] = map[i][j];
            }

            for (int range:cameraRange[now.num]) {
                int t = range+l>3?range+l-4:range+l;
                int i = now.i + mi[t]; int j = now.j + mj[t];
                while (inRange(i,j)&&tempMap[i][j]!=6) {
                    tempMap[i][j] = -1;
                    i += mi[t]; j+= mj[t];
                }
            }
            run(p+1, tempMap, cameras);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0821/res/15961.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        ArrayList<Camera> cameras = new ArrayList<>(6);

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]!=0&&map[i][j]!=6)
                    cameras.add(new Camera(i,j, map[i][j]-1));
            }
        }
        run(0,map, cameras);

        System.out.println(min);
    }
}
