package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int K = parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");

            int[] costs = new int[K + 1], sum = new int[K + 1];
            for (int i = 1; i <= K; i++) {
                costs[i] = parseInt(inputs[i - 1]);
                if (i == 1) sum[i] = costs[i];
                else sum[i] = sum[i - 1] + costs[i];
            }


            int[][] dp = new int[K + 1][K + 1];
            for (int gap = 1; gap <= K - 1; gap++) {
                for (int from = 1; from + gap <= K; from++) {
                    int to = from + gap;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int mid = from; mid <= to - 1; mid++) {
                        dp[from][to] = Integer.min(dp[from][to], dp[from][mid] + dp[mid + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            bw.write(dp[1][K] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
