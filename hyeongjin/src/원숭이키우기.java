import java.util.*;
import java.io.*;
public class 원숭이키우기 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 0;

        int[] level = new int[N+1];
        int[] power = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            result += level[i] * power[i];
        }
        int D = Integer.parseInt(br.readLine());

        int dp[] = new int[N+1];



        for (int i = 1; i<=N; i++) {
            while(level[i] > 0){
                for (int j = D; j >= 0; j--) {
                    for (int k = i+1; k <= N && k+j-i<=D; k++) {
                        if(level[k]>0) {
                            dp[k + j - i] = Math.max(dp[k + j - i], dp[j] + power[k] - power[i]);
                        }
                    }
                }
                level[i] --;
            }
        }
        System.out.println(dp[D] + result);


    }
}
