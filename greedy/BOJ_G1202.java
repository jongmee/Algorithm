package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);
        Jewellery[] jewelleries = new Jewellery[N];
        int[] bags = new int[K];

        for (int n = 0; n < N; n++) {
            inputs = br.readLine().split(" ");
            jewelleries[n] = new Jewellery(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        for (int k = 0; k < K; k++) bags[k] = Integer.parseInt(br.readLine());

        Arrays.sort(bags);
        Arrays.sort(jewelleries, Comparator.comparingInt(o -> o.weight));

        long ans = 0;
        int jIdx = 0;
        Queue<Jewellery> pq = new PriorityQueue<>();
        for (int k = 0; k < K; k++) {
            while (jIdx < N && bags[k] >= jewelleries[jIdx].weight) {
                pq.add(jewelleries[jIdx]);
                jIdx++;
            }
            if (!pq.isEmpty()) ans += pq.poll().value;
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static class Jewellery implements Comparable<Jewellery> {
        int weight, value;

        public Jewellery(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewellery o) {
            return Integer.compare(o.value, value);
        }
    }
}
