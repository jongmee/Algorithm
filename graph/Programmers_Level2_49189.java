package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Programmers_Level2_49189 {
    private List<Integer>[] graph;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] dist = new int[n + 1];
        int last = bfs(dist);
        for (int d : dist) if (dist[last] == d) answer++;

        return answer;
    }

    private int bfs(int[] dist) {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        dist[1] = 0;
        int last = -1;
        while (!q.isEmpty()) {
            int front = q.poll();
            last = front;
            for (int next : graph[front]) {
                if (dist[next] == -1) {
                    dist[next] = dist[front] + 1;
                    q.add(next);
                }
            }
        }
        return last;
    }

    public static void main(String[] args) {
        Programmers_Level2_49189 solution = new Programmers_Level2_49189();
        System.out.println(solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}
