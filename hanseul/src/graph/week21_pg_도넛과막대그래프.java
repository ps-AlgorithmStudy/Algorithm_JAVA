package graph;

public class week21_pg_도넛과막대그래프 {
    class Solution {

        int[][] degrees;
        int max =  Integer.MIN_VALUE;
        public int[] solution(int[][] edges) {

            degrees = new int[2][1_000_001];
            for (int[] edge : edges){
                degrees[0][edge[0]]++;
                degrees[1][edge[1]]++;
                if (edge[0] > max) max = edge[0];
                if (edge[1] > max) max = edge[1];
            }

            int[] answer = new int[4];

            for (int i = 1; i <= max ; i++){
                if (degrees[0][i] > 1 && degrees[1][i] == 0) // 생성점
                    answer[0] = i;
                if (degrees[0][i] == 0) // 막대그래프
                    answer[2]++;
                if (degrees[0][i] == 2 && degrees[1][i] >= 2) // 8자 그래프
                    answer[3]++;
            }

            answer[1] = degrees[0][answer[0]] - (answer[2] + answer[3]);

            return answer;
        }
    }
/*
찾아야하는 것
1. 생성점 : out 만 있음 out의 개수가 (2)+(3)+(4) 의 개수
2. 막대그래프 : 끝의 개수를 구하면 됨
3. 도넛그래프 : R
4. 8자 그래프 : 2 in 2 out 을 구하면 됨.
*/
}
