package week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2533
public class Solution_bj_2533_사회망_서비스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(solution(br, st));
        br.close();
    }

    public static int solution(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        Node[] tree = new Node[N+1];    // 링크드리스트 트리
        int[][] dp = new int[N+1][2];   // dp[i][0]: i가 얼리어답터가 아닐 때, dp[i][1]: i가 얼리어답터일 때
        boolean[] visited = new boolean[N+1];   // 방문 확인
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a] = new Node(b, tree[a]);
            tree[b] = new Node(a, tree[b]);
        }

        dfs(1, tree, dp, visited);

        return Math.min(dp[1][0], dp[1][1]);
    }

    public static void dfs(int start, Node[] tree, int[][] dp, boolean[] visited) {
        visited[start] = true;
        dp[start][0] = 0;   // start가 얼리어답터가 아닐 때
        dp[start][1] = 1;   // start가 얼리어답터일 때
        for(Node cur=tree[start]; cur!=null; cur=cur.next) {
            if(!visited[cur.n]) {
                dfs(cur.n, tree, dp, visited);
                dp[start][0] += dp[cur.n][1];   // start가 얼리어답터가 아니면, 주위에 얼리어답터가 있어야 함
                dp[start][1] += Math.min(dp[cur.n][0], dp[cur.n][1]);   // start가 얼리어답터면, 주위에 얼리어답터가 있어도 되고 없어도 됨
            }
        }

    }


    static class Node {
        int n;
        Node next;

        public Node(int n, Node next) {
            this.n = n;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append(n);
            sb.append(", ").append(next);
            sb.append('}');
            return sb.toString();
        }
    }














    /* 실패, 아래 코드는 관계의 방향이 일정해야 적용 가능 */
    public static int solution2(BufferedReader br, StringTokenizer st) throws Exception {
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] leftToRight = new ArrayList[N+1];   // 왼쪽에서 오른쪽으로 트리 저장
        List<Integer>[] rightToLeft = new ArrayList[N+1];    // 오른쪽에서 왼쪽으로 트리 저장
        Set<Integer> answer = new HashSet<>();   // 얼리 어답터를 저장할 Set
        Deque<Integer> q = new ArrayDeque<>();   // 탐색을 위한 큐
        for(int i=1; i<=N; i++) {
            leftToRight[i] = new ArrayList<>();
            rightToLeft[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            leftToRight[a].add(b);
            rightToLeft[b].add(a);
        }

        // 역방향 트리 찾기
        List<Integer>[] bottomUp = findLeafNode(N, leftToRight, rightToLeft, q);

        while(!q.isEmpty()) {
            for(int s=0; s<q.size(); s++) {
                int cur = q.poll();
                if(answer.contains(cur)) continue;  // 이미 방문했으면 패스
                if(bottomUp[cur].isEmpty())  continue; // cur이 루트노드면 패스
                int parent = bottomUp[cur].get(0);
                answer.add(parent);
                if(bottomUp[parent].isEmpty()) continue; // parent가 루트노드면 패스
                q.offer(bottomUp[parent].get(0));;

            }
        }
        System.out.println("answer: " + answer);
        return answer.size();
    }

    /**
     * 역방향 트리(자식 리스트에 부모 저장)를 찾고, 리프노드를 큐에 저장
     * @param N 노드의 개수
     * @param leftToRight 왼쪽에서 오른쪽으로 저장된 트리
     * @param rightToLeft 오른쪽에서 왼쪽으로 저장된 트리
     * @param q 리프노드를 저장할 큐
     * @return 역방향 트리
     */
    public static List<Integer>[] findLeafNode(int N, List<Integer>[] leftToRight, List<Integer>[] rightToLeft, Deque<Integer> q) {
        boolean flag = false;
        for(int i=1; i<=N; i++) {
            if(leftToRight[i].isEmpty()) {
                q.offer(i);
            }
            else if(leftToRight[i].size() > 1) {
                flag = true;
            }
        }
        if(flag) return rightToLeft;
        q.clear();
        for (int i=1; i<=N; i++) {
            if(rightToLeft[i].isEmpty()) {
                q.offer(i);
            }
        }
        return leftToRight;
    }
}

