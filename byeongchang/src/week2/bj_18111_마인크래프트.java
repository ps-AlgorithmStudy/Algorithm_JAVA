package week2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_18111_마인크래프트 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/bj_18111.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        solution(br, st);
    }

    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 세로
        int M = Integer.parseInt(st.nextToken());  // 가로
        int B = Integer.parseInt(st.nextToken());  // 인벤토리에 있는 블록 수
        int[] map = new int[257];
        int max = 0;
        int min = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int height = Integer.parseInt(st.nextToken());
                map[height]++;
                max = max > height ? max : height;
                min = min < height ? min : height;
            }
        }
        int minTime = Integer.MAX_VALUE;
        int maxHeight = 0;
        for(int i=min;i<=max;i++){
            int time = 0;
            int inventory = B;
            for(int j=0;j<i;j++){
                time += (i-j)*map[j];
                inventory -= (i-j)*map[j];
            }
            for(int j=i+1;j<=256;j++){
                time += (j-i)*map[j]*2;
                inventory += (j-i)*map[j];
            }
            if(inventory >= 0 && time <= minTime){
                minTime = time;
                maxHeight = i;
            }
        }
        System.out.println(minTime + " " + maxHeight);

    }
}
