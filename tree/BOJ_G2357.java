package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G2357 {
    private static int[] numbers, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
        numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(br.readLine());

        minTree = new int[N * 4];
        maxTree = new int[N * 4];
        initMinTree(0, N - 1, 1);
        initMaxTree(0, N - 1, 1);

        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int left = Integer.parseInt(line[0]) - 1;
            int right = Integer.parseInt(line[1]) - 1;
            bw.write(findMin(0, N - 1, left, right, 1) + " " +
                    findMax(0, N - 1, left, right, 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int initMinTree(int start, int end, int treeIdx) {
        if (start == end) return minTree[treeIdx] = numbers[start];
        int mid = (start + end) / 2;
        return minTree[treeIdx] = Integer.min(initMinTree(start, mid, treeIdx * 2),
                initMinTree(mid + 1, end, treeIdx * 2 + 1));
    }

    private static int initMaxTree(int start, int end, int treeIdx) {
        if (start == end) return maxTree[treeIdx] = numbers[start];
        int mid = (start + end) / 2;
        return maxTree[treeIdx] = Integer.max(initMaxTree(start, mid, treeIdx * 2),
                initMaxTree(mid + 1, end, treeIdx * 2 + 1));
    }

    private static int findMin(int start, int end, int left, int right, int treeIdx) {
        if (end < left || start > right) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[treeIdx];
        int mid = (start + end) / 2;
        return Integer.min(findMin(start, mid, left, right, treeIdx * 2),
                findMin(mid + 1, end, left, right, treeIdx * 2 + 1));
    }

    private static int findMax(int start, int end, int left, int right, int treeIdx) {
        if (end < left || start > right) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[treeIdx];
        int mid = (start + end) / 2;
        return Integer.max(findMax(start, mid, left, right, treeIdx * 2),
                findMax(mid + 1, end, left, right, treeIdx * 2 + 1));
    }
}
