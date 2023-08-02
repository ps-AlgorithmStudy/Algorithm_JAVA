package week2;

import java.util.*;
import java.io.*;

public class q15787 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean [][] trains = new boolean[N+1][21];
        int res = 1;

        // 명령 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int trainNumber = Integer.parseInt(st.nextToken());
            int seat = 0;
            if(cmd==1 || cmd==2) {seat = Integer.parseInt(st.nextToken());}

            switch(cmd) {
                case 1: // 해당 번호 기차, 해당 좌석 -> 탑승
                    trains[trainNumber][seat] = true;
                    break;
                case 2: // 해당 번호 기차, 해당 좌석 -> 하차
                    trains[trainNumber][seat] = false;
                    break;
                case 3: // 한칸씩 뒤로 , 맨뒤에 사람있으면 하차
                    for (int j = 20; j >= 2; j--) trains[trainNumber][j]=trains[trainNumber][j-1];
                    trains[trainNumber][1]=false;
                    break;
                case 4: // 한칸씩 앞으로, 맨앞에 사람있으면 하차
                    for (int j = 1; j < 20; j++) trains[trainNumber][j]= trains[trainNumber][j+1];
                    break;
                default:
                    break;
            }
        }

        // Hashset
        // 1. 순서x  2. 중복x  3.null값 허용
        Set<String> trainSet = new HashSet<String>();
        for (int i = 1; i <= N ; i++) {
            sb = new StringBuilder();
            for (int j = 1; j <= 20 ; j++) {
                if(trains[i][j]) sb.append("1");
                else sb.append("0");
            }
            trainSet.add(sb.toString());
        }
        System.out.println(trainSet.size());

    }
}
