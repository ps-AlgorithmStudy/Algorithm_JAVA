package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week4_bj_9334 {
    static int K;
    static int arr[];
    static StringBuilder[] sb;
    static void getDepth(int front, int rear, int depth) {
        if (depth == K)
            return;
        int mid = (front + rear) / 2;
        sb[depth].append(arr[mid]).append(" ");

        getDepth(front, mid - 1, depth + 1); //left child
        getDepth(mid + 1, rear, depth + 1); // right child
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        arr = new int[(int) Math.pow(2, K) - 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        sb = new StringBuilder[K];
        for (int i = 0; i < K; i++)
            sb[i] = new StringBuilder();

        getDepth(0, arr.length - 1, 0);

        for (int i = 0; i < K; i++)
            System.out.println(sb[i].toString());

    }


}
