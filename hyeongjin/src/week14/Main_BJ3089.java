package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_BJ3089 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 좌표 범위가 -100000 < x,y < 100000
        TreeSet<Integer>[] X = new TreeSet[200000];
        TreeSet<Integer>[] Y = new TreeSet[200000];

        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())+ 100000;
            int y = Integer.parseInt(st.nextToken())+ 100000;

            if(X[x] == null) X[x] = new TreeSet<>();
            if(Y[y] == null) Y[y] = new TreeSet<>();

            // 각 x,y좌표에 대응하는 상대 좌표 입력
            X[x].add(y);
            Y[y].add(x);
        }
        String cmds = br.readLine();

        // 원점 (+100000)
        int nextX = 100000;
        int nextY = 100000;
        for (int i = 0; i < M; i++) {
            char cmd = cmds.charAt(i);
            switch(cmd){
                case 'R':
                    nextX = Y[nextY].higher(nextX);
                    break;
                case 'L' :
                    nextX = Y[nextY].lower(nextX);
                    break;
                case 'U':
                    nextY = X[nextX].higher(nextY);
                    break;
                case 'D' :
                    nextY = X[nextX].lower(nextY);
                    break;
            }
        }
        System.out.printf("%d %d",nextX-100000,nextY-100000);
    }
}