import java.io.*;
import java.util.*;

public class ¼¾¼­_2212{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int sum= 0;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		HashSet<Integer> set = new HashSet<Integer>();
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) set.add(Integer.parseInt(st.nextToken()));
		
		Integer[] arr = set.toArray(new Integer[0]);
		Arrays.sort(arr);
		Integer[] distance = new Integer[arr.length-1];
		for (int i = 0; i < arr.length-1; i++) distance[i] = Math.abs(arr[i]-arr[i+1]);
		Arrays.sort(distance,Collections.reverseOrder());
		for (int i = K-1; i <distance.length; i++) sum += distance[i];
		System.out.println(sum);
	}
}
