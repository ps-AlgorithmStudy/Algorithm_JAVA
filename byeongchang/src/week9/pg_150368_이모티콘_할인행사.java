package week9;

import java.util.ArrayList;
import java.util.Arrays;

public class pg_150368_이모티콘_할인행사 {

    public static void main(String[] args) throws Exception {
        TestCase[] T = new TestCase[2];
        T[0] = new TestCase(new int[][]{{40, 10000}, {25, 10000}},
                new int[]{7000, 9000},
                new int[]{1, 5400});
        T[1] = new TestCase(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}},
                new int[]{1300, 1500, 1600, 4900},
                new int[]{4, 13860});
        pg_150368_이모티콘_할인행사 s = new pg_150368_이모티콘_할인행사();
        for (TestCase tc : T) {
            System.out.println("sol : " + Arrays.toString(s.solution(tc.users, tc.emoticons)));
            System.out.println("ans : " + Arrays.toString(tc.result));
        }

    }

    static class TestCase {
        int[][] users;
        int[] emoticons;
        int[] result;

        public TestCase(int[][] users, int[] emoticons, int[] result) {
            this.users = users;
            this.emoticons = emoticons;
            this.result = result;
        }
    }

    // 여기부터 프로그래머스에 제출, import java.util.*; 빼먹으면 안됨
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];  // 가입자 수, 판매액
        int plus = 0;
        int sale = 0;
        int[] discounts = {10,20,30,40};
        ArrayList<int[]> list = new ArrayList<>(16384);  // 4^7 = 16384
        perm(0,4,emoticons.length,new int[emoticons.length], list);  // 중복순열
        for(int[] disIdx : list) {
            int[] dis = selectDiscount(disIdx, discounts);  // 이모티콘 별 할인율 배열
            plus = 0;
            sale = 0;
            for(int[] user: users) {
                int price = buy(user[0],dis,emoticons);  // 할인율 배열 적용해서 구매 금액 계산
                if(user[1]<=price) plus++;  // 구매금액이 크면 이모티콘 플러스
                else sale += price;  // 아니면 구매
            }
            if(answer[0]<plus) {  // 기존보다 플러스 가입 인원이 늘어났으면 갱신
                answer[0] = plus;
                answer[1] = sale;
            } else if (answer[0]==plus && answer[1]<=sale) {  // 플러스 인원은 같은데 구매금액이 늘어났으면 금액만 갱신
                answer[1] = sale;
            }

        }

        return answer;
    }

    public int buy(int want, int[] dis, int[] emoticons){
        int res = 0;
        for(int i=0; i<dis.length; i++) {
            if(want>dis[i]) continue;
            res += emoticons[i]*(100-dis[i])/100;
        }
        return res;
    }

    public int[] selectDiscount(int[] disIdx, int[] discounts){
        int[] dis = new int[disIdx.length];
        for(int i=0; i<disIdx.length; i++) {
            dis[i] = discounts[disIdx[i]];
        }
        return dis;
    }
    public void perm(int cnt, int n, int l, int[] candidate, ArrayList<int[]> list) {
        if(cnt == l) {
            list.add(candidate.clone());
            return;
        }
        for(int i=0; i<n; i++) {
            candidate[cnt]=i;
            perm(cnt+1,n,l,candidate,list);
        }
    }
}
