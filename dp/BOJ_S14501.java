package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            schedule[i][0] = parseInt(inputs[0]);
            schedule[i][1] = parseInt(inputs[1]);
        }
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) dp[i] = schedule[i][1];

        for (int i = 0; i < N; i++) {
            int nextDay = i + schedule[i][0];
            for (int next = nextDay; next < N; next++) {
                if (schedule[next][0] + next <= N) {
                    dp[next] = Integer.max(dp[next], schedule[next][1] + dp[i]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (schedule[i][0] + i <= N) ans = Integer.max(ans, dp[i]);
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
