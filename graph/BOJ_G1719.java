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

public class BOJ_G1719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        List<List<Node>> routes = new ArrayList<>();
        for (int n = 0; n <= N; n++) routes.add(new ArrayList<>());
        for (int m = 0; m < M; m++) {
            inputs = br.readLine().split(" ");
            int first = parseInt(inputs[0]), second = parseInt(inputs[1]);
            int cost = parseInt(inputs[2]);
            routes.get(first).add(new Node(second, cost, second));
            routes.get(second).add(new Node(first, cost, first));
        }

        int[] shortestPath = new int[N + 1];
        int[] firstNode = new int[N + 1];
        Queue<Node> pq = new PriorityQueue<>();
        for (int startNode = 1; startNode <= N; startNode++) {
            // init
            pq.clear();
            Arrays.fill(shortestPath, Integer.MAX_VALUE);
            Arrays.fill(firstNode, -1);
            // insert first next node to pq
            shortestPath[startNode] = 0;
            for (Node next : routes.get(startNode)) {
                pq.add(next);
                shortestPath[next.num] = next.cost;
                firstNode[next.num] = next.num;
            }
            // fill table shortestPath
            while (!pq.isEmpty()) {
                Node front = pq.poll();
                if (front.cost > shortestPath[front.num]) continue;
                for (Node next : routes.get(front.num)) {
                    if (shortestPath[next.num] > shortestPath[front.num] + next.cost) {
                        shortestPath[next.num] = shortestPath[front.num] + next.cost;
                        firstNode[next.num] = front.first;
                        pq.add(new Node(next.num, shortestPath[next.num], front.first));
                    }
                }
            }
            for (int n = 1; n <= N; n++) {
                if (startNode == n) bw.write("- ");
                else bw.write(firstNode[n] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int num, cost, first;

        public Node(int num, int cost, int first) {
            this.num = num;
            this.cost = cost;
            this.first = first;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
