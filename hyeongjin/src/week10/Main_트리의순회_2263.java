package week10;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
public class Main_트리의순회_2263 {

    static int[] inorder, postorder;
    static StringBuilder sb = new StringBuilder();

    static void solve(int start_i, int end_i, int start_p, int end_p){
        if(end_i < start_i || end_p < start_p) return;
        int root = postorder[end_p];
        int root_idx = 0;
        for (int i = start_i; i <= end_i; i++) {
            if(inorder[i] == root){
                root_idx = i;
            }
        }
        sb.append(inorder[root_idx]).append(" ");

        int next_length = root_idx - start_i;
        solve(start_i , root_idx-1, start_p, start_p + next_length -1);
        solve(root_idx + 1 , end_i, start_p+next_length, end_p-1);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        inorder   = new int[100001]; // left -> root  -> right
        postorder = new int[100001]; // left -> right -> root
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        } // 맨 끝에 오는게 root

        solve(0,n-1,0,n-1);
        System.out.println(sb.toString());
    }
}
