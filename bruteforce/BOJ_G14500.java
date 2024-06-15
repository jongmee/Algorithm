package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G14500 {
    static int[] dirX = {0, 0, -1, 1}; // 상하좌우
    static int[] dirY = {-1, 1, 0, 0};
    static int[][] paper;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        paper = new int[N][M];
        for (int n = 0; n < N; n++) {
            inputs = br.readLine().split(" ");
            for (int m = 0; m < M; m++) paper[n][m] = Integer.parseInt(inputs[m]);
        }

        int[][] visited = new int[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                visited[y][x] = 1;
                dfs(x, y, 1, visited, paper[y][x]);
                visited[y][x] = 0;
            }
        }


        bw.write(maxSum + "\n");


        bw.flush();
        bw.close();
    }

    static int maxSum = -1;

    private static void dfs(int startX, int startY, int len, int[][] visited, int sum) {
        if (len == 4) {
            maxSum = Integer.max(maxSum, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = startX + dirX[i];
            int nextY = startY + dirY[i];

            if (isValidIdx(nextX, nextY) && visited[nextY][nextX] == 0) {
                if (len == 2) {
                    visited[nextY][nextX] = 1;
                    dfs(startX, startY, len + 1, visited, sum + paper[nextY][nextX]);
                    visited[nextY][nextX] = 0;
                }
                visited[nextY][nextX] = 1;
                dfs(nextX, nextY, len + 1, visited, sum + paper[nextY][nextX]);
                visited[nextY][nextX] = 0;
            }
        }
    }

    private static boolean isValidIdx(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}
