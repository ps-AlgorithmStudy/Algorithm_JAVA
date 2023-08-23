package homework.M08.a0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_15686_치킨배달 {
    static class Pointer {
        int i,j;
        Pointer(int a, int b) {
            i = a;
            j = b;
        }
    }

    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }

    static boolean nPm(int[] arr) {
        int n = arr.length;
        int i = n-1;
        while (i>0&&arr[i-1]>=arr[i]) i--;

        if (i==0) return false;
        int j = n-1;
        while (arr[i-1]>=arr[j]) j--;

        swap(arr,i-1,j);
        int k = n-1;
        while(i<k) {
            swap(arr, i++, k--);
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0811/res/input_15686.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Pointer> home = new ArrayList<>(100);
        ArrayList<Pointer> chicken = new ArrayList<>(100);

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x==1) home.add(new Pointer(i,j));
                else if (x==2) chicken.add(new Pointer(i,j));
            }
        }

        int[] mask = new int[chicken.size()];
        for (int i=0;i<m;i++) {
            mask[chicken.size()-i-1] = 1;
        }

        int result = Integer.MAX_VALUE;
        do {
            ArrayList<Pointer> picked = new ArrayList<>(100);
            for (int i=0;i<chicken.size();i++) if (mask[i]==1) picked.add(chicken.get(i));
            int sum = 0;
            for (Pointer h:home) {
                int distance = Integer.MAX_VALUE;
                for (Pointer p:picked) {
                    int t = Math.abs(p.i-h.i) + Math.abs(p.j-h.j);
                    distance = Math.min(distance, t);
                }
                sum+= distance;
            }
            result = Math.min(result, sum);
        } while (nPm(mask));
        System.out.println(result);
    }
}
