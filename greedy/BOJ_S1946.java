package greedy;

import java.io.*;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_S1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (T-- > 0) {
            int N = parseInt(br.readLine());
            int[] ranks = new int[N + 1];
            for (int i = 0; i < N; i++) {
                String[] inputs = br.readLine().split(" ");
                ranks[parseInt(inputs[0])] = parseInt(inputs[1]);
            }

            int cnt = 0;
            for (int i = 1; i < N + 1; i++) {
                pq.add(ranks[i]);
                if (pq.peek().equals(ranks[i])) cnt++;
            }

            bw.write(cnt + "\n");
            pq.clear();
        }

        bw.flush();
        bw.close();
    }
}
