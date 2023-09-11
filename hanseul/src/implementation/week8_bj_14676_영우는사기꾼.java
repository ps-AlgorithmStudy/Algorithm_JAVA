package implementation;

import java.io.*;
import java.util.*;
public class week8_bj_14676_영우는사기꾼 {

    static int N, M, K;
    static ArrayList<Integer>[] build;
    static int[] curbuildings;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        boolean status = false;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        build = new ArrayList[N];
        curbuildings = new int[N];
        for (int i = 0; i < N; i++) {
            build[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            build[to].add(from);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int mode = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken()) - 1;

            if (status) break;
            if (mode == 1){
                for(int condition: build[building]){
                    if (curbuildings[condition] == 0){
                        status = true;
                        break;
                    }
                }
                if (status) continue;
                curbuildings[building]++;
            }
            else {
                if (curbuildings[building] == 0)
                    status = true;
                else
                    curbuildings[building]--;
            }
        }

        if (status)
            System.out.println("Lier!");
        else
            System.out.println("King-God-Emperor");
    }
}
/*

4 4 3
1 2
1 3
2 4
3 4
1 1
1 3
1 4



3 2 4
1 2
2 3
1 1
1 2
2 1
1 2


4 4 6
1 2
1 3
2 4
3 4
1 1
1 2
1 3
1 3
2 2
1 4

4 4 4
1 2
1 3
2 4
3 4
1 1
1 2
1 2
1 4


7 6 6
1 3
2 3
3 4
4 5
4 6
4 7
1 1
1 2
1 3
2 1
2 1
1 4
 */
