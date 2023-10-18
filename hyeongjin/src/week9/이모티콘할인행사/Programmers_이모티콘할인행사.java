package week9.이모티콘할인행사;
import java.util.*;
class Solution {
    int sale[] = {10,20,30,40};
    ArrayList<int[]> res;
    public int[] solution(int[][] users, int[] emoticons) {
        res = new ArrayList<>();
        solve(users,new int[users.length],emoticons,0);
        Collections.sort(res,(a,b)->b[0]-a[0] == 0 ? b[1]-a[1] : b[0]-a[0]);
        return res.get(0);
    }

    void solve(int[][] users,int[] saled, int[] emoticons, int idx) {
        if(idx == emoticons.length) {
            // 할인율이 다 적용 됐을 때
            // System.out.println(Arrays.toString(saled));
            int subscribe = 0;
            int purchaseTotal = 0;
            // 1. 각 유저에 대해 탐색
            for (int i = 0; i < users.length; i++) {
                int user_purchase = 0;
                // 2. 이모티콘과 할인율에 대해 팀섹
                for (int j = 0; j < emoticons.length; j++) {
                    // user[i][0]   : 할인율 한도 user[i][1] : 구매 한도
                    // saled[j] : 이모티콘의 할인율
                    // emoticons[j] : 이모티콘의 정가

                    // 유저가 바로 구매할 할인율을 비교
                    if(saled[j] >= users[i][0]) {
                        // 원하는 할인율일때 바로 구매하고 결제할 금액에 누적
                        user_purchase += emoticons[j] * (100-saled[j]) / 100;
                    }
                    // 누적 금액이 한도 이상일 경우에는 구독수 추가 및 유저에 대한 탐색 중단
                }
                // 결제 금액이 한도를 넘어가면 구독
                if(user_purchase >= users[i][1]) {
                    subscribe++;
                }else{
                    purchaseTotal += user_purchase;
                }
            }
            res.add(new int[]{subscribe,purchaseTotal});
            return;
        }
        for (int i = 0; i < 4; i++) {
            saled[idx]= sale[i];
            solve(users,saled,emoticons,idx+1);
        }
    }
}
public class Programmers_이모티콘할인행사 {
    public static void main(String[] args) {
            //
    }
}
