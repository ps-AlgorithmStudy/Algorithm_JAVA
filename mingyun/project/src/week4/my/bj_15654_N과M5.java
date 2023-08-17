package project.src.week4.my;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_15654_N과M5 {

    static int n;
    static int m;
    static int[] arrData;
    static HashSet<String> hashSet = new HashSet<>();
    static boolean[] v;

    public static void dfs(int cnt, int[] arr, StringBuilder sb) {
        if (cnt == m) {
            StringBuilder tempString = new StringBuilder();
            for (int a:arr) tempString.append(a).append(" ");
            if (!hashSet.contains(tempString.toString())) {
                hashSet.add(tempString.toString());
                sb.append(tempString).append("\n");
            }
        }
        else {
            for (int i=0;i<n;i++) {
                if (cnt==0||arr[cnt-1] <= arrData[i]) {
                    arr[cnt] = arrData[i];
                    dfs(cnt+1, arr,sb);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week4/res/input_bj_15654.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arrData = new int[n];
        v = new boolean[n];
        for (int i=0;i<n;i++) {
            arrData[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrData);
        dfs(0, new int[m], sb);
        System.out.println(sb);
    }

}
