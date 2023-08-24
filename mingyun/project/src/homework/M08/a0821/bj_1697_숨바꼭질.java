package homework.M08.a0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1697_숨바꼭질 {
    static class Obj {
        int pos, time;
        Obj(int a, int b){
            pos = a; time = b;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/M08/a0821/res/input_1697.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());

        ArrayDeque<Obj> deque = new ArrayDeque<>();

        boolean[] v = new boolean[2_0000_000];
        v[n] = true;
        deque.addLast(new Obj(n,0));
        while (!deque.isEmpty()) {
            Obj now = deque.removeFirst();
            if (now.pos == m) {
                System.out.println(now.time);
                break;
            }
            else {
                if ( !v[now.pos * 2]&&(now.pos * 2 - m == 1 || now.pos * 2 <= m)) {
                    v[now.pos * 2] = true;
                    deque.addLast(new Obj(now.pos*2, now.time+1));
                }
                if (!v[now.pos+1]) {
                    v[now.pos+1] = true;
                    deque.addLast(new Obj(now.pos +1, now.time+1));
                }
                if (now.pos -1 >=0 && !v[now.pos-1]) {
                    v[now.pos-1] = true;
                    deque.addLast(new Obj(now.pos -1, now.time+1));
                }
            }
        }
    }
}
