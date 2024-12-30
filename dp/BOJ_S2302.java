package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());

        int[] fixed = new int[N + 1];
        for (int i = 0; i < M; i++) {
            fixed[parseInt(br.readLine())] = 1;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            if (fixed[i] == 0 && fixed[i - 1] == 0) dp[i] = dp[i - 1] + dp[i - 2];
            else dp[i] = dp[i - 1];
        }

        bw.write(dp[N] + "\n");

        bw.flush();
        bw.close();
    }
}
