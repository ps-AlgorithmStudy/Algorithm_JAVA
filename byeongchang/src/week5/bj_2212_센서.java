package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_2212_센서 {
    public static void main(String[] args) throws Exception {
        //https://www.acmicpc.net/problem/2212
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }
    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        HashSet<Integer> sensorSet = new HashSet<>();
        int minRange = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sensorSet.add(Integer.parseInt(st.nextToken()));
        }
        if(K>=N) return 0;
        Integer[] sensor = sensorSet.toArray(new Integer[0]);
        Arrays.sort(sensor);
        int[] diff = new int[sensor.length-1];
        for(int i=0; i<diff.length; i++){
            diff[i] = sensor[i+1]-sensor[i];
        }
        Arrays.sort(diff);
        int answer = 0;
        for(int i=0; i<diff.length-K+1; i++){
            answer += diff[i];
        }
        return answer;
    }
}
