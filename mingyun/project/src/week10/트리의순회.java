package week10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의순회 {
    static int n;
    static int[] inOrder;
    static int[] postOrder;
    static List<Integer> preOrder;
    static void getPreOrder(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {
        if (inOrderStart > inOrderEnd || postOrderStart > postOrderEnd) return;
        int rootNode = postOrder[postOrderEnd];
        preOrder.add(rootNode);
        int rootIndex = inOrderStart;
        for (int i=inOrderStart; i<= inOrderEnd; i++) {
            if (inOrder[i] == rootNode) {
                rootIndex = i; break;
            }
        }
        getPreOrder(inOrderStart, rootIndex-1, postOrderStart, postOrderStart + rootIndex - inOrderStart -1);
        getPreOrder(rootIndex+1, inOrderEnd, postOrderStart + rootIndex - inOrderStart, postOrderEnd-1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week10/트리의순회.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        inOrder = new int[n]; postOrder = new int[n]; preOrder = new ArrayList<>(n);

        for (int i=0;i<n;i++) inOrder[i] = sc.nextInt();
        for (int i=0;i<n;i++) postOrder[i] = sc.nextInt();
        getPreOrder(0,n-1,0, n-1);
        for (int a:preOrder) System.out.printf("%d ", a);
        System.out.println();
    }
}
