package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LCS {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/LCS.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] f = br.readLine().toCharArray();
        char[] s = br.readLine().toCharArray();
        int fl = f.length; int sl = s.length;
        int[][] lcs = new int[fl+1][sl+1];
        for (int i=1; i<= fl; i++) {
            for (int j=1;j<=sl; j++) {
                if (f[i-1] == s[j-1]) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }
                else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        System.out.println(lcs[fl][sl]);
    }
}
