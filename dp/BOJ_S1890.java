package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_S1890 {
    static int N;
    static int[] dirX = {0, 1}; // 아래, 오른쪽
    static int[] dirY = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(line[j]);
        }

        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int m = 0; m < 2; m++) {
                    int nextX = j + dirX[m] * map[i][j];
                    int nextY = i + dirY[m] * map[i][j];
                    if (checkIdx(nextX, nextY)) {
                        if (i == N - 1 && j == N - 1) {
                            break;
                        }
                        dp[nextY][nextX] += dp[i][j];
                    }

                }
            }
        }

        bw.write(dp[N - 1][N - 1] + "\n");

        bw.flush();
        bw.close();
    }

    private static boolean checkIdx(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
