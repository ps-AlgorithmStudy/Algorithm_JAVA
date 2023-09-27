package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_12100_2048_Easy {
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("res/bj_12100.txt"));
//        File file = new File("md/12100.txt");
//        PrintStream printStream = new PrintStream(new FileOutputStream(file));
//        PrintStream sysOut = System.out;
//        System.setOut(printStream);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
//        for(int tc=1; tc<=22; tc++)
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int check = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) check++;
            }
        }
        char[] direc = {'U', 'R', 'D', 'L'};
        if(check == 1) return getMax(map);
        int max = -1;
        ArrayList<int[]> orderList = new ArrayList<>(1024);
        perm(0,4,5,new int[5],orderList);
        for(int[] order: orderList) {
            max = Math.max(max,simulation2048(arrCopy(map), direc, order));
        }
        if(max==-1) return getMax(map);
        return max;
        /* for test */
        //return simulation2048(arrCopy(map), direc, new int[]{3});
    }

    public static int simulation2048(int[][] map, char[] direc, int[] order) {
        int N = map.length;
        int max = -1;
        // 방향이 결정되면
        for(int dirIdx: order) {
            switch (direc[dirIdx]) {
                case 'U':
                    // 방향 반대방향으로 map 탐색하면서 숫자 합치고 옮기기
                    for(int j=0; j<N; j++){
                        for(int i=0; i<N; i++){
                            for(int k=i+1; k<N; k++){
                                if(map[k][j]==0) continue;
                                if(map[i][j]==0) {
                                    map[i][j] = map[k][j];
                                    map[k][j] = 0;
                                    continue;
                                }
                                else if(map[i][j] == map[k][j]) {
                                    map[i][j] += map[k][j];
                                    map[k][j] = 0;
                                }
                                break;
                            }
                            if(max<map[i][j]) max = map[i][j];
                        }
                    }
                    break;
                case 'D':
                    for(int j=0; j<N; j++){
                        for(int i=N-1; i>=0; i--) {
                            for(int k=i-1; k>=0; k--) {
                                if(map[k][j]==0) continue;
                                if(map[i][j]==0) {
                                    map[i][j] = map[k][j];
                                    map[k][j] = 0;
                                    continue;
                                }
                                else if(map[i][j] == map[k][j]) {
                                    map[i][j] += map[k][j];
                                    map[k][j] = 0;
                                }
                                break;
                            }
                            if(max<map[i][j]) max = map[i][j];
                        }
                    }
                    break;
                case 'L':
                    for(int i=0; i<N; i++) {
                        for(int j=0; j<N; j++) {
                            for(int k=j+1; k<N; k++) {
                                if(map[i][k]==0) continue;
                                if(map[i][j]==0) {
                                    map[i][j] = map[i][k];
                                    map[i][k] = 0;
                                    continue;
                                } else if (map[i][j] == map[i][k]) {
                                    map[i][j] += map[i][k];
                                    map[i][k] = 0;
                                }
                                break;
                            }
                            if(max<map[i][j]) max = map[i][j];
                        }
                    }
                    break;
                case 'R':
                    for(int i=0; i<N; i++) {
                        for(int j=N-1; j>=0; j--) {
                            for(int k=j-1; k>=0; k--) {
                                if(map[i][k]==0) continue;
                                if(map[i][j]==0) {
                                    map[i][j] = map[i][k];
                                    map[i][k] = 0;
                                    continue;
                                } else if (map[i][j] == map[i][k]) {
                                    map[i][j] += map[i][k];
                                    map[i][k] = 0;
                                }
                                break;
                            }
                            if(max<map[i][j]) max = map[i][j];
                        }
                    }
                    break;
            }
            /* for test */
//            System.out.println(direc[dirIdx]);
//            for(int[] m:map) System.out.println(Arrays.toString(m));
//            System.out.println();
        }
//        System.out.println("-----------------------------------------------------");
        // 방향이 결정되면
        // 방향 반대방향으로 map 탐색하면서 숫자 합치기
        // 방향 끝으로 숫자 옮기기
        return max;
    }

    // 이동 순서 정하기 - 중복순열
    public static void perm(int cnt, int a, int b, int[] candidate, ArrayList<int[]> orderList) {
        if(cnt == b){
            orderList.add(candidate.clone());
            return;
        }
        for(int i=0; i<a; i++){
            candidate[cnt] = i;
            perm(cnt+1,a,b,candidate,orderList);
        }
    }
    public static int getMax(int[][] map){
        int max = -1;
        int N = map.length;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(max<map[i][j]) max = map[i][j];
            }
        }
        return max;
    }

    public static int[][] arrCopy(int[][] map) {
        int[][] copy = new int[map.length][];
        for(int i=0; i<map.length; i++){
            copy[i] = map[i].clone();
        }
        return copy;
    }
}
