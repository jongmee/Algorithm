package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G5972 {
    static int MAX_COST = 500_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        List<List<Path>> paths = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) paths.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int point1 = parseInt(inputs[0]);
            int point2 = parseInt(inputs[1]);
            int cost = parseInt(inputs[2]);
            paths.get(point1).add(new Path(point2, cost));
            paths.get(point2).add(new Path(point1, cost));
        }

        int[] costs = new int[N + 1];
        Arrays.fill(costs, MAX_COST);
        costs[1] = 0;

        Queue<Path> pq = new PriorityQueue<>();
        for (Path path : paths.get(1)) costs[path.dest] = path.cost;
        pq.addAll(paths.get(1));
        while (!pq.isEmpty()) {
            Path front = pq.poll();
            for (Path path : paths.get(front.dest)) {
                if (costs[path.dest] > path.cost + front.cost) {
                    costs[path.dest] = path.cost + front.cost;
                    path.cost += front.cost;
                    pq.add(path);
                }
            }
        }

        bw.write(costs[N] + "\n");

        bw.flush();
        bw.close();
    }

    static class Path implements Comparable<Path> {
        int dest, cost; // cost는 소의 개수

        public Path(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
