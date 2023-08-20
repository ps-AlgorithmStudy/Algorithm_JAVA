package greedy;

import java.util.*;
import java.io.*;

public class week5_bj_16953 {

    static int A, B;
    static boolean check;
    static int result = Integer.MAX_VALUE;

    static class Cal{
        long cal;
        int depth;

        Cal(long cal, int depth){
            this.cal = cal;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Cal{" +
                    "cal=" + cal +
                    ", depth=" + depth +
                    '}';
        }
    }
    static void bfs(){
        ArrayDeque<Cal> dq = new ArrayDeque<>();

        dq.offer(new Cal(A, 1));
        while (!dq.isEmpty()){
            Cal num = dq.poll();
//            System.out.println(num);
            if (num.cal * 2 < B) dq.offer(new Cal(num.cal * 2 , num.depth + 1));
            else if (num.cal * 2 == B) {
                result = (result < num.depth) ? result : num.depth;
                check = true; return;
            }
            if (num.cal * 10 + 1 < B) dq.offer(new Cal(num.cal * 10 + 1  , num.depth + 1));
            else if (num.cal * 10 + 1  == B){
                result = (result < num.depth) ? result : num.depth;
                check = true; return;
            }
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        bfs();

        if (check)
            System.out.println(result + 1);
        else
            System.out.println(-1);
    }
}
