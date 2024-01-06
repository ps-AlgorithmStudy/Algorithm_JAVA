package week19;

import java.io.IOException;
import java.util.Arrays;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        StringBuilder sb;
        for (int i = 0; i < numbers.length; i++) {
            sb= new StringBuilder();
            long num = numbers[i];

            // 주어진 수를 이진수로
            String numToBinary = Long.toBinaryString(num);
            int height = (int) Math.ceil(Math.log10(numToBinary.length() + 1) / Math.log10(2));

            // 포화이진트리의 노드 수
            int treeSize = (int)Math.pow(2,height)-1;

            for (int j = 0; j < treeSize-numToBinary.length(); j++) {
                sb.append('0');
            }
            sb.append(numToBinary);
            answer[i] = isRepresentable(sb.toString()) ? 1:0;
        }
        return answer;
    }

    boolean isRepresentable(String tree){

        // 무사히 리프노드까지 도달
        if(tree.length() <=1) return true;

        int mid = tree.length()/2;

        // 왼쪽 서브트리
        String left = tree.substring(0,mid);
        // 오른쪽 서브트리
        String right = tree.substring(mid+1);

        char leftChild = left.charAt(left.length()/2);
        char rightChild = right.charAt(right.length()/2);

        // 자식 둘중 하나가 1이고, 현재 트리의 노드가 0일때
        if(tree.charAt(mid) == '0' && (leftChild=='1' || rightChild=='1')) return false;
        // 좌 우 서브트리중 하나라도 유효하지 않은 경우
        else return isRepresentable(left) && isRepresentable(right);
    }

}
public class Main_표현가능한이진트리 {
    public static void main(String[] args)throws IOException {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new long[]{7,42,5,63,111,95})));
    }
}
