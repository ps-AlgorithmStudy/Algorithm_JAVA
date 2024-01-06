package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class week17_bj_9252_LCS2 {
    static int[][] arr;
    static int n;
    static int m;
    static String nStr;
    static String mStr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nStr = br.readLine();
        mStr = br.readLine();
        n = nStr.length();
        m = mStr.length();

        arr = new int[n + 1][m + 1];

        for (int i=1;i <= n; i++) {
            for (int j=1;j <= m;j++) {
                int min = (arr[i - 1][j - 1] > arr[i - 1][j]) ? arr[i - 1][j] : arr[i - 1][j - 1];
                min = (min > arr[i][j - 1]) ? arr[i][j - 1] : min;
                char ch = nStr.charAt(i - 1);
                char ch1 = mStr.charAt(j - 1);

                if (ch == ch1) arr[i][j] = arr[i - 1][j - 1];
                else arr[i][j] = min + 1;
            }
        }

        System.out.println(arr[n][m]);
    }
}
