import java.io.*;
import java.util.*;

public class Main_bj_19236_청소년상어 {
    static final int N = 4;
    
    static int[][] maps;
    static List<Fish> fish;
    
    // 방향 주의!
    // u - nw - l - sw - d - se - e - ne;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    
    static class Fish {
        int id;
        int dir;
        
        public Fish(int id, int dir) {
            super();
            this.id = id;
            this.dir = dir;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Fish [id=").append(id).append(", dir=").append(dir).append("]");
            return builder.toString();
        }        
        
    }
    
    static void solution() {
        for(int[] m : maps) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println(fish);
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // id값을 기록
        maps = new int[N][N];
        
        // id값에 해당하는 fish를 기록
        fish = new ArrayList<>(16);
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int id = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                maps[i][j] = id;
                fish.add(new Fish(id, dir));
            }
        }
        
        solution();
    }
}
