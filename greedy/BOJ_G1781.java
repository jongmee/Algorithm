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

public class BOJ_G1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            nodes[i] = new Node(parseInt(inputs[0]), parseInt(inputs[1]));
        }
        Arrays.sort(nodes, Comparator.comparingInt(n -> n.date));
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n -> n.prize));
        for (int i = 0; i < N; i++) {
            pq.add(nodes[i]);
            if (pq.size() > nodes[i].date) pq.poll();
        }

        int totalPrize = 0;
        while (!pq.isEmpty()) totalPrize += pq.poll().prize;
        bw.write(totalPrize + "\n");
        bw.flush();
        bw.close();
    }

    private static class Node {
        int date, prize;

        public Node(int date, int prize) {
            this.date = date;
            this.prize = prize;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "date=" + date +
                    ", prize=" + prize +
                    '}';
        }
    }
}
