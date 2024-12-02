package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G1504 {
    /*
    다익스트라 시간 복잡도: O(E * logV)
     */
    private static List<Node>[] graph;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        int E = parseInt(inputs[1]);

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int e = 0; e < E; e++) {
            inputs = br.readLine().split(" ");
            int a = parseInt(inputs[0]), b = parseInt(inputs[1]), c = parseInt(inputs[2]);
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        inputs = br.readLine().split(" ");
        int u = parseInt(inputs[0]), v = parseInt(inputs[1]);

        int s2u = dijkstra(1, u);
        int s2v = dijkstra(1, v);
        int u2v = dijkstra(u, v);
        int u2e = dijkstra(u, N);
        int v2e = dijkstra(v, N);


        int minCost = Integer.MAX_VALUE;
        if (s2u != -1 && v2e != -1) minCost = Integer.min(minCost, s2u + v2e);
        if (s2v != -1 && u2e != -1) minCost = Integer.min(minCost, s2v + u2e);

        if (minCost == Integer.MAX_VALUE) bw.write("-1\n");
        else bw.write((minCost + u2v) + "\n");

        bw.flush();
        bw.close();
    }

    private static int dijkstra(int start, int end) {
        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, costs[start]));
        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (front.val == end) return front.cost;

            for (Node next : graph[front.val]) {
                if (costs[next.val] > front.cost + next.cost) {
                    costs[next.val] = front.cost + next.cost;
                    pq.add(new Node(next.val, costs[next.val]));
                }
            }
        }
        return -1; // start에서 end까지 갈 수 없음
    }

    private static class Node implements Comparable<Node> {
        int val, cost;

        public Node(int val, int cost) {
            this.val = val;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
