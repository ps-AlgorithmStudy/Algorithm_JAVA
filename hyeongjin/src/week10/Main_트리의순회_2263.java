package week10;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
public class Main_트리의순회_2263 {

    static int[] inorder, postorder,idx;
    static StringBuilder sb = new StringBuilder();

    static void solve(int start_in, int end_in, int start_pre, int end_pre){
        if(end_in < start_in || end_pre < start_pre) return;
        int root = postorder[end_pre];
        int root_idx = idx[root];
        sb.append(root).append(" ");

        int next_length  = root_idx - start_in;
        solve(start_in , root_idx-1, start_pre, start_pre + start_pre -1);
        solve(root_idx + 1 , end_in, start_pre+next_length, end_pre-1);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        inorder   = new int[n+1]; // left -> root  -> right
        postorder = new int[n+1]; // left -> right -> root
        idx       = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        } // 맨 끝에 오는게 root

        idx = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            idx[inorder[i]] = i;
        }
        solve(1,n,1,n);
        System.out.println(sb.toString());
    }
}
