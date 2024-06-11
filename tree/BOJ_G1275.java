package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G1275 {
    private static long[] numbers, segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), Q = Integer.parseInt(line[1]);

        line = br.readLine().split(" ");
        numbers = new long[N];
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(line[i]);

        segmentTree = new long[N * 4];
        init(0, N - 1, 1);

        for (int i = 0; i < Q; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]) - 1;
            int y = Integer.parseInt(line[1]) - 1;
            int a = Integer.parseInt(line[2]) - 1;
            long b = Long.parseLong(line[3]);
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            bw.write(sum(0, N - 1, x, y, 1) + "\n");
            long ori = numbers[a];
            update(0, N - 1, a, b - ori, 1);
            numbers[a] = b;
        }

        bw.flush();
        bw.close();
    }

    private static long init(int start, int end, int treeIdx) {
        if (start == end) return segmentTree[treeIdx] = numbers[start];
        int mid = (start + end) / 2;
        return segmentTree[treeIdx] = init(start, mid, treeIdx * 2) + init(mid + 1, end, treeIdx * 2 + 1);
    }

    private static long sum(int start, int end, int x, int y, int treeIdx) {
        if (start > y || end < x) return 0;
        if (x <= start && y >= end) return segmentTree[treeIdx];
        int mid = (start + end) / 2;
        return sum(start, mid, x, y, treeIdx * 2) + sum(mid + 1, end, x, y, treeIdx * 2 + 1);
    }

    private static void update(int start, int end, int idx, long val, int treeIdx) {
        if (start > idx || end < idx) return;
        segmentTree[treeIdx] += val;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, idx, val, treeIdx * 2);
        update(mid + 1, end, idx, val, treeIdx * 2 + 1);
    }
}
