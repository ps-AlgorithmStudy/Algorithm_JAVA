import java.util.*;
import java.io.*;

public class week1_bj_20542 {

    static int[][] arr;
    static int n;
    static int m;
    static String nStr;
    static String mStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nStr = br.readLine();
        mStr = br.readLine();

        arr = new int[n + 1][m + 1];
        for (int i=1;i<=n;i++)
            arr[i][0] = i;
        for (int i=1;i<=m;i++)
            arr[0][i] = i;

        for (int i=1;i <= n; i++)
        {
            for (int j=1;j <= m;j++)
            {
                int min = (arr[i - 1][j - 1] > arr[i - 1][j]) ? arr[i - 1][j] : arr[i - 1][j - 1];
                min = (min > arr[i][j - 1]) ? arr[i][j - 1] : min;

                boolean bool;
                char ch = nStr.charAt(i - 1);
                char ch1 = mStr.charAt(j - 1);
//                if (nStr.charAt(i - 1))

                if (arr[i][0] == arr[0][j]) arr[i][j] = min;
                else arr[i][j] = min + 1;
            }
        }
        System.out.println(arr[n][m]);
    }
}
