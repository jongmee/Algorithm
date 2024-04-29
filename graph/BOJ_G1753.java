package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int V = parseInt(inputs[0]), E = parseInt(inputs[1]);
        int K = parseInt(br.readLine());
        List<List<Node>> nodes = new ArrayList<>();
        for (int v = 0; v <= V; v++) nodes.add(new ArrayList<>());

        for (int e = 0; e < E; e++) {
            inputs = br.readLine().split(" ");
            int u = parseInt(inputs[0]);
            int v = parseInt(inputs[1]);
            int w = parseInt(inputs[2]);
            nodes.get(u).add(new Node(v, w));
        }

        int[] result = dijkstra(nodes, K, V);

        for (int i = 1; i <= V; i++) {
            if (i == K) bw.write("0\n");
            else if (result[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(result[i] + "\n");
        }


        bw.flush();
        bw.close();
    }

    static int[] dijkstra(final List<List<Node>> nodes, int start, int V) {
        int[] costs = new int[V + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Queue<Node> pq = new PriorityQueue<>();

        costs[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (front.cost > costs[front.position]) continue;

            for (Node nextNode : nodes.get(front.position)) {
                int nextCost = costs[front.position] + nextNode.cost;
                if (nextCost < costs[nextNode.position]) {
                    costs[nextNode.position] = nextCost;
                    pq.add(new Node(nextNode.position, nextCost));
                }
            }
        }
        return costs;
    }

    static class Node implements Comparable<Node> {
        int position, cost;

        public Node(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
