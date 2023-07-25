import java.util.*;
import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    
}

public class week1_bj_20542 {
    
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\Users\\User\\Documents\\dev\\java project\\algorithm-study-input\\bj_20542.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TestCase = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= TestCase; i++) {
            System.out.print("Case #" + i + ": ");
            week1_bj_20542.solution(br, st);
        }
    }

    public static void solution(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result = 0;
        String SY = br.readLine();
        String answer = br.readLine();


        System.out.println(sb.toString());
    }
}

