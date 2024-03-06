package greedy;

import java.io.*;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_S13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        int[] roads = new int[N - 1];
        int[] prices = new int[N];

        String[] roadsInput = br.readLine().split(" ");
        String[] pricesInput = br.readLine().split(" ");
        for (int i = 0; i < N - 1; i++) roads[i] = parseInt(roadsInput[i]);
        for (int i = 0; i < N; i++) prices[i] = parseInt(pricesInput[i]);

        long totalCost = 0;
        PriorityQueue<Integer> optimalPrices = new PriorityQueue<>();
        for (int i = 0; i < N - 1; i++) {
            optimalPrices.add(prices[i]);
            long cost = optimalPrices.peek();
            totalCost += cost * (long) roads[i];
        }

        bw.write(String.valueOf(totalCost));

        bw.flush();
        bw.close();
    }
}
