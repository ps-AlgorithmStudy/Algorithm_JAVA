package week12;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main_텀프로젝트 {
    static int[] next;
    static boolean[] v;
    static boolean[] result;
    static void dfs(int cur, List<Integer> list){
        v[cur] = true; // 방문처리하고
        list.add(cur); // 리스트에 담는다
        int nxt = next[cur]; // cur : 현재학생 ,nxt :다음 학생
        if(v[nxt]) { // 다음 학생이 이미 거쳐갔던 학생이라면
        	 // 1. 이번에 방문했던 학생이라면
            if (list.contains(nxt)) {
                int start = list.indexOf(nxt);      // 루프에서 해당 학생부터
                int end = list.size();              // 해당 학생 전까지
                for (int i = start; i < end; i++) { // 결과 boolean배열에 방문처리를 해준다
                    result[list.get(i)] = true;
                }
            }
            // 2. 이전에 방문했던 학생이라면 바로 종료
            return;
        }
        dfs(nxt,list);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            result = new boolean[N+1];
            next = new int[N+1];
            v = new boolean[N+1];
            int count = 0;
            ArrayList<Integer> list;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                next[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if(!v[i]) { // 방문한적 없으면
                    list = new ArrayList<>();
                    dfs(i,list);
                }
            }
            for (int i = 1; i <= N; i++) {
                if(!result[i]){
//                    System.out.println(i);
                    count ++;
                }
            }
            System.out.println(count);
        }
    }
}