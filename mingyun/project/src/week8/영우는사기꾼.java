package week8;

import java.io.*;
import java.util.*;

public class 영우는사기꾼 {
    static int N, M, K;
    static List<Integer>[] Condition;
    static int[] Build;
    static int[] Build_Able;
    static boolean answer = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Condition = new ArrayList[N + 1];
        Build = new int[N + 1];
        Build_Able = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Condition[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            Condition[X].add(Y);
            Build_Able[Y]++;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int Cmd = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            if (Cmd == 1) {
                if (Build_Able[A] > 0) {
                    answer = false;
                    break;
                } else {
                    Build[A]++;
                    if (Build[A] == 1) {
                        for (int j : Condition[A]) {
                            Build_Able[j]--;
                        }
                    }
                }
            } else if (Cmd == 2) {
                if (Build[A] == 0) {
                    answer = false;
                    break;
                } else {
                    Build[A]--;
                    if (Build[A] == 0) {
                        for (int j : Condition[A]) {
                            Build_Able[j]++;
                        }
                    }
                }
            }
        }
        System.out.println(answer ? "King-God-Emperor" : "Lier!");
    }
}
