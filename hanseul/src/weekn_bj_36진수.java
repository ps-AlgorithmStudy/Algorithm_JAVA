import java.io.*;
import java.util.*;

public class weekn_bj_36진수 {

    static int N, K;
    static int[] radix;
    static char[] chs;
    static char[] b;
    static String[] nums;
    static int[][] charMap;


    static TreeSet<Character> ts = new TreeSet<>();
    static void comb(int cnt, int start){

//        for (int j = 0; j < 51; j++) {
//            if (tmpMax[j] / 36 == 0) continue;
//            tmpMax[j + 1] += tmpMax[j] / 36;
//            tmpMax[j] %= 36;
//        }

//        for (int i = charMap.length - 1; i < charMap.length; i--) {
//            for (int j = 0; j < charMap[i].length; j++) {
//                if (charMap[i][j] != 0) ts.add(radix10(j));
//                if (ts.size() == K) break;
//            }
//            if (ts.size() == K) break;
//        }
        for (int i = 0; i < charMap[0].length; i++) {
            for (int j = 0; j < charMap.length; j++) {
                if (charMap[j][i] / 36 == 0) continue;
                charMap[j + 1][i] += charMap[j][i] / 36;
                charMap[j][i] %= 36;
            }
        }

        for (int i = charMap.length - 1; i >= 0; i--) {
            for (int j = 0; j < charMap[i].length; j++) {
                if (charMap[i][j] != 0) ts.add(radix10(j));
                if (ts.size() == K) break;
            }
            if (ts.size() == K) break;
        }

        int idx = 0;
        for(char ch : ts){
            b[idx++] = ch;
        }
        calcMax();
    }

    static boolean init = false;
    static int[] diffArr = new int[4];
    static void calcMax(){
        int[] tmpMax = new int[52];
        for(char ch1 : b){
            int idx = radix36(ch1);
            for (int i = 0; i < charMap.length; i++) {
                tmpMax[i] += charMap[i][idx] * (35 - idx);
            }
        }

        for (int j = 0; j < 51; j++) {
            if (tmpMax[j] / 36 == 0) continue;
            tmpMax[j + 1] += tmpMax[j] / 36;
            tmpMax[j] %= 36;
        }

        if (!init){
            diffArr = tmpMax;
            init = true;
        }
        else{
            for (int j = 51; j >= 0; j--) {
                if (diffArr[j] < tmpMax[j]){
                    diffArr = tmpMax;
                    return;
                }
                else if (diffArr[j] > tmpMax[j]) return;
            }
        }
    }


    static String plus36(){
//        arr = new int[52];

        for (int i = 0; i < N; i++) {
            char strs[] = nums[i].toCharArray();
            for (int j = 0, k = strs.length - 1; j < strs.length; j++, k--) {
                diffArr[j] += radix36(strs[k]);
            }
        }
        return radixStr();
    }

    static String radixStr(){
        String result = "";

        for (int j = 0; j < 51; j++) {
            if (diffArr[j] / 36 == 0) continue;
            diffArr[j + 1] += diffArr[j] / 36;
            diffArr[j] %= 36;
        }

        int idx = 51;
        while (idx >= 0 && diffArr[idx] == 0) idx--;

        for (int i = idx; i >= 0 ; i--) {
            if (0 <= diffArr[i] && diffArr[i] <= 9) result += (char)('0' + diffArr[i]);
            else result += ((char)('A' + diffArr[i] - 10));
        }

        return result;
    }

//    static boolean isZ(char ch){
//        for(char ch1 : b)
//            if (ch1 == ch) return true;
//        return false;
//    }
//    static boolean isZ(int n){
//        for(char ch1 : b)
//            if (ch1 == radix10(n)) return true;
//        return false;
//    }

    static int radix36(char ch){
        if ('0' <= ch && ch <= '9') return ch - '0';
        else return ch - 'A' + 10;
    }

    static char radix10(int n){
        if (0 <= n && n <= 9) return (char)(n + '0');
        else return (char)(n - 10 + 'A');
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        radix = new int[36];
        nums = new String[N];
        charMap = new int[52][36];

        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
            char strs[] = nums[i].toCharArray();
            for (int j = 0, k = strs.length - 1; j < strs.length; j++, k--) {
                int tmp = radix36(strs[j]);
                radix[tmp]++;
                charMap[k][tmp]++;
            }
        }

        K = Integer.parseInt(br.readLine());

        String strTmp = "";
        for (int i = 0; i < radix.length; i++) {
            if (radix[i] != 0)
                strTmp += radix10(i);
        }
        chs = strTmp.toCharArray();

        if (K > chs.length) K = chs.length;

        b = new char[K];
        comb(0,0);

        System.out.println(plus36());
    }
}
/*
1
ABCDE
2
 */