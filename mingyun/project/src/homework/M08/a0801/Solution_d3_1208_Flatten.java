package homework.M08.a0801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d3_1208_Flatten {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_1208.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc=1;tc<=10;tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[100];
            for (int i=0;i<100;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i=0;i<=n;i++) {
                int max = 0;
                int min = Integer.MAX_VALUE;
                int maxPos = 0, minPos = 0;
                for (int j=0;j<100; j++) {
                    if (max < arr[j]) {
                        max = arr[j];
                        maxPos = j;
                    }
                    if (min > arr[j]) {
                        min = arr[j];
                        minPos = j;
                    }
                }
                if (i==n) sb.append("#").append(tc).append(" ").append(max-min).append("\n");
                arr[maxPos]--; arr[minPos]++;
            }
        }
        System.out.println(sb);
    }
}
