package implementation;

import java.io.*;
import java.util.*;

public class week2_bj_10994 {

    static int N;

    static StringBuilder sb = new StringBuilder();

    static void printStar(int n){
        String front = new String();
        String rear = new String();
        if (n == N)
        {
            sb.append("*");
            for (int i = 0; i < 2 * (n - 1); i++) {
                sb.append(" *");
            }
            sb.append("\n");
            return ;
        }

        int total = 1 + 4 * (N - 1);
        total -= 2 * (1 + (n - 1) * 2);

        for (int i = 0; i < n; i++) {
            if (i == 0)
            {
                front += "*";
                rear += "*";
            }
            else
            {
                front += " *";
                rear += " *";
            }

        }
        for (int i = 0; i < total; i++){
            front += "*";
            rear += " ";
        }
        for (int i = 0; i < n; i++) {
            if (i == n - 1)
            {
                front += "*";
                rear += "*";
            }
            else
            {
                front += "* ";
                rear += "* ";
            }
        }
        front += "\n";
        rear += "\n";

        //top
        sb.append(front);
        sb.append(rear);
        //middle
        printStar(n + 1);
        //bottom
        sb.append(rear);
        sb.append(front);

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        printStar(1);
        System.out.println(sb.toString());
    }
}

// string 을 재활용해서 sb.append 한것보다 for문을 2배로 더 돌렸을 때 시간이 더 짧은 이유?
