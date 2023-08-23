package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_1339_단어_수학 {
    //https://www.acmicpc.net/problem/1339
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
        Arrays.sort(upper, ((o1, o2) -> o1.length()==o2.length()?o1.compareTo(o2):Integer.compare(o2.length(),o1.length())));
        //System.out.println(Arrays.toString(upper));
        // 가장 긴 문자열 앞부터 9~ 부여
        // 같은 자리 문자열은 번갈아가면서 숫자 부여
        char[] alphbet = new char[26];
        int flag = 9;
        int[] number = new int[upper[0].length()];
        for(int i=0; i<N; i++){

        }
        return ans;
    }
//    public static void DoNotDoItThisWay() {
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
