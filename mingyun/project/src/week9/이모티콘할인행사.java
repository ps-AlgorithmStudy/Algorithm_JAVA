package week9;

import java.util.Arrays;

class Solution {
    int[] dl = {10,20,30,40};
    int el;
    int[] em;
    int[][] ur;
    int[] answer;

    void dfs(int c, int[] rate) {
        if (c == el) {
            int s=0, plus=0;
            for (int[] u:ur) {
                int sum = 0;
                for (int i=0;i<el;i++) {
                    if (rate[i] >= u[0]) {
                        sum += em[i] * (100-rate[i]) / 100;
                    }
                }
                if (sum >= u[1]) plus++;
                else s += sum;
            }
            if (plus > answer[0]) {
                answer[0] = plus;
                answer[1] = s;
            }
            else if (plus == answer[0]) {
                answer[1] = Math.max(answer[1], s);
            }
            return;
        }
        for (int d:dl) {
            rate[c] = d;
            dfs(c+1, rate);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        el = emoticons.length;
        em = emoticons;
        ur = users;
        dfs(0, new int[el]);
        return answer;
    }
}

public class 이모티콘할인행사 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [][] users = {{40,10000}, {25,10000}};
        int [] emoticons = {7000,9000};
        System.out.println(Arrays.toString(solution.solution(users, emoticons)));
    }
}