package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            if (i % 2 == 0) dp[i] = Integer.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Integer.min(dp[i], dp[i / 3] + 1);
            dp[i] = Integer.min(dp[i], dp[i - 1] + 1);
        }

        bw.write(dp[N] + "\n");
        bw.write(N + " ");
        
        int cnt = dp[N], prev = N;
        for (int i = N; i >= 1; i--) {
            if (dp[i] < cnt && can(prev, i)) {
                bw.write(i + " ");
                cnt = dp[i];
                prev = i;
            }
        }

        bw.flush();
        bw.close();
    }

    private static boolean can(int prev, int aft) {
        return prev - aft == 1 || aft * 2 == prev || aft * 3 == prev;
    }
}
