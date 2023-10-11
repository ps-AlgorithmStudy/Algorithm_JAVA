package implementation;

import java.io.*;
import java.util.*;

public class week9_bj_16637_괄호추가하기 {

    static Integer num[];
    static Character oper[];
    static int[] b;
    static int N;
    static int max = Integer.MIN_VALUE;

    static void comb(int cnt, int start){
        // cal
        max = Math.max(max, cal(cnt));

        if (cnt == N / 2) return;

        for (int i = start; i < N / 2; i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 2);
        }

    }

    static int cal(int cnt){
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(num));
        ArrayList<Character> opers = new ArrayList<>(Arrays.asList(oper));

        for (int i = cnt - 1; i >= 0; i--) {
            calIndex(b[i], nums, opers);
        }
        while(!opers.isEmpty()){
            calIndex(0, nums, opers);
        }

        return nums.get(0);
    }

    static void calIndex(int i, ArrayList<Integer> nums, ArrayList<Character> opers){
        int tmp = 0;
        int t1 = nums.remove(i);
        int t2 = nums.remove(i);
        char operator = opers.remove(i);

        if (operator == '+') tmp = t1 + t2;
        else if (operator == '-') tmp = t1 - t2;
        else if (operator == '*') tmp = t1 * t2;
        nums.add(i, tmp);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        b = new int[N / 2];
        num = new Integer[N / 2 + 1];
        oper  = new Character[N / 2];

        int j = 0, k = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) num[j++] = str.charAt(i) - '0';
            else oper[k++] = str.charAt(i);
        }

        comb(0, 0);
        System.out.println(max);
    }
}
