
import java.io.*;
import java.util.*;


public class week1_bj_2293 {
    static int n;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k + 1];
        for (int i=0;i<n;i++)
        {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp <= k)
                arr[tmp]++;
            for (int j = tmp + 1; j <= k; j++)
            {
                if (j - tmp > 0)
                    arr[j] += arr[j - tmp];
                else break;
            }
        }

        System.out.println(arr[k]);
        br.close();
    }

}
