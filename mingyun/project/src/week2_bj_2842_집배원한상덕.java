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
    static int[] range;
    static int left;
    static int right;
    static int cnt;

    public static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n && v[x][y]==0 && range[left] <= s[x][y] && s[x][y] <= range[right];
    }

    public static boolean dfs(int x, int y) {
        if (m[x][y]=='K') cnt++;
        if (cnt==kCnt){
            return true;
        }

        boolean flag = false;
        for (int i=0;i<8;i++) {
            int tx = x+dx[i];
            int ty = y+dy[i];
            if (isRange(tx,ty)) {
                v[tx][ty] = 1;
                if (dfs(tx,ty)) return true;
            }
        }
        return flag;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_2842.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        m = new char[100][100];
        s = new int[100][100];

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
        Object[] tempArr = hashSet.toArray();

        range = new int[tempArr.length];
        for (int i=0;i< tempArr.length;i++) {
            range[i] = (int)tempArr[i];
        }

        int res = Integer.MAX_VALUE;

        while (right < range.length) {
            v = new int[100][100];
            if (isRange(x, y)) {
                v[x][y] = 1;
                cnt = 0;
                if (dfs(x, y)) {
                    res = Math.min(res, range[right] - range[left]);
                    left++;
                } else {
                    right++;
                }
            } else {
                right++;
            }
        }
        System.out.println(res);
    }
}