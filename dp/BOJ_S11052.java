package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S11052 {
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        cards = new int[N + 1];
        for (int i = 0; i < N; i++) cards[i + 1] = parseInt(inputs[i]);

        // 1번 풀이
        maxCosts = new int[N + 1];
        combinationWithMemorization(N, 1, 0, 0);
        bw.write(maxCosts[N] + "\n");

        // 2번 풀이 (10배 빠름)
        bw.write(dp(N) + "\n");

        bw.flush();
        bw.close();
    }

    private static int dp(int N) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Integer.max(dp[i], dp[i - j] + cards[j]);
            }
        }
        return dp[N];
    }

    private static int[] maxCosts;

    private static void combinationWithMemorization(int N, int start, int cnt, int cost) {
        for (int i = start; i <= N; i++) {
            if (cnt + i <= N && maxCosts[cnt + i] < cost + cards[i]) {
                maxCosts[cnt + i] = cost + cards[i];
                combinationWithMemorization(N, i, cnt + i, cost + cards[i]);
            }
        }
    }
}
