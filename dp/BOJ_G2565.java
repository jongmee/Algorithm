package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class BOJ_G2565 {
    private static int[][] lines;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = parseInt(br.readLine());
        lines = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            lines[i][0] = parseInt(inputs[0]);
            lines[i][1] = parseInt(inputs[1]);
        }

        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (lines[j][1] < lines[i][1]) dp[i] = Integer.max(dp[j] + 1, dp[i]);
            }
        }

        int max = -1;
        for (int d : dp) max = Integer.max(max, d);

        bw.write((n - max) + "\n");

        bw.flush();
        bw.close();
    }
}
