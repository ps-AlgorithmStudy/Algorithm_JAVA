package homework.M08.a0808;

import java.io.*;
import java.util.*;

public class Solution_d4_1233_사칙연산유효성검사 {
    public static class Tree {
        private class Node {
            boolean isOperator;
            int childCount;
        }

        private int N;
        private Node[] tree;
        Tree(int N) {
            this.N = N;
            tree = new Node[N+1];
            for (int i=1;i<=N;i++) {
                tree[i] = new Node();
            }
        }

        void add(int idx, boolean isOperator, int count) {
            tree[idx].isOperator = isOperator;
            tree[idx].childCount += count;
        }

        int isValid() {
            for (int i=1;i<=N;i++) {
                if (tree[i].isOperator&&tree[i].childCount!=2) return 0;
                if (!tree[i].isOperator&&tree[i].childCount!=0) return 0;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("mingyun/project/res/input_d4_1233.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("+","-","/","*"));

        for (int T=1;T<=10;T++) {
            int N = Integer.parseInt(br.readLine());
            Tree tree = new Tree(N);
            for (int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int countTokens = st.countTokens();
                int idx = Integer.parseInt(st.nextToken());
                String operator = st.nextToken();
                if (hashSet.contains(operator)) {
                    tree.add(idx, true,countTokens-2);
                }
                else {
                    tree.add(idx, false,countTokens-2);
                }
            }
            sb.append("#").append(T).append(" ").append(tree.isValid()).append("\n");
        }

        System.out.println(sb);
    }
}
