package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_G2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] children = new int[N + 1];
        for (int i = 0; i < N; i++) children[i + 1] = parseInt(br.readLine());

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i] && dp[j] >= dp[i]) dp[i] = dp[j] + 1;
            }
        }

        int maxVal = -1;
        for (int i = 1; i <= N; i++) maxVal = max(maxVal, dp[i]);
        bw.write((N - maxVal) + "\n");


        bw.flush();
        bw.close();
    }
}
