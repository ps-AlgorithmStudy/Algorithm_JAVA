package homework.M08.a0804;

import java.io.*;
import java.util.*;

public class Solution_d3_1225_암호생성기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_1225.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<=10;i++) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j=0;j<8;j++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }
            int work = deque.getFirst();
            int cnt = 1;
            while (work != 0) {
                work = deque.removeFirst() - cnt++;
                if (work < 0) {
                    work = 0;
                }
                if (cnt == 6) cnt = 1;
                deque.add(work);
            }
            sb.append("#").append(i).append(" ");
            for (int j=0;j<8;j++) {
                sb.append(deque.removeFirst()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
