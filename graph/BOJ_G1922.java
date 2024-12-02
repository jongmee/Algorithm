package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G1922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());

        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            String[] inputs = br.readLine().split(" ");
            int a = parseInt(inputs[0]);
            int b = parseInt(inputs[1]);
            int c = parseInt(inputs[2]);
            if (a == b) continue;
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int[] visited = new int[N + 1];
        Queue<Node> pq = new PriorityQueue<>();

        int ans = 0, cnt = 0;
        visited[1] = 1;
        for (Node first : graph[1]) pq.add(first);

        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (visited[front.num] == 1) continue;
            visited[front.num] = 1;

            ans += front.cost;

            cnt++;
            if (cnt == N - 1) break;

            for (Node next : graph[front.num]) {
                if (visited[next.num] == 0) {
                    pq.add(next);
                }
            }
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static class Node implements Comparable<Node> {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
