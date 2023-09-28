package week8;

import java.io.*;
import java.util.*;

public class Easy2048 {

    static class Solution {
        int rc = 0;
        class Block {
            int value;
            Block(int v) {
                value = v;
            }
            @Override
            public String toString() {
                return Integer.toString(value);
            }
        }
        int n;
        int max=2;
        Block[][] copy(Block[][] blocks) {
            Block[][] newBlocks = new Block[n][n];
            //deep copy
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    newBlocks[i][j] = new Block(blocks[i][j].value);
                }
            }
            return newBlocks;
        }

        Block[] leftMerge(Block[] target) {
            ArrayDeque<Block> arr = new ArrayDeque<>(n);
            for (int j=0;j<n-1;j++) {
                if (target[j].value == target[j+1].value) {
                    target[j].value *= 2;
                    target[j+1].value = 0;
                    j++;
                    max = Math.max(target[j].value, max);
                }
            }
            for (int j=0;j<n;j++) {
                if (target[j].value != 0) {
                    arr.add(target[j]);
                    max = Math.max(target[j].value, max);
                }
            }
            int zero = n-arr.size();
            for (int j=0;j<zero;j++) {
                arr.add(new Block(0));
            }
            return arr.toArray(new Block[n]);
        }

        Block[] rightMerge(Block[] target) {
            for (int j=n-1;j>0;j--) {
                if (target[j].value == target[j-1].value) {
                    target[j].value *= 2;
                    target[j-1].value = 0;
                    j--;
                }
            }
            ArrayDeque<Block> arr = new ArrayDeque<>(n);
            for (int j=0;j<n;j++) {
                if (target[j].value != 0) {
                    arr.add(target[j]);
                    max = Math.max(target[j].value, max);
                }
            }
            int zero = n-arr.size();
            for (int j=0;j<zero;j++) {
                arr.addFirst(new Block(0));
            }
            return arr.toArray(new Block[n]);
        }

        Block[][] left(Block[][] blocks) {
            Block[][] newBlock = copy(blocks);
            for (int i=0;i<n;i++) {
                newBlock[i] = leftMerge(newBlock[i]);
            }
            return newBlock;
        }

        Block[][] right(Block[][] blocks) {
            Block[][] newBlock = copy(blocks);
            for (int i=0;i<n;i++) {
                newBlock[i] = rightMerge(newBlock[i]);
            }
            return newBlock;
        }

        Block[][] top(Block[][] blocks) {
            Block[][] newBlock = copy(blocks);
            for (int j=0;j<n;j++) {
                Block[] merged = new Block[n];
                for (int i=0;i<n;i++) {
                    merged[i] = newBlock[i][j];
                }
                merged = leftMerge(merged);
                for (int i=0;i<n;i++) {
                    newBlock[i][j] = merged[i];
                }
            }
            return newBlock;
        }

        Block[][] bottom(Block[][] blocks) {
            Block[][] newBlock = copy(blocks);
            for (int j=0;j<n;j++) {
                Block[] merged = new Block[n];
                for (int i=0;i<n;i++) {
                    merged[i] = newBlock[i][j];
                }
                merged = rightMerge(merged);
                for (int i=0;i<n;i++) {
                    newBlock[i][j] = merged[i];
                }
            }
            return newBlock;
        }

        void dfs(Block[][] blocks, int cnt) {
            for (Block[] block:blocks) {
                System.out.println(Arrays.toString(block));
            }
            System.out.println(rc++);
            System.out.println();
            if (cnt==5) return;
            Block[][] newBlock;
            newBlock = left(blocks);
            dfs(newBlock, cnt+1);
            newBlock = right(blocks);
            dfs(newBlock, cnt+1);
            newBlock = top(blocks);
            dfs(newBlock, cnt+1);
            newBlock = bottom(blocks);
            dfs(newBlock, cnt+1);
        }

        Solution(int n, int[][] map) {
            this.n = n;
            Block[][] blocks = new Block[n][n];

            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    blocks[i][j] = new Block(map[i][j]);
                }
            }
            dfs(blocks,0);
            System.out.println(max);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("mingyun/project/src/week8/2048.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new Solution(n,map);
    }
}
