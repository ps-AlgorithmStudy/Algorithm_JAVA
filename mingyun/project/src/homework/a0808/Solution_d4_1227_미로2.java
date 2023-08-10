package project.src.homework.a0808;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution_d4_1227_미로2 {
    static int gi,gj;
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    static boolean inRange(int i, int j) {
        return 0<=i && i<100 && 0<=j && j<100;
    }

    static boolean dfs(int pi, int pj, boolean[][] map, boolean[][] v) {
        v[pi][pj] = true;
        if (pi==gi && pj==gj) {
            return true;
        }

        for (int i=0;i<4;i++) {
            int mi = pi + di[i];
            int mj = pj + dj[i];
            if (inRange(mi,mj) && !map[mi][mj] && !v[mi][mj]) {
                if (dfs(mi,mj,map,v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(Files.newInputStream(Paths.get("mingyun/project/res/input_d4_1227.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int T=1;T<=10;T++) {
            br.readLine();
            int si=0, sj=0;
            boolean[][] v = new boolean[100][100];
            boolean[][] map = new boolean[100][100];
            for (int i=0;i<100;i++) {
                char[] temp = br.readLine().toCharArray();
                for (int j=0;j<100;j++) {
                    if (temp[j]=='1') map[i][j] = true;
                    if (temp[j]=='2') {
                        si=i;
                        sj=j;
                    }
                    if (temp[j]=='3') {
                        gi=i;
                        gj=j;
                    }
                }
            }
            sb.append("#").append(T).append(" ").append(dfs(si,sj, map, v)?1:0).append("\n");
        }
        System.out.println(sb);
    }
}
