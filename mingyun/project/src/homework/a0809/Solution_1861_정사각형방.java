package project.src.homework.a0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방 {

    static class Data {
        int i;
        int j;
        Data(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0809/res/input_d4_1861.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int TC=1;TC<=T;TC++) {
            HashMap<Integer, Data> hashMap = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            int [][] map = new int[n][n];
            for (int i=0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<n;j++) {
                    int m = Integer.parseInt(st.nextToken());
                    map[i][j] = m;
                    hashMap.put(m, new Data(i,j));
                }
            }

            int max = 0;
            int result = 0;
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    int key = map[i][j];
                    Data now = hashMap.get(key);
                    try {
                        while (true) {
                            Data next = hashMap.get(++key);
                            int distance = Math.abs(now.i-next.i) + Math.abs(now.j-next.j);
                            if (distance != 1) break;
                            now = next;
                        }
                    } catch (Exception ignored) {
                    }

                    int work = key - map[i][j];
                    if (work > max) {
                        max = work;
                        result = map[i][j];
                    }
                    else if (work == max) {
                        if (result > map[i][j]) result = map[i][j];
                    }
                }
            }
            sb.append("#").append(TC).append(" ").append(result).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
