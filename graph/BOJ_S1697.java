package graph;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S1697 {
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);

        if (N == K) {
            bw.write(String.valueOf(0));
            closeWriter(bw);
            return;
        } else if (N > K) {
            bw.write(String.valueOf(N - K));
            closeWriter(bw);
            return;
        }

        int[] directions = {-1, 1, 2};
        int possiblePathSize = K * 2 + 1;

        int[] path = new int[possiblePathSize];
        Arrays.fill(path, MAX);

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        path[N] = 0;

        int front, next;
        while (!q.isEmpty()) {
            front = q.poll();
            for (int dir = 0; dir < 3; dir++) {
                if (directions[dir] == 2) next = front * directions[dir];
                else next = front + directions[dir];
                if (next >= 0 && next < possiblePathSize && path[next] > path[front] + 1) {
                    path[next] = path[front] + 1;
                    q.add(next);
                }
            }
        }
        bw.write(String.valueOf(path[K]));

        closeWriter(bw);
    }

    static void closeWriter(BufferedWriter bw) throws IOException {
        bw.flush();
        bw.close();
    }
}
