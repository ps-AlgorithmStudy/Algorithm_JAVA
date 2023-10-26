import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class week10_bj_1013_36진수 {

    static int N, K;
    static int[][] input;
    static char[][] strs;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[52][36];

        BigInteger inputNum = BigInteger.ZERO;
        for (int i = 0; i < N; i++) {
            BigInteger tmp = new BigInteger(String.valueOf((Integer.parseInt(br.readLine(), 36))));
            inputNum = inputNum.add(tmp);

        }
        System.out.println(inputNum.toString(36));
    }
}
