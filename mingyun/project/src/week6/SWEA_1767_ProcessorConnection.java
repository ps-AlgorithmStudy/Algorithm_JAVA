package project.src.week6;

import java.util.Scanner;

class Processor {
    int x, y;
    Processor(int x, int y) { this.x = x; this.y = y; }
}

public class SWEA_1767_ProcessorConnection {
    private static int testCases, gridSize, processorCount, minLength;
    private static int grid[][], directionsX[] = {-1, 1, 0, 0}, directionsY[] = {0, 0, -1, 1};
    private static Processor processors[];
    private static boolean selected[];

    public static void generateCombinations(int idx, int cnt, int R) {
        if(cnt == R) {
            depthFirstSearch(0, 0);
            return;
        }
        for(int i = idx; i < processorCount; i++) {
            selected[i] = true;
            generateCombinations(i + 1, cnt + 1, R);
            selected[i] = false;
        }
    }

    public static void depthFirstSearch(int idx, int cnt) {
        if(idx == processorCount) {
            minLength = Math.min(minLength, cnt);
            return;
        }
        if(!selected[idx]) {
            depthFirstSearch(idx + 1, cnt);
            return;
        }
        for(int i = 0; i < 4; i++) {
            int x = processors[idx].x, y = processors[idx].y, tmp = 0;
            boolean success = false;
            while(true) {
                x += directionsX[i]; y += directionsY[i];
                if(x < 0 || x >= gridSize || y < 0 || y >= gridSize) {
                    success = true;
                    break;
                }
                if(grid[x][y] != 0) break;
                grid[x][y] = 2;
                tmp++;
            }
            if(success) depthFirstSearch(idx + 1, cnt + tmp);
            while(true) {
                x -= directionsX[i]; y -= directionsY[i];
                if(x == processors[idx].x && y == processors[idx].y) break;
                grid[x][y] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        testCases = scanner.nextInt();
        for(int t = 1; t <= testCases; t++) {
            gridSize = scanner.nextInt();
            grid = new int[gridSize][gridSize]; processors = new Processor[12]; selected = new boolean[12];
            processorCount = 0; minLength = Integer.MAX_VALUE;
            for(int i = 0; i < gridSize; i++) for(int j = 0; j < gridSize; j++) grid[i][j] = scanner.nextInt();
            for(int i = 1; i < gridSize - 1; i++) for(int j = 1; j < gridSize - 1; j++) if(grid[i][j] == 1) processors[processorCount++] = new Processor(i, j);

            for(int i = processorCount; i >= 0; i--) {
                generateCombinations(0, 0, i);
                if(minLength < Integer.MAX_VALUE) break;
            }

            System.out.println("#" + t + " " + minLength);
        }
    }
}
