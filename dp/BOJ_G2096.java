package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G2096 {
    static int[] dirX = {-1, 0, 1};
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) board[i][j] = Integer.parseInt(line[j]);
        }

        int[][] maxAns = new int[N][3];
        int[][] minAns = new int[N][3];
        for (int i = 0; i < N; i++) Arrays.fill(minAns[i], Integer.MAX_VALUE);

        for (int i = 0; i < 3; i++) {
            maxAns[0][i] = board[0][i];
            minAns[0][i] = board[0][i];
        }

        for (int y = 1; y < N; y++) {
            for (int x = 0; x < 3; x++) {
                for (int dir = 0; dir < 3; dir++) {
                    int prevX = dirX[dir] + x;
                    int prevY = y - 1;
                    if (isValidIdx(prevX, prevY)) {
                        maxAns[y][x] = Integer.max(maxAns[y][x], maxAns[prevY][prevX] + board[y][x]);
                        minAns[y][x] = Integer.min(minAns[y][x], minAns[prevY][prevX] + board[y][x]);
                    }
                }
            }
        }

        int maxPoint = -1;
        for (int x = 0; x < 3; x++) maxPoint = Integer.max(maxPoint, maxAns[N - 1][x]);

        int minPoint = Integer.MAX_VALUE;
        for (int x = 0; x < 3; x++) minPoint = Integer.min(minPoint, minAns[N - 1][x]);

        bw.write(maxPoint + " " + minPoint + "\n");


        bw.flush();
        bw.close();
    }

    private static boolean isValidIdx(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < N;
    }

}
