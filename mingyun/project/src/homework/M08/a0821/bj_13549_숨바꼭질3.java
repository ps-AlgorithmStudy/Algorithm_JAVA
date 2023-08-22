package homework.M08.a0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_13549_숨바꼭질3 {
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

        int[] v = new int[1_000_000];
        int[] pv = new int[1_000_000];
        Arrays.fill(v,1_000_000);
        v[n] = 0;
        deque.addLast(new Obj(n,0));
        int r = 1_000_000;
        while (!deque.isEmpty()) {
            Obj now = deque.removeFirst();
            if (now.pos == m) {
                r = Math.min(r, now.time);
            }
            else {
                if (now.pos * 2 <= 100000 && v[now.pos * 2] >= now.time &&(now.pos * 2 - m == 1 || now.pos * 2 <= m)) {
                    if (pv[now.pos * 2] <= 10) {
                        pv[now.pos * 2]++;
                        v[now.pos * 2] = now.time;
                        deque.addLast(new Obj(now.pos*2, now.time));
                    }
                }
                if (now.pos +1 <= 100000 && v[now.pos+1] > now.time) {
                    v[now.pos+1] = now.time;
                    deque.addLast(new Obj(now.pos +1, now.time+1));
                }
                if (now.pos -1 <= 100000 && now.pos -1 >=0 && v[now.pos-1] > now.time) {
                    v[now.pos-1] = now.time;
                    deque.addLast(new Obj(now.pos -1, now.time+1));
                }
            }
        }
        System.out.println(r);
    }
}
