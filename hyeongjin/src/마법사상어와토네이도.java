import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {
    static int N; // 격자 크기
    static int result; // 격자 밖으로 나간 모래
    static int[][] map; // 격자 모래 양

    // 토네이도 진행 방향
    static int[] di = { 0, 1, 1, 1, 0,-1,-1,-1};
    static int[] dj = {-1,-1, 0 ,1, 1, 1, 0,-1};

//    // 맵 출력
//    static void printMap(){
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] +" ");
//            }System.out.println();
//        }
//    }

    // 격자 밖으로 나가는지
    static boolean isOut(int i,int j){
        return (i < 0 || i >= N || j < 0 || j>=N);
    }

    // 현재 위치의 모래를 각각 퍼뜨림
    static void spread(int i, int j, int d){
        int sand = map[i][j];
        map[i][j] = 0;
        int ai = i + di[d];
        int aj = j + dj[d];
        int alpha = sand;
        alpha -= moveSand(i + di[d]*2      , j + dj[d]*2       , sand * 0.05); // 앞앞

        alpha -= moveSand(i + di[(d+2)%8]  , j + dj[(d+2)%8]   , sand * 0.07); // 왼
        alpha -= moveSand(i + di[(d+6)%8]  , j + dj[(d+6)%8]   , sand * 0.07); // 오
        alpha -= moveSand(i + di[(d+2)%8]*2, j + dj[(d+2)%8]*2 , sand * 0.02); // 왼왼
        alpha -= moveSand(i + di[(d+6)%8]*2, j + dj[(d+6)%8]*2 , sand * 0.02); // 오오

        alpha -= moveSand(i + di[(d+1)%8]  , j + dj[(d+1)%8]   , sand * 0.1 ); // 11시
        alpha -= moveSand(i + di[(d+7)%8]  , j + dj[(d+7)%8]   , sand * 0.1 ); // 1시

        alpha -= moveSand(i + di[(d+3)%8]  , j + dj[(d+3)%8]   , sand * 0.01); // 7시
        alpha -= moveSand(i + di[(d+5)%8]  , j + dj[(d+5)%8]   , sand * 0.01); // 5시

        // 남은 모래는 alpha로 처리
        moveSand(ai, aj, alpha);
    }

    // 해당 위치로 모래가 이동
    // 격자 바깥으로 넘어가는 모래는 결과값에 누적
    static int moveSand(int i , int j, double sand){
        int fSand = (int)Math.floor(sand);
        if(isOut(i,j))
            result += fSand;
        else
            map[i][j] += fSand;
        return fSand;
    }
    static void moveTonado(int i , int j, int d, int cnt, int toGo, int toGoCnt){
        // spread();
        spread(i,j,d);
        // 도착지점이면 종료
        if(i== 0 && j==0) return;

        // 1. 방향을 바꿀 때가 되었는지
        if(toGoCnt == toGo/2 || toGoCnt == toGo) d = (d+2)%8;

        // 방향 조정
        int ni = i + di[d];
        int nj = j + dj[d];

        // 2. 유지한 방향으로 가야할 만큼 갔는지
        if(toGoCnt == toGo){
            toGo = toGo+2;
            toGoCnt = 0;
        }

        moveTonado(ni, nj, d, cnt+1, toGo, toGoCnt+1);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        moveTonado(N/2,N/2, 0,0, 2, 0);
//        printMap();
        System.out.println(result);
    }
}
