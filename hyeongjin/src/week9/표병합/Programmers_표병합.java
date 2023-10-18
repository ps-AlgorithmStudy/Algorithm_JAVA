package week9.표병합;

import java.util.*;
class Solution {
    int[] parent = new int[2501]; // 50x50
    public String[] cell = new String[2501];
    // Union-find => 병합을 고려할 떄 사용
    public int find(int a){
        if(parent[a] ==a) return a;
        else return parent[a] = find(parent[a]);
    }
    // 좌표값을 cell index로 치환
    public int posToIdx(int i, int j){
        return 50 * (i-1) + j;
    }
    public void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
    public
    String[] solve(String[] commands) {
        StringTokenizer st;
        List<String> printList = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            cell[i] = "";
            parent[i] = i;
        }
        for (String cmd : commands) {
            st = new StringTokenizer(cmd);
            String keyword = st.nextToken();
            switch(keyword) {
                // 1. Update
                case "UPDATE":
                    // 1-2) UPDATE value1 value2 => value1을 value2로
                    if(st.countTokens()==2) {
                        String before = st.nextToken(); // 기존값
                        String after = st.nextToken();  // 변경값
                        // 표 탐색
                        for (int i = 1; i <=2500; i++) {
                            if(before.equals(cell[i])){
                                cell[i] = after;
                            }
                        }
                    }
                    // 1-3) UPDATE r c value => (r,c)를 value 로
                    else{
                        int    row = Integer.parseInt(st.nextToken());
                        int    col = Integer.parseInt(st.nextToken());
                        String val = st.nextToken();
                        cell[find(posToIdx(row, col))] = val;
                    }
                    break;

                // 2. Merge
                // MERGE r1 c1 r2 c2 => (r1,c1), (r2,c1)을 병합
                case "MERGE":
                    int row1 = Integer.parseInt(st.nextToken());
                    int col1 = Integer.parseInt(st.nextToken());
                    int row2 = Integer.parseInt(st.nextToken());
                    int col2 = Integer.parseInt(st.nextToken());

                    int idx1 = posToIdx(row1,col1);
                    int idx2 = posToIdx(row2,col2);

                    int root1 = find(idx1);
                    int root2 = find(idx2);

                    // 병합 되어있는 상태가 아니라면
                    if(root1 != root2) {
                        String mergeValue = "";
                        if(cell[root1].equals("")){
                            mergeValue = cell[root2];
                        }else{
                            mergeValue = cell[root1];
                        }
                        // 기존 셀들을 비워주고
                        cell[root1] = "";
                        cell[root2] = "";

                        // 저장한 값을 병합 후
                        union(root1,root2);

                        // root셀에 넣는다
                        cell[root1] = mergeValue;
                    }
                    break;

                // 3. UnMerge
                // UNMERGE r c => 해당 셀의 모든 병합 해제
                case "UNMERGE":
                    int row = Integer.parseInt(st.nextToken());
                    int col = Integer.parseInt(st.nextToken());

                    int idx  = posToIdx(row,col);
                    int root = find(idx);

                    // 병합 해제 후 나눠줄 값을 저장
                    String rootValue = cell[root];
                    cell[root] = "";
                    cell[idx]  = rootValue;
                    for (int i = 1; i <=2500 ; i++) {
                        if(find(i) == root){
                            parent[i] = i;
                        }
                    }
                    break;

                // 4. Print
                // 해당 셀의 내용 출력, 비어있을 경우 EMPTY 출력
                case "PRINT":
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    int index  = posToIdx(r,c);
                    int rootIdx = find(index);

                    if(cell[rootIdx].equals("")){
                        printList.add("EMPTY");
                    }else{
                        printList.add(cell[rootIdx]);
                    }
                    break;
            }
        }
        return printList.toArray(new String[0]);

    }
    public String[] solution(String[] commands) {
        String[] answer = {};
        return solve(commands);
    }
}
public class Programmers_표병합 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] commands = {
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",
                "MERGE 1 2 1 3",
                "MERGE 1 3 1 4",
                "UPDATE korean hansik",
                "UPDATE 1 3 group",
                "UNMERGE 1 4",
                "PRINT 1 3",
                "PRINT 1 4"
        };

        System.out.println(Arrays.toString(s.solution(commands)));
    }
}
