import java.io.*;
import java.util.*;

public class week1_bj_11053 {

    static int N;
    static int arr[];
    static int dp[];
    static int max;
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "");

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        dp = new int[N];

        for (int i=0;i<N;i++){
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;
        }
        for (int i= 0; i < N ;i++)
        {
            dp[i] = 1;
            max = arr[i];
            result = 1;
            for (int j = i; j< N; j++)
            {
                if (dp[i - 1] < dp[i] && max < dp[i])
                {
                    max = dp[i];
                    result++;
                    dp[i] = (result + 1 > dp[i]) ? result + 1 : dp[i];
                }
            }
        }
        System.out.println(result);
    }
}

//10 20 0 10 20 30 40 50


