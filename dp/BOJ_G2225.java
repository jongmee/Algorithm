package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G2225 {
    private static final int DIVIDER = 1_000_000_000;
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        K = parseInt(inputs[1]);
        dp = new long[N + 1][K + 1];
        find(N, 0);

        bw.write(dp[N][0] % DIVIDER + "\n");

        bw.flush();
        bw.close();
    }

    private static long[][] dp;

    private static long find(int rest, int cnt) {
        if (cnt >= 0 && rest >= 0 && dp[rest][cnt] != 0) return dp[rest][cnt];
        if (rest == 0) {
            return dp[rest][cnt] = (dp[rest][cnt] + 1) % DIVIDER;
        }
        if (cnt == K || rest < 0) return 0;

        for (int next = 0; next <= N; next++) dp[rest][cnt] += (find(rest - next, cnt + 1) % DIVIDER);
        return dp[rest][cnt] % DIVIDER;
    }
}
