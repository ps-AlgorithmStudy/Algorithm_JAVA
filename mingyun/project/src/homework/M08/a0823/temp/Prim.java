package homework.M08.a0823.temp;

import java.io.*;
import java.util.*;

public class Prim {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);  // 입력을 받기 위한 Scanner 객체 생성
        int N = sc.nextInt();  // 정점(vertex)의 개수를 입력 받음

        // 그래프의 인접 행렬 표현. g[i][j]는 i에서 j로 가는 간선의 가중치를 저장
        int[][] g = new int[N][N];
        boolean[] v = new boolean[N];  // v[i]는 정점 i가 방문되었는지의 여부
        int[] minEdge = new int[N];  // minEdge[i]는 정점 i로의 최소 간선의 가중치

        // 그래프 정보와 minEdge 배열 초기화
        for(int i=0;i<N;i++) {
            for (int j =0;j<N;j++) {
                g[i][j] = sc.nextInt();  // 간선의 가중치 입력
            }
            minEdge[i] = Integer.MAX_VALUE;  // 초기 최소 간선 가중치는 매우 큰 값으로 설정
        }

        int result = 0;  // 최소 신장 트리의 가중치 합계
        int cnt =0;      // 방문한 정점의 수

        // 시작 정점으로부터의 거리를 0으로 설정
        minEdge[0] = 0;

        // 모든 정점을 순회
        for (int i = 0; i < N; i++) {
            int minVertex = -1;  // 최소 간선 가중치를 가진 정점의 인덱스
            int min = Integer.MAX_VALUE;  // 최소 간선 가중치 값

            // 방문하지 않은 정점 중에서 최소 간선 가중치를 가진 정점 찾기
            for(int j = 0 ; j < N ; j ++) {
                if (!v[j] && min > minEdge[j]) {
                    minVertex = j;
                    min = minEdge[j];
                }
            }

            // 찾은 정점을 방문 처리
            v[minVertex] = true;
            result += min;  // 결과에 최소 간선 가중치를 더함
            if(cnt++==N-1) break;  // 모든 정점을 방문했다면 반복문 종료

            // 현재 선택한 정점(minVertex)과 연결된 다른 정점들의 최소 간선 가중치 업데이트
            for(int j=0;j<N;j++) {
                if(!v[j] && g[minVertex][j]!=0 && minEdge[j] > g[minVertex][j]) {
                    minEdge[j] = g[minVertex][j];
                }
            }
        }

        System.out.println(result);  // 최소 신장 트리의 가중치 출력
        sc.close();  // Scanner 객체 종료
    }
}