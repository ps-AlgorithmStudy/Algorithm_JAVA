package week19;

import java.util.ArrayList;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/150367
// 실패
public class pg_150367_표현_가능한_이진트리 {
    public int[] solution(long[] numbers) {
        ArrayList<Integer> answer = new ArrayList<>();
        for(long num: numbers) {
            char[] bin = Long.toBinaryString(num).toCharArray();
            int sqrt = (int) Math.sqrt(bin.length) + 1;
            char[] tree = new char[(int) Math.pow(2, sqrt) - 1];
            Arrays.fill(tree, '0');
            for(int i=0; i<bin.length; i++) {
                tree[tree.length - bin.length + i] = bin[i];
            }

            int rootIdx = tree.length / 2;
            int level = (int) Math.sqrt(tree.length);
            if(isBinaryTree(rootIdx, level, tree, true)) answer.add(1);
            else answer.add(0);
        }

        return answer.parallelStream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isBinaryTree(int rootIdx, int level, char[] bin, boolean res) {
        if (level == 0) return true;    // 리프 노드면 자식이 없음
        if (!res) return false;    // 이진 트리가 아니면 더 이상 탐색할 필요 없음
        int sub = (int) Math.pow(2, level) / 2;  // 루트와 서브트리의 루트 사이의 노드 개수
        char root = bin[rootIdx];
        int leftSubRootIdx = rootIdx - sub;
        int rightSubRootIdx = rootIdx + sub;
        if (leftSubRootIdx >= 0) {   // 왼쪽 서브트리가 존재하면
            if( root == '0' && bin[leftSubRootIdx] == '1') return false; // root가 0이면서 왼쪽 서브트리의 루트가 1이면 이진 트리가 아님
            res &= isBinaryTree(leftSubRootIdx, level - 1, bin, res);
        }
        if(!res) return false;
        if (rightSubRootIdx < bin.length) { // 오른쪽 서브트리가 존재하면
            if( root == '0' && bin[rightSubRootIdx] == '1') return false; // root가 0이면서 오른쪽 서브트리의 루트가 1이면 이진 트리가 아님
            res &= isBinaryTree(rightSubRootIdx, level - 1, bin, res);
        }
        return res;
    }




    public static void main(String[] args) {
        TestCase[] tc = new TestCase[3];
        tc[0] = new TestCase(new long[]{95}, new int[]{0});
        tc[1] = new TestCase(new long[]{7, 42, 5}, new int[]{1, 1, 0});
        tc[2] = new TestCase(new long[]{63, 111, 95}, new int[]{1, 1, 0});

        pg_150367_표현_가능한_이진트리 sol = new pg_150367_표현_가능한_이진트리();
        for(TestCase t: tc) {
            int[] answer = sol.solution(t.numbers);
            System.out.println(Arrays.equals(answer, t.result) + " sol : " + Arrays.toString(answer) + " ans : " + Arrays.toString(t.result));
        }

    }

    static class TestCase {
        long[] numbers;
        int[] result;

        public TestCase(long[] numbers, int[] result) {
            this.numbers = numbers;
            this.result = result;
        }
    }
}
