package week9;

import java.io.*;
import java.util.*;

public class ct_산타의_선물_공장_2 {
    static LinkedList<Present>[] factory;
    static HashMap<Integer, Present> bag;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.print(solution(br, st));
        br.close();
    }
    public static String solution(BufferedReader br, StringTokenizer st) throws Exception {
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for(int job=0; job<Q; job++) {
            st = new StringTokenizer(br.readLine());
            int task = Integer.parseInt(st.nextToken());
            int res = 0;
            switch (task){
                case 100:
                    int N = Integer.parseInt(st.nextToken());
                    int M = Integer.parseInt(st.nextToken());
                    factory = new LinkedList[N+1];
                    bag = new HashMap<>();
                    for(int i=1; i<=N; i++) factory[i] = new LinkedList<>();
                    for(int i=1; i<=M; i++) {
                        int idx = Integer.parseInt(st.nextToken());
                        Present p = new Present(idx,i);
                        factory[idx].add(p);
                        bag.put(i,p);
                    }

                    /*for test*/
//                    for(LinkedList<Present> f: factory) System.out.println(f);
//                    System.out.println();
                    /*for test*/

                    break;
                case 200:
                    res = movePresent(st, factory);
                    break;
                case 300:
                    res = replacePresent(st, factory);
                    break;
                case 400:
                    res = dividePresent(st, factory);
                    break;
                case 500:
                    res = infoPresent(st, factory,bag);
                    break;
                case 600:
                    res = infoBelt(st, factory);
                    break;
                default:
                    break;
            }
            if(task!=100) sb.append(res).append('\n');
            /*for test*/
            System.out.println(task+" "+res);
            /*for test*/
        }
        return sb.toString();
    }
    private static int movePresent(StringTokenizer st, LinkedList<Present>[] factory) {
        int m_src = Integer.parseInt(st.nextToken());
        int m_dst = Integer.parseInt(st.nextToken());
        for(Present p : factory[m_src]) {
            p.belt = m_dst;
        }
        factory[m_dst].addAll(0,factory[m_src]);
        factory[m_src].clear();
        /*for test*/
//        System.out.println(200+" "+m_src+" "+m_dst);
//        for(LinkedList<Present> f: factory) System.out.println(f);
//        System.out.println(factory[m_dst].size());
//        System.out.println();
        /*for test*/
        return factory[m_dst].size();
    }
    private static int replacePresent(StringTokenizer st, LinkedList<Present>[] factory) {
        int m_src = Integer.parseInt(st.nextToken());
        int m_dst = Integer.parseInt(st.nextToken());

        Present p_src = factory[m_src].poll();
        Present p_dst = factory[m_dst].poll();
        if(p_src != null) {
            p_src.belt = m_dst;
            factory[m_dst].addFirst(p_src);
        }
        if(p_dst != null) {
            p_dst.belt = m_src;
            factory[m_src].addFirst(p_dst);
        }

        /*for test*/
//        System.out.println(300+" "+m_src+" "+m_dst);
//        for(LinkedList<Present> f: factory) System.out.println(f);
//        System.out.println(factory[m_dst].size());
//        System.out.println();
        /*for test*/

        return factory[m_dst].size();
    }
    private static int dividePresent(StringTokenizer st, LinkedList<Present>[] factory) {
        int m_src = Integer.parseInt(st.nextToken());
        int m_dst = Integer.parseInt(st.nextToken());
        int half = factory[m_src].size()/2;
        if(half==0) return factory[m_dst].size();

        factory[m_dst].addAll(0,factory[m_src].subList(0,half));
        //factory[m_src].removeAll(factory[m_dst]);
        Iterator<Present> it = factory[m_src].iterator();
        while(half --> 0) {
            Present p = it.next();
            p.belt = m_dst;
            it.remove();

        }

        /*for test*/
//        System.out.println(400+" "+m_src+" "+m_dst);
//        for(LinkedList<Present> f: factory) System.out.println(f);
//        System.out.println(factory[m_dst].size());
//        System.out.println();
        /*for test*/

        return factory[m_dst].size();
    }
    private static int infoPresent(StringTokenizer st, LinkedList<Present>[] factory, HashMap<Integer, Present> bag) {
        int p_num = Integer.parseInt(st.nextToken());
        int b_num = bag.get(p_num).belt;
        int idx = factory[b_num].indexOf(bag.get(p_num));
        int aIdx = idx-1;
        int bIdx = idx+1;
        int a = 0;
        int b = 0;
        if(aIdx<0) a = -1;
        else a = factory[b_num].get(aIdx).num;
        if(bIdx>=factory[b_num].size()) b = -1;
        else b = factory[b_num].get(bIdx).num;

        /*for test*/
//        System.out.println(500+" "+p_num);
//        for(LinkedList<Present> f: factory) System.out.println(f);
//        System.out.println(a+2*b);
//        System.out.println();
        /*for test*/

        return a+2*b;
    }
    private static int infoBelt(StringTokenizer st, LinkedList<Present>[] factory) {
        int b_num = Integer.parseInt(st.nextToken());
        int a = 0;
        int b = 0;
        int c = 0;
        if(factory[b_num].isEmpty()) {
            a = -1;
            b = -1;
            c = 0;
        }
        else {
            a = factory[b_num].get(0).num;
            c = factory[b_num].size();
            b = factory[b_num].get(c-1).num;
        }

        /*for test*/
//        System.out.println(600+" "+b_num);
//        for(LinkedList<Present> f: factory) System.out.println(f);
//        System.out.println(a+2*b+3*c);
//        System.out.println();
        /*for test*/

        return a+2*b+3*c;
    }

    static class Present implements Comparable<Present>{
        int belt;
        int num;

        public Present(int belt, int num) {
            this.belt = belt;
            this.num = num;
        }

        @Override
        public int compareTo(Present o) {
            return Integer.compare(this.num,o.num);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("P(");
            sb.append(num).append(')');
            return sb.toString();
        }
    }
}
