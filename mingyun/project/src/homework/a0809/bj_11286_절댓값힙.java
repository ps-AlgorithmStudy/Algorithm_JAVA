package project.src.homework.a0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj_11286_절댓값힙 {
    static class Heap implements Comparable<Heap> {
        int abs;
        int realData;

        Heap(int abs, int realData) {
            this.abs = abs;
            this.realData = realData;
        }

        @Override
        public int compareTo(Heap o) {
            return this.abs == o.abs ?Integer.compare(this.realData, o.realData):Integer.compare(this.abs, o.abs);
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0809/res/input_11725.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Heap> heaps = new PriorityQueue<>();
        for (int i=0;i<n;i++) {
            int m = Integer.parseInt(br.readLine());
            if (m==0) {
                if (!heaps.isEmpty()) sb.append(heaps.remove().realData).append("\n");
                else sb.append("0\n");
            }
            else heaps.add(new Heap(Math.abs(m),m));
        }
        System.out.print(sb);
    }
}
