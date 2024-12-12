package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S15990 {
    private static int DIVIDER = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        long[][] dp = new long[100_001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][3] = 1;

        for (int num = 3; num < 100_001; num++) {
            dp[num][1] += (dp[num - 1][2] + dp[num - 1][3]) % DIVIDER;
            dp[num][2] += (dp[num - 2][1] + dp[num - 2][3]) % DIVIDER;
            dp[num][3] += (dp[num - 3][1] + dp[num - 3][2]) % DIVIDER;
        }

        while (T-- > 0) {
            int n = parseInt(br.readLine());
            bw.write(((dp[n][1] + dp[n][2] + dp[n][3]) % DIVIDER) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
