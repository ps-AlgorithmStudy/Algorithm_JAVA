package homework.a0814;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class bj_1074_Z {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] arr = {{0,1},{2,3}};
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());


        int n = 0;
        int p=0;
        int startNum = 0;
        while(true) {
            int max = Math.max(x, y);
            for (int i=1;i<=N;i++) {
                p = (int)Math.pow(2, i);
                if (max<p) {
                    n = i;
                    break;
                }
            }
            if(n==1) {
                System.out.println(startNum + arr[x][y]);
                break;
            }
            int tempNum = (int)Math.pow(2, (n-1)*2);
            p = (int)Math.pow(2, n-1);
            if (x >= p && y >= p) {
                startNum += tempNum * 3;
            }
            else if (x >=p && y < p) {
                startNum += tempNum * 2;
            }
            else {
                startNum += tempNum;
            }
            x = x % p;
            y = y % p;
        }
    }
}




