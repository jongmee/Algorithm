package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_G1766 {
    /*
    이런 경우도 있음
    3 -> 2
    1 -> 2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        int[] ingoings = new int[N + 1];
        List<Integer>[] tree = new List[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            inputs = br.readLine().split(" ");
            int A = parseInt(inputs[0]), B = parseInt(inputs[1]);
            tree[A].add(B);
            ingoings[B] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) if (ingoings[i] == 0) pq.add(i);

        while (!pq.isEmpty()) { // 4 -> 3 -> 2 -> 4 같은 경우는 주어지지 않겠지?
            int front = pq.poll();
            bw.write(front + " ");

            for (int next : tree[front]) {
                ingoings[next] -= 1;
                if (ingoings[next] == 0) pq.add(next);
            }
        }

        bw.flush();
        bw.close();
    }
}
