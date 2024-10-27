package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_G2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = parseInt(br.readLine());
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            nodes[i] = new Node(parseInt(inputs[0]), parseInt(inputs[1]));
        }
        Arrays.sort(nodes, Comparator.comparingInt(n2 -> n2.d));

        int money = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n1 -> n1.p));
        for (Node node : nodes) {
            pq.add(node);
            while (pq.size() > node.d) pq.poll();
        }

        while (!pq.isEmpty()) money += pq.poll().p;

        bw.write(money + "\n");

        bw.flush();
        bw.close();
    }

    private static class Node {
        int p, d;

        public Node(int p, int d) {
            this.p = p;
            this.d = d;
        }

        public Node minus() {
            this.d -= 1;
            return this;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "p=" + p +
                    ", d=" + d +
                    '}';
        }
    }
}
