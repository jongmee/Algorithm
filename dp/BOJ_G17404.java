package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G17404 {
    static int[][] houses;
    static int N, MAX_VAL = 1000 * 1000 + 5;
    static long ans = MAX_VAL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        houses = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) houses[i][j] = parseInt(line[j]);
        }

        fillColor(0);
        fillColor(1);
        fillColor(2);

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static void fillColor(int startColor) {
        long[][] dp = new long[N][3];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], MAX_VAL);
        dp[0][startColor] = houses[0][startColor];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k) continue;
                    dp[i][j] = Long.min(dp[i][j], dp[i - 1][k] + houses[i][j]);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (i == startColor) continue;
            ans = Long.min(ans, dp[N - 1][i]);
        }
    }
}
