package homework.M08.a0804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_d4_1218_괄호짝짓기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_d4_1218.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int T=1;T<=10;T++) {
            br.readLine();
            char[] arr = br.readLine().toCharArray();
            Deque<Integer> deque = new ArrayDeque<>();
            boolean flag = false;
            for (int c:arr) {
                if (c==')'|| c==']' || c=='}' || c=='>') {
                    if (deque.size()>0) {
                        int d = deque.removeLast();
                        if (!(d-c==-1||d-c==-2)) {
                            flag = true;
                            break;
                        }
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
                else {
                    deque.add(c);
                }
            }
            if (flag) {
                sb.append("#").append(T).append(" ").append("0").append("\n");
            }
            else {
                sb.append("#").append(T).append(" ").append("1").append("\n");
            }
        }
        System.out.println(sb);
    }
}
