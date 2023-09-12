package implementation;

import java.io.*;
import java.util.*;

public class week8_bj_1629_곱셈 {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < B; i++) {
            A = (A * A) % C;
        }
        System.out.println(A);
    }

}
