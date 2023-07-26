import org.omg.CORBA.WStringValueHelper;

import java.io.*;
import java.util.*;

public class week1_bj_1092_배 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/res/input_bj_1092.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        int[] crane = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i=0;i<n;i++) {
            crane[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(crane);

        int m = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer> struct = new ArrayList<>();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i=0;i<m;i++) {
            struct.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        Collections.sort(struct);

        int res = 0;
        int move = 0;
        do {
            move = 0;
            res++;
            for (int i=struct.size()-1;i>=0;i--) {
                if (move == n) break;
                if (struct.get(i) <= crane[n-move-1]) {
                    move++;
                    struct.remove(i);
                }
            }
            if (struct.size() == 0) break;
        } while (move!=0);

        if(struct.size()!=0) System.out.println(-1);
        else System.out.println(res);
    }
}
