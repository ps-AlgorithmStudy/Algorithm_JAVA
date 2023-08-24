package homework.B형특강;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 수열편집 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/B형특강/res/수열편집.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), l = Integer.parseInt(st.nextToken());

            LinkedList<Integer> linkedList = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n;i++) {
                linkedList.add(Integer.parseInt(st.nextToken()));
            }

            for (int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                char order = st.nextToken().charAt(0);
                switch (order) {
                    case 'I':
                        linkedList.add(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
                        break;
                    case 'D':
                        linkedList.remove(Integer.parseInt(st.nextToken()));
                        break;
                    case 'C':
                        linkedList.set(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                        break;
                }
            }
            try {
                linkedList.get(l);
                sb.append("#").append(TC).append(" ").append(linkedList.get(l)).append("\n");
            }
            catch (Exception ignore){
                sb.append("#").append(TC).append(" ").append(-1).append("\n");
            }
        }
        System.out.println(sb);
    }
}
