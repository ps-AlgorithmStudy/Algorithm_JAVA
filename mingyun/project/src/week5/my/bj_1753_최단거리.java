package week5.my;

import java.io.*;
import java.util.*;

public class bj_1753_최단거리 {

    static class Node {
        int to, val;
        Node(int a, int b) {
            to = a; val = b;
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week5/res/input_bj_1753.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

    }
}