package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BOJ_G2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), K = Integer.parseInt(line[1]);
        line = br.readLine().split("");
        int[] numbers = new int[N + 2];
        numbers[0] = 10;
        numbers[N + 1] = 10;
        for (int i = 0; i < N; i++) numbers[i + 1] = Integer.parseInt(line[i]);

        Queue<Node> pq = new PriorityQueue<>();
        Set<Integer> idxToRemove = new HashSet<>();
        int cnt = 0, idx = 1;
        while (cnt < K) {
            if (idx == N + 1 || (numbers[idx] >= numbers[idx + 1] && numbers[idx] >= numbers[idx - 1])) {
                // numbers[idx]가 꼭대기
                while (!pq.isEmpty() && pq.peek().val < numbers[idx]) {
                    Node node = pq.poll();
                    idxToRemove.add(node.idx);
                    cnt++;
                    if (cnt >= K) break;
                }
            }
            pq.add(new Node(numbers[idx], idx));
            idx++;
        }

        for (int i = 1; i <= N; i++) {
            if (!idxToRemove.contains(i)) bw.write(numbers[i] + "");
        }
        bw.write("\n");


        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int val, idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(val, o.val);
        }
    }
}
