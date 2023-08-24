package homework.M08.a0809;

import java.io.*;
import java.util.*;

public class Solution_10580_전봇대 {
    static class Line {
        int start, end;
        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int TC=1; TC<=T; TC++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Line> lines = new ArrayList<>();

            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                lines.add(new Line(start, end));
            }

            int count = 0;
            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    Line l1 = lines.get(i);
                    Line l2 = lines.get(j);

                    if ((l1.start < l2.start && l1.end > l2.end) ||
                            (l1.start > l2.start && l1.end < l2.end)) {
                        count++;
                    }
                }
            }
            sb.append("#").append(TC).append(" ").append(count).append("\n");
        }

        System.out.println(sb);
    }
}