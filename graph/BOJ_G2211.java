package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G2211 {
    /*
    1. 최소 개수의 회선만을 복구
    2. 슈퍼컴퓨터가 다른 컴퓨터들과 통신하는데 걸리는 최소 시간이,
        원래의 네트워크에서 통신하는데 걸리는 최소 시간보다 커져서는 안 된다
    3. 1 ≤ N ≤ 1,000
     */
    static int N, M;
    static List<List<Line>> network = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        for (int n = 0; n <= N; n++) network.add(new ArrayList<>());
        for (int m = 0; m < M; m++) {
            inputs = br.readLine().split(" ");
            int A = parseInt(inputs[0]), B = parseInt(inputs[1]), C = parseInt(inputs[2]);
            network.get(A).add(new Line(B, C, List.of(B)));
            network.get(B).add(new Line(A, C, List.of(A)));
        }

        Queue<Line> pq = new PriorityQueue<>();
        int[] shortestPath = new int[N + 1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        List<List<Integer>> routes = new ArrayList<>();
        for (int n = 0; n <= N; n++) routes.add(new ArrayList<>());

        shortestPath[1] = 0;
        for (Line next : network.get(1)) {
            shortestPath[next.computer] = next.cost;
            pq.add(next);
            routes.get(next.computer).addAll(next.routes);
        }

        while (!pq.isEmpty()) {
            Line front = pq.poll();
            if (front.cost > shortestPath[front.computer]) continue;
            for (Line next : network.get(front.computer)) {
                if (shortestPath[next.computer] > shortestPath[front.computer] + next.cost) {
                    shortestPath[next.computer] = shortestPath[front.computer] + next.cost;
                    List<Integer> newList = front.add(next.computer);
                    routes.get(next.computer).clear();
                    routes.get(next.computer).addAll(newList);
                    pq.add(new Line(next.computer, shortestPath[next.computer], newList));
                }
            }
        }

        Set<Answer> ans = new HashSet<>();
        for (int i = 2; i <= N; i++) {
            int prev = 1;
            for (int j = 0; j < routes.get(i).size(); j++) {
                ans.add(new Answer(prev, routes.get(i).get(j)));
                prev = routes.get(i).get(j);
            }
        }

        bw.write(ans.size() + "\n");
        for (Answer a : ans) bw.write(a.a + " " + a.b + "\n");

        bw.flush();
        bw.close();
    }

    static class Line implements Comparable<Line> {
        int computer, cost;
        List<Integer> routes;

        public Line(int computer, int cost, List<Integer> routes) {
            this.computer = computer;
            this.cost = cost;
            this.routes = new ArrayList<>(routes);
        }

        public List<Integer> add(int computer) {
            List<Integer> re = new ArrayList<>(routes);
            re.add(computer);
            return re;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(cost, o.cost);
        }
    }

    static class Answer {
        int a, b;

        public Answer(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Answer answer = (Answer) o;
            return a == answer.a && b == answer.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
