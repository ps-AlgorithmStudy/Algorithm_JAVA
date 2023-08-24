package homework.M08.a0816;

import java.util.Scanner;

public class NQueen {
    static int N, ans=0;
    static int[] col;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        col = new int[N+1];
        setQueen(1);
        System.out.println(ans);
    }

    static void setQueen(int row) {
        if(!isAvailable(row-1)) return;
        if(row>N) {
            ans++;
            return;
        }
        for (int c=1;c<=N;c++) {
            col[row] = c;
            setQueen(row+1);
        }
    }

    static boolean isAvailable(int row) {
        for (int i=1;i<row;i++) {
            if(col[i]==col[row] || row-i == Math.abs(col[row]-col[i])) {
                return false;
            }
        }
        return true;
    }
}
