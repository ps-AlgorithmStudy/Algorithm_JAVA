package homework.M08.a0824;

import java.io.*;
import java.util.*;

public class bj_15961_회전초밥 {

    static class Work {
        ArrayDeque<Integer> susi;
        ArrayDeque<Integer> belt;
        int[] counter;
        int cnt = 0;
        int N,d,k,c;
        Work() throws Exception {
            System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0824/res/15961.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());

            susi = new ArrayDeque<>(N);
            belt = new ArrayDeque<>(k);
            counter = new int[d+1];
            for (int i = 0; i < N; i++) {
                susi.addLast(Integer.parseInt(br.readLine()));
            }

            for (int i = 0; i < k; i++) {
                int susiOut = susi.removeFirst();
                if (counter[susiOut]==0) cnt++;
                counter[susiOut]++;
                belt.add(susiOut);
            }

        }

        int run() {
            int max=cnt;
            for (int i = 0; i < N; i++) {
                int beltOut = belt.removeFirst();
                int susiOut = susi.removeFirst();

                if (counter[beltOut]==1) cnt--;
                counter[beltOut]--;
                if (counter[susiOut]==0) cnt++;
                counter[susiOut]++;

                belt.addLast(susiOut);
                susi.addLast(beltOut);
                int tempCnt = cnt;
                if (counter[c]==0) tempCnt++;
                max = Math.max(max, tempCnt);
            }
            return max;
        }
    }
    public static void main(String[] args) throws Exception {
        Work work = new Work();
        System.out.println(work.run());
    }
}