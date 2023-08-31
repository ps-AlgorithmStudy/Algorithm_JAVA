package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class bj_1339_단어_수학 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br));
        br.close();
    }
    public static int solution(BufferedReader br) throws Exception {
        int N = Integer.parseInt(br.readLine());
        String[] upper = new String[N];
        int ans = 0;
        for(int i=0; i<N; i++){
            upper[i] = br.readLine();
        }

        //알파벳의 개수, 자리수를 고려
        //개수*자리수
        int[][] count = new int[26][2];  // 알파벳 위치(c-'A'), 개수*자리수
        for(String up: upper){  // 알파벳의 개수 계산
            int digit = up.length();
            for(int i=0; i<digit; i++){
                int alphabet = up.charAt(i)-'A';
                count[alphabet][0] = alphabet;
                count[alphabet][1] += (int)Math.pow(10,digit-i-1);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o[1]));
        for(int[] cnt: count) {
            if(cnt[1] == 0) continue;
            pq.add(cnt);
        }
        int flag = 9;
        while(!pq.isEmpty()) {
            ans += flag--*pq.poll()[1];
        }

        return ans;
    }
//    public static void DoNotDoItThisWay2() {
//        // 문자열을 가장 긴 것부터, 알파벳 순으로 정렬
//        //Arrays.sort(upper, ((o1, o2) -> o1.length()==o2.length()?o1.compareTo(o2):Integer.compare(o2.length(),o1.length())));
//        //System.out.println(Arrays.toString(upper));
//        // 가장 긴 문자열 앞부터 9~ 부여
//        // 같은 자리 문자열은 번갈아가면서 숫자 부여
//        // 2
//        //AB
//        //BB 대응 불가능
//        int[] alphbet = new int[26];
//        int flag = 9;
//        ArrayList<Character>[] number = new ArrayList[upper[0].length()];
//        for(int i=0; i<number.length; i++) number[i] = new ArrayList<>();
//        for(String up: upper) {
//            int diff = number.length - up.length();
//            for(int i=diff; i<number.length; i++) {
//                char c = up.charAt(i-diff);
//                number[i].add(c);
//            }
//        }
//        for(int i=0; i<number.length; i++) {
//            ArrayList<Character> num = number[i];
//            for(char c: num) {
//                if(alphbet[c-'A'] == 0 && flag >=0) {
//                    alphbet[c-'A'] = flag;
//                    flag--;
//                }
//                ans += (int)(alphbet[c-'A']*Math.pow(10,number.length-i-1));
//            }
//        }
//
//    }
//    public static void DoNotDoItThisWay() {
//        // 2
//        //GCF
//        //ACDEB 대응 불가능
//        for(String up: upper){
//            char[] num = new char[up.length()];
//            for(int i=0; i<up.length(); i++){
//                char c = up.charAt(i);
//                if(alphbet[c-'A']==0) {
//                    alphbet[c-'A'] = (char)('0'+flag);
//                    flag--;
//                }
//                num[i] = alphbet[c-'A'];
//            }
//            ans += Integer.parseInt(new String(num));
//
//        }
//    }

}
