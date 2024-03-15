package dp;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_S10844 {
    /*
    계단 수: 인접한 모든 자리의 차이가 1
    구할 것: 길이가 N인 계단 수의 개수 % 1_000_000_000
     */
    static final int DIVIDER = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[][] dp = new int[N + 2][12];
        for (int num = 1; num < 11; num++) dp[1][num] = 1;

        for (int len = 2; len <= N; len++) {
            for (int num = 1; num < 11; num++) {
                dp[len][num] = (dp[len - 1][num - 1] + dp[len - 1][num + 1]) % DIVIDER;
            }
        }

        int sum = 0;
        for (int i = 2; i < 11; i++) sum = (sum + dp[N][i]) % DIVIDER;
        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
    }
}
