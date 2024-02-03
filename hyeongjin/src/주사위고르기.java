
import java.util.*;

class Solution {
    static int L;
    static boolean[] v;
    static List<Integer> DiceA;
    static List<Integer> DiceB;
    static List<boolean[]> diceComb;
    public int[] solution(int[][] dice) {
        int max = 0;
        int[] answer = {};
        L = dice.length;
        v = new boolean[L];
        diceComb = new ArrayList<>();
        perm(0,0);

        for (boolean[] comb : diceComb) {
            int idxA = 0;
            int idxB = 0;

            int[] combA = new int[L/2];
            int[] combB = new int[L/2];
            for(boolean tf : comb){
                if(tf)combA[idxA++]=idxA+idxB;
                else combB  [idxB++] =idxA+idxB;
            }

            int win = 0;

            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();

            getSum(0,0,combA,dice,sumA);
            getSum(0,0,combB,dice,sumB);

            Collections.sort(sumA);
            Collections.sort(sumB);

            int aIdx = 0;
            int bIdx = 0;

            for(int a : sumA){
                while(bIdx < sumB.size() && sumB.get(bIdx) < a) {
                    bIdx++;
                }
                win += bIdx;
            }

            if(win > max){
                max = win;
                answer = combA;
            }
        }

        return answer;
    }

    void perm (int depth, int idx){
        if (depth==L/2) {
            diceComb.add(v.clone());
            return;
        }

        for (int i = idx; i < L; i++) {
            if (!v[i]) {
                v[i] = true;
                perm(depth + 1, i + 1);
                v[i] = false;
            }
        }
    }
    void getSum(int idx, int sum, int[] comb, int[][] dice, List<Integer> sumList){
        if(idx==L/2){
            sumList.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            getSum(idx+1, sum+dice[comb[idx]-1][i], comb, dice, sumList);
        }
    }

}

public class 주사위고르기 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}})));
        System.out.println(Arrays.toString(s.solution(new int[][]{{1,2,3,4,5,6},{2,2,4,4,6,6,}})));
        System.out.println(Arrays.toString(s.solution(new int[][] {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}})));
    }
}
