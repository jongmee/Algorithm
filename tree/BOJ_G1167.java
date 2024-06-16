package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_G1167 {
    static List<List<Node>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList<>(V);
        for (int i = 0; i <= V; i++) tree.add(new ArrayList<>());

        for (int v = 1; v <= V; v++) {
            String[] line = br.readLine().split(" ");
            int vertex = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length - 1; i += 2) {
                tree.get(vertex).add(new Node(Integer.parseInt(line[i]), Integer.parseInt(line[i + 1])));
            }
        }

        int[] visited = new int[V + 1];
        visited[1] = 1;
        dfs(0, 1, visited);

        Arrays.fill(visited, 0);
        visited[maxNode] = 1;
        dfs(0, maxNode, visited);

        bw.write(maxLen + "\n");

        bw.flush();
        bw.close();
    }

    static int maxLen = -1;
    static int maxNode;

    private static void dfs(int sum, int startIdx, int[] visited) {
        if (maxLen < sum) {
            maxLen = sum;
            maxNode = startIdx;
        }

        for (Node next : tree.get(startIdx)) {
            if (visited[next.idx] == 0) {
                visited[next.idx] = 1;
                dfs(sum + next.cost, next.idx, visited);
            }
        }
    }

    private static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
