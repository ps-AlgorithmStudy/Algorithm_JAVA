package homework.M08.a0803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_12891_DNA비밀번호 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/week3/input_bj_12891.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[] charArr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int ta = 0;
        int tc = 0;
        int tg = 0;
        int tt = 0;

        for (int i=0;i<m;i++) {
            switch (charArr[i]) {
                case ('A'):
                    ta++;break;
                case ('C'):
                    tc++;break;
                case ('G'):
                    tg++;break;
                case ('T'):
                    tt++;break;
            }
        }

        int cnt =0;
        for (int i=0;i<=n-m;i++) {
            if (i!=0) {
                switch (charArr[i-1]) {
                    case ('A'):
                        ta--;break;
                    case ('C'):
                        tc--;break;
                    case ('G'):
                        tg--;break;
                    case ('T'):
                        tt--;break;
                }
                switch (charArr[i+m-1]) {
                    case ('A'):
                        ta++;break;
                    case ('C'):
                        tc++;break;
                    case ('G'):
                        tg++;break;
                    case ('T'):
                        tt++;break;
                }
            }
            if (ta >= a && tc >= c && tg>=g&&tt>=t) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
