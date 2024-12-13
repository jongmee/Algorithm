package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), S = parseInt(inputs[1]), M = parseInt(inputs[2]);
        inputs = br.readLine().split(" ");

        int[][] dp = new int[N + 1][M + 1];
        dp[0][S] = 1;
        for (int i = 0; i < N; i++) {
            int volume = parseInt(inputs[i]);
            for (int j = 0; j <= M; j++) {
                if (dp[i][j] == 1) {
                    if (j + volume <= M) dp[i + 1][j + volume] = 1;
                    if (j - volume >= 0) dp[i + 1][j - volume] = 1;
                }
            }
        }

        int ans = -1;
        for (int i = M; i >= 0; i--) {
            if (dp[N][i] == 1) {
                ans = i;
                break;
            }
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }
}
