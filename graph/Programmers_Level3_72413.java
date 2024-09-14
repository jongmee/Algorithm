package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Programmers_Level3_72413 {
    private List<Node>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        ANS = Integer.MAX_VALUE;
        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<>();
        for (int[] fare : fares) {
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }

        int[] started = new int[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[s] = 0;
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (started[front.dest] == 0) {
                started[front.dest] = 1;
                getAloneCost(a, b, front.dest, n, front.cost);
            }
            if (front.cost > costs[front.dest]) continue;
            for (Node next : graph[front.dest]) {
                if (costs[next.dest] > front.cost + next.cost) {
                    costs[next.dest] = front.cost + next.cost;
                    pq.add(new Node(next.dest, costs[next.dest]));
                }
            }
        }
        return ANS;
    }

    private int ANS;

    private void getAloneCost(int a, int b, int startNode, int n, int togetherCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[startNode] = 0;
        pq.add(new Node(startNode, 0));
        while (!pq.isEmpty()) {
            Node front = pq.poll();
            if (front.cost > costs[front.dest]) continue;
            for (Node next : graph[front.dest]) {
                if (costs[next.dest] > front.cost + next.cost) {
                    costs[next.dest] = front.cost + next.cost;
                    pq.add(new Node(next.dest, costs[next.dest]));
                }
            }
        }
        if (costs[a] == Integer.MAX_VALUE || costs[b] == Integer.MAX_VALUE) return;
        ANS = Integer.min(ANS, costs[a] + costs[b] + togetherCost);
    }

    private class Node implements Comparable<Node> {
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

    public static void main(String[] args) {
        Programmers_Level3_72413 solution = new Programmers_Level3_72413();
        System.out.println(solution.solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
        System.out.println(solution.solution(6, 4, 6, 2,
                new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
    }
}
