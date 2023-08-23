package homework.M08.a0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj_6808_규영이와인영이의카드게임 {
        static int[] gcard;
        static int[] icard;
        static boolean[] v;

        static int win;
        static int lose;

        public static void dfs(int cnt, int[] arr) {
            if (cnt==9) {
                int gy=0, in=0;
                for (int i=0;i<9;i++) {
                    if (gcard[i] > arr[i]) gy+= gcard[i] + arr[i] + 2;
                    else in+= gcard[i] + arr[i] + 2;
                }
                if (gy>in) win++;
                else if (gy<in) lose++;
            }
            else {
                for (int i=0;i<9;i++) {
                    if (!v[i]) {
                        arr[cnt] = icard[i];
                        v[i] = true;
                        dfs(cnt+1, arr);
                        v[i] = false;
                    }
                }
            }
        }

        public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0810/res/input_6808.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int TC=1;TC<=T;TC++) {
            st = new StringTokenizer(br.readLine());
            gcard = new int[9];
            icard = new int[9];
            v = new boolean[9];
            boolean[] mapping = new boolean[18];
            for (int i=0;i<9;i++) {
                int card = Integer.parseInt(st.nextToken())-1;
                gcard[i] = card;
                mapping[card] = true;
            }
            int p=0;
            for (int i=0;i<18;i++) if (!mapping[i]) icard[p++] = i;

            win = 0;
            lose = 0;
            dfs(0, new int[9]);
            System.out.println("#"+ TC + " "+win + " " + lose);
        }
    }
}
