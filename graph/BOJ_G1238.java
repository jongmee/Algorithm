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

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_G1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]), X = parseInt(inputs[2]);
        List<List<Node>> roads = new ArrayList<>(N + 1);
        List<List<Node>> reverse = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int from = parseInt(inputs[0]), dest = parseInt(inputs[1]), cost = parseInt(inputs[2]);
            roads.get(from).add(new Node(dest, cost));
            reverse.get(dest).add(new Node(from, cost));
        }

        int maxTime = -1;
        int[] minTimesToX = getMinTime(X, reverse, N);
        int[] minTimesToHome = getMinTime(X, roads, N);
        for (int i = 1; i <= N; i++) {
            int total = minTimesToX[i] + minTimesToHome[i];
            maxTime = max(maxTime, total);
        }

        bw.write(maxTime + "\n");
        bw.flush();
        bw.close();
    }

    static int[] getMinTime(int X, List<List<Node>> roads, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        int[] minTimes = new int[N + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        minTimes[X] = 0;
        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (front.cost > minTimes[front.dest]) continue;
            for (Node next : roads.get(front.dest)) {
                if (minTimes[next.dest] > front.cost + next.cost) {
                    minTimes[next.dest] = front.cost + next.cost;
                    pq.add(new Node(next.dest, minTimes[next.dest]));
                }
            }
        }
        return minTimes;
    }

    static class Node implements Comparable<Node> {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
