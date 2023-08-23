package homework.M08.a0807;

import java.io.*;
import java.util.*;

public class Solution_d3_1228_암호문 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_d3_1228.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;



        for (int T=1;T<=10;T++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<N;i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<M;i++) {
                st.nextToken();
                int p = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                List<Integer> temp = new ArrayList<>();
                for (int j=0;j<n;j++) {
                    temp.add(Integer.parseInt(st.nextToken()));
                }
                list.addAll(p,temp);
            }

            sb.append("#").append(T).append(" ");
            for (int i=0;i<10;i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
