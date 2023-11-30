package week14;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/3089
public class Solution_bj_3089_네잎_클로버를_찾아서 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution2(br, st));
        br.close();

    }

    public static String solution2(BufferedReader br, StringTokenizer st) throws Exception {
        final int BIAS = 99999;
        int posX = BIAS;
        int posY = BIAS;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        TreeSet<Integer>[] cloverX = new TreeSet[199999];
        TreeSet<Integer>[] cloverY = new TreeSet[199999];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + BIAS;
            int y = Integer.parseInt(st.nextToken()) + BIAS;
            if (cloverX[x] == null) {
                cloverX[x] = new TreeSet<>();
            }
            if (cloverY[y] == null) {
                cloverY[y] = new TreeSet<>();
            }
            cloverX[x].add(y);
            cloverY[y].add(x);

        }

        char[] orders = br.readLine().toCharArray();
        for (char order : orders) {
            switch (order) {
                case 'U':
                    posY = cloverX[posX].higher(posY);
                    break;
                case 'D':
                    posY = cloverX[posX].lower(posY);
                    break;
                case 'L':
                    posX = cloverY[posY].lower(posX);
                    break;
                case 'R':
                    posX = cloverY[posY].higher(posX);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(posX - BIAS).append(" ").append(posY - BIAS);
        return sb.toString();
    }

    public static String solution(BufferedReader br, StringTokenizer st) throws Exception {
        int[] pos = { 0, 0 };
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        HashMap<Integer, TreeSet<Integer>> cloverX = new HashMap<>(); // x 좌표를 기준으로 y 좌표값을 저장할 해시맵
        HashMap<Integer, TreeSet<Integer>> cloverY = new HashMap<>(); // y 좌표를 기준으로 x 좌표값을 저장할 해시맵
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            makeMap(x, y, cloverX, cloverY);
        }
        char[] orders = br.readLine().toCharArray();
        for (char order : orders) {
            switch (order) {
                case 'U':
                    pos[1] = cloverX.get(pos[0]).higher(pos[1]); // 현재 위치의 y좌표값의 다음 값
                    break;
                case 'D':
                    pos[1] = cloverX.get(pos[0]).lower(pos[1]); // 현재 위치의 y좌표값의 이전 값
                    break;
                case 'L':
                    pos[0] = cloverY.get(pos[1]).lower(pos[0]); // 현재 위치의 x좌표값의 이전 값
                    break;
                case 'R':
                    pos[0] = cloverY.get(pos[1]).higher(pos[0]); // 현재 위치의 x좌표값의 다음 값
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pos[0]).append(" ").append(pos[1]);
        return sb.toString();

    }

    public static void makeMap(int x, int y, HashMap<Integer, TreeSet<Integer>> cloverX,
            HashMap<Integer, TreeSet<Integer>> cloverY) {
        if (cloverX.containsKey(x)) {
            cloverX.get(x).add(y);
        } else {
            TreeSet<Integer> temp = new TreeSet<>();
            temp.add(y);
            cloverX.put(x, temp);
        }
        if (cloverY.containsKey(y)) {
            cloverY.get(y).add(x);
        } else {
            TreeSet<Integer> temp = new TreeSet<>();
            temp.add(x);
            cloverY.put(y, temp);
        }
    }
}
