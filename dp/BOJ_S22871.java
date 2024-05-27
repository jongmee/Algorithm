package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BOJ_S22871 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] rocks = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) rocks[i] = Integer.parseInt(line[i]);

        long[][] cases = findAllCases(N, rocks);
        long[] optimalResult = dp(N, cases);

        bw.write(optimalResult[N - 1] + "\n");

        bw.flush();
        bw.close();
    }

    static long[][] findAllCases(int N, int[] rocks) {
        long[][] cases = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int diff = Integer.max(rocks[i] - rocks[j], rocks[j] - rocks[i]);
                cases[i][j] = (long) (j - i) * (1 + diff);
            }
        }
        return cases;
    }

    static long[] dp(int N, long[][] cases) {
        long[] dp = new long[N];
        dp[1] = cases[0][1];
        for (int i = 2; i < N; i++) {
            long tmpMin = Long.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                if (Long.max(dp[j], cases[j][i]) < tmpMin) {
                    tmpMin = Long.max(dp[j], cases[j][i]);
                }
            }
            tmpMin = Long.min(tmpMin, cases[0][i]);
            dp[i] = tmpMin;
        }
        return dp;
    }
}
