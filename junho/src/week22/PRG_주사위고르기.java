import java.util.*;

class PRG_주사위고르기 {
    
    HashMap<String, Integer> record;
    int[][] tempDice;
    
    
    public int[] solution(int[][] dice) {    
        // 일단 2 그룹으로 나눔
        final int N = dice.length;
        boolean[] v = new boolean[N];
        
        record = new HashMap<>();
        tempDice = dice;
        
        comb(N, N/2, v, 0, 0);
        
        // ---
        List<String> keys = new ArrayList<>(record.keySet());
        keys.sort((o1, o2) -> {
            return -1 * Integer.compare(record.get(o1), record.get(o2));
        }
        );
        
        // ---
        String answer = keys.get(0);
        System.out.println(answer);
        return Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    void comb(int n, int r, boolean[] v, int depth, int start) {
        // base case; 다 골랐다
        if(depth == r) {
            List<Integer> aSum = getSortedSum(v, true);
            List<Integer> bSum = getSortedSum(v, false);
            String key = getKey(v);
            int win = compare(aSum, bSum);
            record.put(key, win);
            return;
        }
                
        for(int i = start; i < n; i++) {
            v[i] = true;
            comb(n, r, v, depth + 1, i + 1);
            v[i] = false;
        }
        
    }
    
    List<Integer> getSortedSum(boolean[] v, boolean flag) {
        List<Integer> sumList = new ArrayList<>(100);
        recursive(sumList, v, flag, 0, 0);
        Collections.sort(sumList);
        return sumList;
    }
    
    void recursive(List<Integer> sumList, boolean[] v, boolean flag, int start, int sum) {
        if(start == v.length) {
            sumList.add(sum);
            return;
        }
        
        if(v[start] != flag) {
            recursive(sumList, v, flag, start + 1, sum);
            return;
        }
                
        for(int num : tempDice[start]) {
            recursive(sumList, v, flag, start + 1, sum + num);
        }
    }
    
    String getKey(boolean[] v) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < v.length; i++) {
            if(v[i])
                sb.append(i + 1).append(" ");
        }
        return sb.toString();
    }
    
    int compare(List<Integer> aSum, List<Integer> bSum) {
        int sum = 0;
        for(int key : aSum) {
            int count = Collections.binarySearch(bSum, key);
            if(count < 0) {
                count = -1 * count - 1;
            }
            sum += count;
        }
        return sum;
    }
    
    
}