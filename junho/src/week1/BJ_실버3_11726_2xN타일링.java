package week1;

import java.io.*;

public class BJ_실버3_11726_2xN타일링 {
	static int[] tables;
    static final int MAX_L = 1000;
    static final int DIVISOR = 10_007;


    static void makeTable() {
        tables = new int[MAX_L + 1];
        tables[1] = 1;
        tables[2] = 2;
        for(int i = 3; i <= MAX_L; i++) {
            tables[i] = (tables[i-1] + tables[i-2]) % DIVISOR;
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream(""));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makeTable();

        int N = Integer.parseInt(br.readLine());
        System.out.println(tables[N]);
    }
}