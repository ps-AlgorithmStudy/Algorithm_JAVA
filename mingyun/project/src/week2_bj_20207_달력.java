import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week2_bj_20207_달력 {

    public void solution() throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_20207.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] arr = new int[367];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            for (int j=left;j<=right;j++) {
                arr[j]++;
            }
        }
        int sum = 0;
        int cnt = 1;
        int max = 0;
        for (int i=1;i<=366;i++) {
            if (max == 0) {
                if (arr[i] !=0) {
                    max = arr[i];
                }
            }
            else {
                if (arr[i] == 0) {
                    sum += max * cnt;
                    max = 0;
                    cnt = 1;
                } else {
                    cnt++;
                    max = Math.max(max, arr[i]);
                }
            }
        }
        System.out.println(sum);
    }
    public static void main(String[] args) throws Exception {
        new week2_bj_20207_달력().solution();
    }
}
