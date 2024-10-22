package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G1106 {
    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int C = parseInt(inputs[0]);
        N = parseInt(inputs[1]);
        int[][] cities = new int[N][2];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            cities[i][0] = parseInt(inputs[0]); // 비용
            cities[i][1] = parseInt(inputs[1]); // 고객 수
        }

        dp = new int[N][C + 1];
        int cost = cities[0][0], cnt = cities[0][1];
        for (int i = 1; i <= C; i++) {
            if (i <= cnt) dp[0][i] = cost;
            else {
                cost += cities[0][0];
                cnt += cities[0][1];
                dp[0][i] = cost;
            }
        }

        for (int i = 1; i < N; i++) {
            cost = cities[i][0];
            cnt = cities[i][1];
            for (int j = 1; j <= C; j++) {
                if (j <= cnt) dp[i][j] = cost;
                else {
                    cost += cities[i][0];
                    cnt += cities[i][1];
                }
            }
            for (int j = 1; j <= C; j++) {
                if (j - cities[i][1] >= 1) dp[i][j] = Integer.min(dp[i - 1][j], dp[i][j - cities[i][1]] + cities[i][0]);
                else dp[i][j] = Integer.min(dp[i - 1][j], dp[i][j]);
            }
        }

        bw.write(dp[N - 1][C] + "\n");

        bw.flush();
        bw.close();
    }
}
