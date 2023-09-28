package dp;
import java.io.*;
import java.util.*;

public class week8_bj_9251_LCS {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp;
        int max = 0;

        dp = new int[str2.length() + 1][str1.length()];
        Arrays.fill(dp[0],0);

        for (int i = 1; i < str2.length() - max; i++) {

        }



    }

}
