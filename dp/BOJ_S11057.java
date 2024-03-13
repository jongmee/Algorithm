package dp;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_S11057 {
    static int divider = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        int[][] dp = new int[N + 1][11];
        int[] total = new int[N + 1];

        for (int i = 0; i < 10; i++) dp[1][i] = 1;
        total[1] = 10;

        for (int num = 2; num <= N; num++) {
            dp[num][0] = total[num - 1];
            int sum = dp[num][0];
            for (int i = 1; i < 10; i++) {
                dp[num][i] = (dp[num][i - 1] - dp[num - 1][i - 1] + divider) % divider; // 주의!
                sum = (sum + dp[num][i]) % divider;
            }
            total[num] = sum;
        }
        bw.write(String.valueOf(total[N]));

        bw.flush();
        bw.close();
    }
}
