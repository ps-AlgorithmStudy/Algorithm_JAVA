package week9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 찾기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week9/찾기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pt = br.readLine().toCharArray();

        int textLength = text.length, ptLength = pt.length;
        int[] pi = new int[ptLength];
        for (int i=1,j=0;i<ptLength; i++) {
            while (j>0 && pt[i] != pt[j]) j = pi[j-1];
            if (pt[i]==pt[j]) pi[i] = ++j;
            else pi[i] = 0;
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0,j=0; i<textLength; i++) {
            while (j>0 && text[i] != pt[j]) j = pi[j-1];
            if (text[i] == pt[j]) {
                if (j== ptLength-1) {
                    cnt++;
                    list.add(i-j);
                    j=pi[j];
                }
                else {
                    j++;
                }
            }
        }
        System.out.println(cnt);
        StringBuilder sb = new StringBuilder();
        if (cnt>0) {
            for (int a:list) {
                sb.append(a+1).append(" ");
            }
            System.out.println(sb);
        }
    }
}
