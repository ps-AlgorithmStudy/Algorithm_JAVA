package week11;

import java.util.*;
public class 사라지는발판 {
    int[] di = {0,1,0,-1};
    int[] dj = {1,0,-1,0};
    int n;

    boolean inRange(int i, int j) {
        return 0 <= i && i< n && 0 <= j && j < n;
    }

    class Trace {
        int winner, moveCount;
        Trace(int a, int b) {
            winner = a; moveCount = b;
        }
    }

    class Player {
        int i, j;
        Player(int a, int b) {
            i = a; j = b;
        }
    }

    void dfs(int[][] board, int moveCount, Player[] player, List<Trace> trace, int turn) {
        int[][] newBoard = new int[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        Player targetPlayer = player[turn];
        boolean flag = false;
        int nextTurn = (turn+1) % 2;
        for (int d=0;d<4;d++) {
            int mi = targetPlayer.i + di[d];
            int mj = targetPlayer.j + dj[d];
            if (inRange(mi, mj)) {

            }
        }
        if (!flag) trace.add(new Trace(nextTurn, moveCount));
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        n = board.length;
        dfs(board, 0, new Player[]{new Player(aloc[0],aloc[1]), new Player(bloc[0],bloc[1])}, new ArrayList<>(), 0);
        return answer;
    }
}


