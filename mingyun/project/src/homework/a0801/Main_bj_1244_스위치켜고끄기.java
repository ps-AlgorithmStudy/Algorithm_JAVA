package homework.a0801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_1244_스위치켜고끄기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_1244.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                for (int j = val; j <= n; j += val) {
                    arr[j] = (arr[j] == 0 ? 1 : 0);
                }
            } else if (gender == 2) {
                arr[val] = (arr[val] == 0 ? 1 : 0);
                int left = val - 1;
                int right = val + 1;

                while (left >= 1 && right <= n && arr[left] == arr[right]) {
                    arr[left] = (arr[left] == 0 ? 1 : 0);
                    arr[right] = (arr[right] == 0 ? 1 : 0);
                    left--;
                    right++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(arr[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
