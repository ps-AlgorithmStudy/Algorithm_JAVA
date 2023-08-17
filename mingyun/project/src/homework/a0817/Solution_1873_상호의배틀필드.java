package homework.a0817;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_1873_상호의배틀필드 {
    static class Tank {
        int i, j, p;
    }
    static boolean inRange(int i, int j, int n, int m) {
        return 0<=i && i<n&&0<=j&&j<m;
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0817/res/input_1873.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        char[] state = {'<','>','^','v'};
        int[][] mv = {{0,-1},{0,1},{-1,0},{1,0}};
        StringBuilder sb = new StringBuilder();

        Tank tank = new Tank();
        HashMap<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('L',0);hashMap.put('R',1);hashMap.put('U',2);hashMap.put('D',3);

        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken()), m=Integer.parseInt(st.nextToken());
            char[][] map = new char[n][];
            for (int i=0;i<n;i++) {
                map[i] = br.readLine().toCharArray();
                for (int j=0;j<m;j++) {
                    for(int c=0;c<4;c++) {
                        if (state[c]==map[i][j]) {
                            tank.i = i; tank.j=j; tank.p=c;
                            map[i][j] = '.';
                        }
                    }
                }
            }
            br.readLine();
            for(char c:br.readLine().toCharArray()) {
                if (c=='S') {
                    int i = tank.i + mv[tank.p][0];
                    int j = tank.j + mv[tank.p][1];
                    while (inRange(i,j,n,m)) {
                        if (map[i][j]=='*') {
                            map[i][j] = '.';
                            break;
                        }
                        if (map[i][j]=='#') break;
                        i += mv[tank.p][0];
                        j += mv[tank.p][1];
                    }
                }
                else {
                    tank.p = hashMap.get(c);
                    int i = tank.i + mv[tank.p][0];
                    int j = tank.j + mv[tank.p][1];
                    if (inRange(i,j,n,m) && map[i][j]=='.') {
                        tank.i = i;
                        tank.j = j;
                    }
                }
            }
            map[tank.i][tank.j] = state[tank.p];
            sb.append("#").append(TC).append(" ");
            for (char[] a:map) {
                for (char b:a) {
                    sb.append(b);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
