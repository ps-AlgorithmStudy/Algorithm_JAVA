package week2;

import java.io.*;
import java.util.*;

public class bj_16236_아기상어 {
    int n;
    public boolean inRange(int i, int j) {
        return 0<=i && 0<=j && i<n && j<n;
    }

    public class MvObject {
        public int i;
        public int j;
        public int dept;
        MvObject(int i, int j, int dept) {
            this.i = i;
            this.j = j;
            this.dept = dept;
        }
    }

    public void solution() throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int size = 2;
        int[][] map;

        map = new int[n][n];

        int pi=0, pj=0;

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==9) {
                    pi = i;
                    pj = j;
                    map[i][j] = 0;
                }
            }
        }

        boolean flag = true;

        int eat = 0;
        int result = 0;

        int[] di = {-1,0,0,1};
        int[] dj = {0,-1,1,0};


        MvObject rObject = new MvObject(pi, pj, 0);
        while (flag) {
            flag=false;
            Deque<MvObject> mvObjects = new ArrayDeque<>();
            int[][] v = new int[n][n];
            mvObjects.add(rObject);
            v[rObject.i][rObject.j] = 1;
            MvObject mvObject;

            while (!mvObjects.isEmpty()) {
                mvObject = mvObjects.removeFirst();
                if (map[mvObject.i][mvObject.j]!=0 && map[mvObject.i][mvObject.j] < size) {
                    if (!flag) rObject = mvObject;
                    else if (rObject.dept >= mvObject.dept) {
                        if (rObject.i > mvObject.i || rObject.i == mvObject.i && rObject.j > mvObject.j) rObject = mvObject;
                    }
                    flag = true;
                }
                else {
                    for (int i=0;i<4;i++) {
                        int ti = mvObject.i + di[i];
                        int tj = mvObject.j + dj[i];
                        if (inRange(ti,tj)&&v[ti][tj] == 0 && map[ti][tj] <= size) {
                            mvObjects.add(new MvObject(ti,tj, mvObject.dept+1));
                            v[ti][tj] = 1;
                        }
                    }
                }
            }
            if (flag) {
                eat++;
                map[rObject.i][rObject.j] = 0;
                result = rObject.dept;
                if (eat == size) {
                    size++;
                    eat = 0;
                }
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) throws Exception {
        new bj_16236_아기상어().solution();
    }
}
