package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Programmers_Level2_12978 {
    private List<Town>[] graph;
    private int[] costs;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        graph = new List[N + 1];
        costs = new int[N + 1];
        for (int i = 1; i < N + 1; i++) graph[i] = new ArrayList<>();
        for (int[] path : road) {
            graph[path[0]].add(new Town(path[1], path[2]));
            graph[path[1]].add(new Town(path[0], path[2]));
        }
        dijkstra();
        for (int cost : costs) if (cost <= K) answer++;
        return answer;
    }

    private void dijkstra() {
        PriorityQueue<Town> pq = new PriorityQueue<>();
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;
        pq.add(new Town(1, 0));
        while (!pq.isEmpty()) {
            Town front = pq.poll();
            if (front.cost > costs[front.dest]) continue;
            for (Town next : graph[front.dest]) {
                if (costs[next.dest] > front.cost + next.cost) {
                    costs[next.dest] = front.cost + next.cost;
                    pq.add(new Town(next.dest, costs[next.dest]));
                }
            }
        }
    }

    private class Town implements Comparable<Town> {
        int dest, cost;

        public Town(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Town o) {
            return Integer.compare(cost, o.cost);
        }
    }

    public static void main(String[] args) {
        Programmers_Level2_12978 solution = new Programmers_Level2_12978();
        System.out.println(solution.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        System.out.println(solution.solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }
}
