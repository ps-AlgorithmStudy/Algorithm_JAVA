package implementation;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

public class week2_bj_20207 {
    static int[] arr = new int[365];
    static int N;
    static int max;
    static int min;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            //최적화를 위해 입력된 값의 min, max 값 저장
            if (i == 0)
            {
                min = n;
                max = m;
            } else {
                min = (min > n) ? n : min;
                max = (max > n) ? max : n;
            }
            // 해당 구간의 arr 값을 + 1 함.
            for (int j=n;j<=m;j++){
                arr[j - 1]++;
            }
        }
        //결과값 저장
        int result = 0;

        for (int i= min - 1 ; i < max ; i++){

            int row = 0, col = 0; // 코팅지의 row / col 의 값을 저장할 예정
            if (arr[i] != 0) { // 일정이 있는 날이면
                row = arr[i]; // 세로길이 초기화
                col = 0; // 가로길이 초기화
                while (arr[i] != 0){ // 일정이 연속되는 날까지 가로길이를 구함
                    col++;
                    row = (row > arr[i]) ? row : arr[i]; // 해당 구간중 제일 긴 세로길이를 구함
                    i++;
                    if (i == 365) // ArrayIndexOutOfBounds 처리
                        break;
                }
            }
            result += col * row;
        }
        System.out.println(result);
    }
}
