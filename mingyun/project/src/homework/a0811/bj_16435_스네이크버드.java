package project.src.homework.a0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class bj_16435_스네이크버드 {
    /*
    9 1
    9 5 8 1 3 2 7 6 4
     */
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0811/res/input_16435.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int c = 0;
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i]<m) c++;
        }
        Arrays.sort(arr);
        int length = c + m;
        for (int i=c;i<n;i++) {
            if (arr[i]<=length) length++;
        }
        System.out.println(length);
    }
}
