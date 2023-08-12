package project.src.homework.a0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1931_회의실배정 {
    static class Data {
        int start;
        int end;
        Data(int a, int b) {
            start = a;
            end = b;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/homework/a0811/res/input_1931.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        Data[] data = new Data[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i] = new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(data, (o1, o2) -> o1.end== o2.end?Integer.compare(o1.start, o2.start):Integer.compare(o1.end, o2.end));

        int end = data[0].end;
        int res = 1;
        for (int i=1;i<n;i++) {
            if (end <= data[i].start) {
                end = data[i].end;
                res++;
            }
        }
        System.out.println(res);
    }
}
