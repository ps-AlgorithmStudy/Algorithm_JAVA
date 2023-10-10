package implementation;

import java.io.*;
import java.util.*;

public class week9_pg_2023kakao_이모티콘할인행사 {

    static int[][] user = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
    static int[] emoticons = {1300, 1500, 1600, 4900};

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] result = solution(user, emoticons);
        System.out.println(Arrays.toString(result));
    }

    static int[] b;
    static int userNum, maxPrice;
    static void perm(int cnt, int n, int[][] users, int[] emoticons){
        if (cnt == n){
            //cal
            cal(users, emoticons);
            return ;
        }
        for (int i = 1; i < 5; i++) {
            b[cnt] = i * 10;
            perm(cnt + 1, n, users, emoticons);
        }
    }

    static void cal(int[][] users, int[] emoticons){
        int user = 0, price = 0;
//        System.out.println(Arrays.toString(b));
//        System.out.println();
        for (int i = 0; i < users.length; i++) {
            double totalP = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] > b[j]) continue;
                double p = emoticons[j] * ((double) (100 - b[j]) / 100);
                totalP += p;
            }
//            System.out.println(totalP);
            if (totalP >= users[i][1]) user++;
            else price += (int) totalP;
        }
        if (user > userNum){
            userNum = user;
            maxPrice = price;
        }
        else if (user == userNum){
            maxPrice = Math.max(maxPrice, price);
        }
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        b = new int[emoticons.length];

        perm(0, emoticons.length, users, emoticons);

        answer[0] = userNum;
        answer[1] = maxPrice;
        return answer;
    }
}
