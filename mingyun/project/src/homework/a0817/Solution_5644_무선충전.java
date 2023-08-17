package homework.a0817;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5644_무선충전 {
    static int[][] mv = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};

    static boolean inRange(int i, int j) {
        return 0<=i && i<10 && 0<=j && j<10;
    }

    static void dfs(int i, int j, int c, Charger p, int pi, int pj, ArrayList<Charger>[][] map, boolean[][] v) {
        map[pi][pj].add(p);
        v[pi][pj] = true;
        for (int d=1;d<=4;d++) {
            int mi = pi + mv[d][0];
            int mj = pj + mv[d][1];
            if ((inRange(mi,mj) && Math.abs(i-mi) + Math.abs(j-mj) <= c) && !v[mi][mj]) dfs(i, j, c, p, mi, mj, map, v);
        }
    }

    static class Person {
        int i, j;
        Person(int a, int b) {
            i = a; j = b;
        }
    }

    static class Charger {
        int num, power;
        Charger(int a, int b) {
            num = a;
            power = b;
        }
    }

    public static int getSum(ArrayList<Charger> a, ArrayList<Charger> b) {
        int maxSum = 0;

        for (Charger chargerA : a) {
            for (Charger chargerB : b) {
                int currentSum;
                if (chargerA.num == chargerB.num) {
                    currentSum = chargerA.power;
                } else {
                    currentSum = chargerA.power + chargerB.power;
                }
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        if (a.isEmpty() && !b.isEmpty()) {
            maxSum = b.get(0).power;
        } else if (!a.isEmpty() && b.isEmpty()) {
            maxSum = a.get(0).power;
        }

        return maxSum;
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0817/res/input_5644.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int TC=1;TC<=T;TC++) {
            // 입력 시작
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken());
            int[] A = new int[m], B = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i=0;i<m;i++) A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<m;i++) B[i] = Integer.parseInt(st.nextToken());

            ArrayList<Charger>[][] map = new ArrayList[10][10];
            for (int i=0;i<10;i++) {
                for (int j=0;j<10;j++) map[i][j] = new ArrayList<>(2);
            }

            for (int ap=1;ap<=a;ap++) {
                st = new StringTokenizer(br.readLine());
                int j = Integer.parseInt(st.nextToken()) -1,
                    i = Integer.parseInt(st.nextToken()) -1,
                    c = Integer.parseInt(st.nextToken()),
                    p = Integer.parseInt(st.nextToken());
                dfs(i,j,c,new Charger(ap, p),i,j,map, new boolean[10][10]);
            }
            // 입력 끝

            for (int i=0;i<10;i++) {
                for (int j=0;j<10;j++) map[i][j].sort(((o1, o2) -> -Integer.compare(o1.power, o2.power)));
            }

            Person personA = new Person(0,0);
            Person personB = new Person(9,9);
            int sum = 0;

            for (int i=-1;i<m;i++) {
                if (i!=-1) {
                    personA.i += mv[A[i]][0]; personA.j += mv[A[i]][1];
                    personB.i += mv[B[i]][0]; personB.j += mv[B[i]][1];
                }
                sum += getSum(map[personA.i][personA.j],map[personB.i][personB.j]);
            }
            System.out.println("#"+ TC + " " + sum);
        }
    }
}
