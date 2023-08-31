package week5.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1339_단어수학 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_1339.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] chars = new char[n][];
        for (int i=0;i<n;i++) {
            chars[i] = br.readLine().toCharArray();
        }
        long[] weights = new long[26];

        for (int i = 0; i < n; i++) {
            int length = chars[i].length;
            for (int j = 0; j < length; j++) {
                weights[chars[i][j] - 'A'] += (long) Math.pow(10, length - j - 1);
            }
        }
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (weights[i] != 0) {
                list.add((char) ('A' + i));
            }
        }
        list.sort((o1, o2) -> Long.compare(weights[o2 - 'A'], weights[o1 - 'A']));
        int num = 9;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c:list) {
            hashMap.put(c, num--);
        }

        long res = 0;
        for (int i=0;i<n;i++) {
            char[] now = chars[i];
            long m = 0;
            for (char a:now) {
                m = m * 10 + hashMap.get(a);
            }
            res += m;
        }

        System.out.println(res);
    }
}