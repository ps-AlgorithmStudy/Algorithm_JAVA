package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1091 {
    static int N;
    static int[] P,S,cards,cardsOrg;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N= Integer.parseInt(br.readLine());
        P= new int[N]; // 최종 결과
        S= new int[N]; // 섞는 방법
        cards = new int[N];

        st= new StringTokenizer(br.readLine());
        for (int i= 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        st= new StringTokenizer(br.readLine());
        for (int i= 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        for (int i= 0; i < N; i++) {
            cards[i] = i;
        }

        cardsOrg = cards.clone();

        int cnt = 0 ;

        while(!isSame()){
            if (cnt!=0 && Arrays.equals(cards,cardsOrg)) {
                cnt = -1;
                break;
            }
            cards = shuffle();
//            System.out.println(Arrays.toString(cards));
            cnt++;
        }

        System.out.println(cnt);
    }

    static int[] shuffle(){
        int[] res= new int[N];
        for (int i = 0; i < N; i++) {
            res[S[i]] = cards[i];
        }
        return res;
    }
    static boolean isSame(){
        for (int i = 0; i < N; i++) {
            int c= cards[i];
            if(P[c] != i%3){
                return false;
            }
        }return true;
    }
}
