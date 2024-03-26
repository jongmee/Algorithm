package dp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.*;

public class BOJ_G1520 {
    static final int MAX_HEIGHT = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int M = parseInt(inputs[0]), N = parseInt(inputs[1]);

        int[][] map = new int[M + 2][N + 2];
        for (int i = 0; i < M + 2; i++) Arrays.fill(map[i], MAX_HEIGHT);

        for (int i = 1; i <= M; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) map[i][j] = parseInt(inputs[j - 1]);
        }

        int[] dirX = {0, 0, -1, 1}; // 상하좌우
        int[] dirY = {-1, 1, 0, 0};

        long[][] dp = new long[M + 2][N + 2];
        dp[1][1] = 1;
        for (int m = 1; m <= M; m++) {
            for (int n = 1; n <= N; n++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = n + dirX[dir];
                    int nextY = m + dirY[dir];
                    if (map[nextY][nextX] < map[m][n]) {
                        dp[nextY][nextX] += dp[m][n];
                    }
                }
            }
        }

        int repeat = 4;
        List<Integer> dirIdx = new ArrayList<>(4);
        while(repeat-->0) {
            for (int m = 1; m <= M; m++) {
                for (int n = 1; n <= N; n++) {
                    if (m == 1 && n == 1) continue;
                    dirIdx.clear();
                    for (int dir = 0; dir < 4; dir++) {
                        int prevX = n + dirX[dir];
                        int prevY = m + dirY[dir];
                        if (map[prevY][prevX] > map[m][n]) dirIdx.add(dir);
                    }
                    long tmp = 0;
                    for (int idx : dirIdx) tmp += dp[m + dirY[idx]][n + dirX[idx]];
                    dp[m][n] = tmp;
                }
            }
        }

        bw.write(String.valueOf(dp[M][N]));

        bw.flush();
        bw.close();
    }
}
