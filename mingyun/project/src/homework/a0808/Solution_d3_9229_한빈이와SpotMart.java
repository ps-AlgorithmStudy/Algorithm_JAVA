package project.src.homework.a0808;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Solution_d3_9229_한빈이와SpotMart {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_9229.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int T=1;T<=TC;T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int max = -1;
            for (int i=0; i<n;i++) {
                for (int j=i+1;j<n;j++) {
                    int sum = arr[i] + arr[j];
                    if (sum <= m) max = Math.max(max,sum);
                }
            }
            sb.append("#").append(T).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
