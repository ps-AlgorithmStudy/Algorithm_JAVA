package project.src.week3;

import java.io.*;
import java.util.*;

public class bj_2481_해밍경로 {
    public static int convertToNumber(String binaryString) {
        int num = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                num += 1 << (binaryString.length() - 1 - i);
            }
        }
        return num;
    }

    public static Deque<Integer> backTrack(int target, int[] v) {
        Deque<Integer> result = new ArrayDeque<>();
        if (v[target]==0) {
            result.addLast(-1);
            return result;
        }
        result.addLast(target);
        while (v[target]!= -1) {
            target = v[target];
            result.addLast(target);
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_2481.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] v = new int[n+1];
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i=1;i<=n;i++) {
            int temp = convertToNumber(br.readLine());
            hashMap.put(temp,i);
            if (i==1) deque.addLast(temp);
        }

        v[1] = -1;

        while (!deque.isEmpty()) {
            int target = deque.removeFirst();
            int targetIndex = hashMap.get(target);
            for (int i=0;i<k;i++) {
                int nextCode = target ^ (1<<i);
                if (hashMap.containsKey(nextCode)) {
                    int nextIndex = hashMap.get(nextCode);
                    if (v[nextIndex] == 0) {
                        deque.addLast(nextCode);
                        v[nextIndex] = targetIndex;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i=0;i<m;i++) {
            int target = Integer.parseInt(br.readLine());
            Deque<Integer> track = backTrack(target,v);
            while (!track.isEmpty()) {
                sb.append(track.removeLast()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
