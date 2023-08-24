package homework.M08.a0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_1759_암호만들기 {

    static int l,c;
    static int[] arr;
    static HashSet<Integer> set = new HashSet<>();

    static void permutation(int p, int cnt, int[] result, StringBuilder sb, int a, int b) {
        if(cnt==l) {
            if (a<1 || b<2) return;
            for (int r:result) sb.append((char) r);
            sb.append("\n");
        }
        else {
            for(int i=p+1;i<c;i++) {
                result[cnt] = arr[i];
                if (set.contains(arr[i])) {
                    permutation(i,cnt+1,result, sb, a+1, b);
                }
                else {
                    permutation(i,cnt+1,result, sb, a, b + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0821/res/input_1759.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[c];
        for (int i=0;i<c;i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();

        set.add((int)'a');set.add((int)'e');set.add((int)'i');set.add((int)'o');set.add((int)'u');

        permutation(-1,0,new int[l], sb,0,0);
        System.out.println(sb);
    }
}
