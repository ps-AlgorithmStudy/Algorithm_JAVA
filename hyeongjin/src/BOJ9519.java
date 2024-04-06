import java.io.*;
import java.util.*;

public class BOJ9519 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder head = new StringBuilder();
        StringBuilder tail = new StringBuilder();

        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        list.add(word);

        int length = word.length();
        int cycle = 0;
        int loop = X;

        String cur = word;
        while (loop>0) {
            head.setLength(0);
            tail.setLength(0);
            if(length%2 == 0) { // 짝
                for(int i=length-1; i>=0; i-=2) {
                    tail.append(cur.charAt(i));
                }
                for(int i=0; i<length; i+=2) {
                    head.append(cur.charAt(i));
                }
            }else { // 홀
                for(int i=length-2; i>=0; i-=2) {
                    tail.append(cur.charAt(i));
                }
                for(int i=0; i<length; i+=2) {
                    head.append(cur.charAt(i));
                }
            }
            head.append(tail);

            cur = head.toString();
            list.add(cur);

            if(map.get(cur) == null) {
                map.put(cur, cycle++);
            }else {
                break;
            }
        }

        if(loop>0)System.out.println(list.get(X%cycle));
        else System.out.println(cur);
    }
}
