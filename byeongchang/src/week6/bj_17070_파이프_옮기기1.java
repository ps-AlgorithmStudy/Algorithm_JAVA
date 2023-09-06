package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_17070_파이프_옮기기1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br,st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        Pipe[][] house = new Pipe[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp==1) house[i][j] = new Pipe(true);
                else house[i][j] = new Pipe(0,0,0);
            }
        }
        house[0][1].horiz = 1;
        for(int i=2; i<N; i++) {
            if(house[0][i].wall) break;
            house[0][i].horiz = 1;
        }

        return pipe(house);
    }
    public static int pipe(Pipe[][] house) {
        int N = house.length;
        for(int i=1; i<N; i++) {
            for(int j=1; j<N; j++) {
                if(house[i][j].wall) continue;
                if(house[i-1][j].wall) house[i][j].horiz = house[i][j-1].diag+house[i][j-1].horiz;
                else if(house[i][j-1].wall) house[i][j].verti = house[i-1][j].diag+house[i-1][j].verti;
                else if(house[i-1][j-1].wall) {
                    house[i][j].horiz = house[i][j-1].horiz;
                    house[i][j].verti = house[i-1][j].verti;
                }
                else {
                    house[i][j].horiz = house[i][j-1].diag+house[i][j-1].horiz;
                    house[i][j].verti = house[i-1][j].diag+house[i-1][j].verti;
                    house[i][j].diag = house[i-1][j-1].diag+house[i-1][j-1].horiz+house[i-1][j-1].verti;
                }
            }
        }
        //for(Pipe[] p: house) System.out.println(Arrays.toString(p));
        return house[N-1][N-1].sum();
    }
    static class Pipe {
        int horiz;
        int verti;
        int diag;
        boolean wall=false;
        public Pipe(int horiz, int verti, int diag) {
            this.horiz = horiz;
            this.verti = verti;
            this.diag = diag;
        }

        public Pipe(boolean wall) {
            this.wall = wall;
        }
        int sum() {
            return horiz+verti+diag;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Pipe{");
            sb.append(horiz);
            sb.append(", ").append(verti);
            sb.append(", ").append(diag);
            sb.append(", ").append(wall);
            sb.append('}');
            return sb.toString();
        }
    }
}
