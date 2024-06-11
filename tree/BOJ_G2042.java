package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G2042 {
    private static long[] numbers, segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]), Q = Integer.parseInt(line[2]);
        numbers = new long[N];
        for (int i = 0; i < N; i++) numbers[i] = Long.parseLong(br.readLine());
        segmentTree = new long[N * 4];
        init(0, N - 1, 1);

        for (int i = 0; i < M + Q; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            if (a == 1) {
                long c = Long.parseLong(line[2]);
                b--;
                long ori = numbers[b];
                numbers[b] = c;
                update(0, N - 1, b, c - ori, 1);
            } else {
                int c = Integer.parseInt(line[2]);
                b--;
                c--;
                bw.write(sum(0, N - 1, b, c, 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static long init(int start, int end, int treeIdx) {
        if (start == end) return segmentTree[treeIdx] = numbers[start];
        int mid = (start + end) / 2;
        return segmentTree[treeIdx] = init(start, mid, treeIdx * 2) + init(mid + 1, end, treeIdx * 2 + 1);
    }

    private static void update(int start, int end, int idx, long val, int treeIdx) {
        if (start > idx || end < idx) return;
        segmentTree[treeIdx] += val;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, idx, val, treeIdx * 2);
        update(mid + 1, end, idx, val, treeIdx * 2 + 1);
    }

    private static long sum(int start, int end, int left, int right, int treeIdx) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return segmentTree[treeIdx];
        int mid = (start + end) / 2;
        return sum(start, mid, left, right, treeIdx * 2) + sum(mid + 1, end, left, right, treeIdx * 2 + 1);
    }
}
