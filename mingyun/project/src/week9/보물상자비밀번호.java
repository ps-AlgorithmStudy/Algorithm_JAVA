package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 보물상자비밀번호 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/보물상자비밀번호.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int TC=1;TC<=T;TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ArrayDeque<Character> arrayDeque = new ArrayDeque<>();

            for (char c: br.readLine().toCharArray()) {
                arrayDeque.addLast(c);
            }
            HashSet<Integer> hashSet = new HashSet<>();

            for (int i=0;i<n;i++) {
                ArrayDeque<Character> temp = new ArrayDeque<>(arrayDeque);
                for (int d=0;d<4;d++) {
                    StringBuilder sb = new StringBuilder();
                    for (int c=0;c<n/4;c++) {
                        sb.append(temp.removeFirst());
                    }
                    hashSet.add(Integer.parseInt(sb.toString(), 16));
                }
                arrayDeque.addLast(arrayDeque.removeFirst());
            }
            ArrayList<Integer> arrayList = new ArrayList<>(hashSet);
            arrayList.sort(((o1, o2) -> -Integer.compare(o1, o2)));
            System.out.println("#" + TC + " " + arrayList.get(k-1));
        }

    }
}
