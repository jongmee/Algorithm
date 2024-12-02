package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G1197 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int V = parseInt(inputs[0]), E = parseInt(inputs[1]);

        List<Edge> edges = new ArrayList<>();
        for (int e = 0; e < E; e++) {
            inputs = br.readLine().split(" ");
            edges.add(new Edge(parseInt(inputs[0]), parseInt(inputs[1]), parseInt(inputs[2])));
        }

        Collections.sort(edges);

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) parents[i] = i;

        int ans = 0, cnt = 0;
        for (Edge edge : edges) {
            int fromParent = findParent(edge.from);
            int toParent = findParent(edge.to);
            if (fromParent != toParent) {
                ans += edge.cost;
                cnt++;
                if (toParent < fromParent) parents[fromParent] = toParent;
                else parents[toParent] = fromParent;
            }
            if (cnt == V - 1) break;
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static int findParent(int num) {
        if (parents[num] == num) return num;
        return parents[num] = findParent(parents[num]);
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
