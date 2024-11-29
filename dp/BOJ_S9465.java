package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S9465 {
    /*
    for 문으로 푼 solution2가 재귀인 solution1보다 2배 빠름
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int n = parseInt(br.readLine());
            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                String[] inputs = br.readLine().split(" ");
                for (int j = 0; j < n; j++) stickers[i][j] = parseInt(inputs[j]);
            }
            bw.write(solution2(n, stickers) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solution2(int n, int[][] stickers) {
        dp = new int[2][n];
        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];
        for (int c = 1; c < n; c++) {
            if (c == 1) {
                dp[0][c] = dp[1][c - 1] + stickers[0][c];
                dp[1][c] = dp[0][c - 1] + stickers[1][c];
            } else {
                dp[0][c] = Integer.max(Integer.max(dp[1][c - 1] + stickers[0][c], dp[0][c - 2] + stickers[0][c]), dp[1][c - 2] + stickers[0][c]);
                dp[1][c] = Integer.max(Integer.max(dp[0][c - 1] + stickers[1][c], dp[0][c - 2] + stickers[1][c]), dp[1][c - 2] + stickers[1][c]);
            }
        }
        return Integer.max(dp[0][n - 1], dp[1][n - 1]);
    }

    private static int solution1(int n, int[][] stickers) {
        dp = new int[2][n];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        int case1 = getMax(n - 1, 0, stickers);
        int case2 = getMax(n - 1, 1, stickers);
        return Integer.max(case1, case2);
    }

    private static int[][] dp;

    private static int getMax(int c, int r, int[][] stickers) {
        if (dp[r][c] != -1) return dp[r][c];
        if (c == 1) return dp[r][c] = stickers[r][c] + stickers[toggle(r)][c - 1];
        if (c == 0) return dp[r][c] = stickers[r][c];
        int result = Integer.max(getMax(c - 1, toggle(r), stickers) + stickers[r][c], getMax(c - 2, r, stickers) + stickers[r][c]);
        result = Integer.max(result, getMax(c - 2, toggle(r), stickers) + stickers[r][c]);
        return dp[r][c] = result;
    }

    private static int toggle(int n) {
        if (n == 0) return 1;
        return 0;
    }
}
