package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_P1725 {
    static int[] minHeightIdxSegmentTree;
    static long[] graph;
    static long maxArea = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        minHeightIdxSegmentTree = new int[N * 4];
        graph = new long[N + 1];
        graph[N] = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) graph[i] = Long.parseLong(br.readLine());
        init(0, N - 1, 1);

        findMaxArea(0, N - 1);

        bw.write(maxArea + "\n");

        bw.flush();
        bw.close();
    }

    private static int init(int start, int end, int treeIdx) {
        if (start == end) return minHeightIdxSegmentTree[treeIdx] = start;
        int mid = (start + end) / 2;
        int firstIdx = init(start, mid, treeIdx * 2);
        int secondIdx = init(mid + 1, end, treeIdx * 2 + 1);
        if (graph[firstIdx] < graph[secondIdx]) return minHeightIdxSegmentTree[treeIdx] = firstIdx;
        return minHeightIdxSegmentTree[treeIdx] = secondIdx;
    }

    private static int findMinHeightIdx(int start, int end, int left, int right, int treeIdx) {
        if (start > right || end < left) return N;
        if (left <= start && end <= right) return minHeightIdxSegmentTree[treeIdx];
        int mid = (start + end) / 2;
        int firstIdx = findMinHeightIdx(start, mid, left, right, treeIdx * 2);
        int secondIdx = findMinHeightIdx(mid + 1, end, left, right, treeIdx * 2 + 1);
        if (graph[firstIdx] < graph[secondIdx]) return firstIdx;
        return secondIdx;
    }

    private static void findMaxArea(int left, int right) {
        if (left > right) return;
        int minIdx = findMinHeightIdx(0, N - 1, left, right, 1);
        long area = graph[minIdx] * (right - left + 1);
        maxArea = Long.max(area, maxArea);
        findMaxArea(left, minIdx - 1);
        findMaxArea(minIdx + 1, right);
    }
}
