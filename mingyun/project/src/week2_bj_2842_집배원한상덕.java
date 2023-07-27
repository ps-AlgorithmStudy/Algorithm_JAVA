import java.io.*;
import java.util.*;

public class week2_bj_2842_집배원한상덕 {
    static int[] dx = {-1,1,0,0,-1,1,1,-1};
    static int[] dy = {0,0,-1,1,-1,-1,1,1};
    static int n;
    static char[][] m;
    static int[][] s;
    static int[][] v;
    static int kCnt=0;
    static Object[] range;
    static int left;
    static int right;

    public static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n && v[x][y]==0 && (int)range[left] <= s[x][y] && s[x][y] <= (int)range[right];
    }

    public static void dfs(int x, int y, int cnt) {
        for (int i=0;i<8;i++) {
            int tx = x+dx[i];
            int ty = x+dy[i];
            if (isRange(tx,ty)) {
                v[tx][ty] = 1;
                dfs(tx,ty,cnt);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_2842.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        m = new char[n][n];
        s = new int[n][n];

        int x = 0, y=0;
        for (int i=0;i<n;i++) {
            m[i] = br.readLine().toCharArray();
            for (int j=0;j<n;j++) {
                if (m[i][j] == 'P') {
                    x = i;
                    y = j;
                }
                else if (m[i][j] == 'K') {
                    kCnt++;
                }
            }
        }

        int max = 0, min=Integer.MAX_VALUE;
        Set<Integer> hashSet = new TreeSet<>();
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max,s[i][j]);
                min = Math.min(min,s[i][j]);
                hashSet.add(s[i][j]);
            }
        }
        range = hashSet.toArray();

        for (right = 0;right<range.length;right++) {
            while (left<=right && left<range.length) {
                v = new int[n][n];
                if (isRange(x,y)) {
                    dfs(x,y,0);
                }
            }
        }
        System.out.println((int) range[0]);
    }
}