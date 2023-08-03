package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_20207_달력 {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res\\bj_20207.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        solution(br, st);
    }
    public static void solution(BufferedReader br, StringTokenizer st) throws Exception{
        int N = Integer.parseInt(br.readLine());
        int[] calendar = new int[366];
        int max = 0;   // end의 최대값
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for(int j=start; j<=end; j++){  // start~end까지 1을 더해 특정 일자에 일정이 몇 개 있는지 계산
                calendar[j]++;
            }
            max = max >= end ? max : end;
        }
        int width = 0;
        int height = 0;
        int area = 0;
        for(int i=1; i<=max; i++){  // 일정 끝나는 날까지
            if(calendar[i]==0){  // 일정이 없으면 이전 일정의 최대 폭,너비로 계산
                area += width*height;
                width = 0;
                height = 0;
            }else{
                width++;
                height = height >= calendar[i] ? height : calendar[i];
            }
        }
        area += width*height;
        System.out.println(area);
    }
}
