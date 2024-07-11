package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_G14391 {
    static int[][] paper, paperCheck;
    static int N, M, maxSum = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);
        paper = new int[N][M];
        paperCheck = new int[N][M];
        for (int y = 0; y < N; y++) {
            inputs = br.readLine().split("");
            for (int x = 0; x < M; x++) paper[y][x] = parseInt(inputs[x]);
        }

        cut(-1, 0);

        bw.write(maxSum + "\n");


        bw.flush();
        bw.close();
    }

    static int cnt;

    static void cut(int startX, int startY) {
        int nextX = startX + 1, nextY = startY;
        if (nextX >= M) {
            nextX = 0;
            nextY += 1;
            if (nextY >= N) {
                int rowSum = sumRow();
                int colSum = sumColumn();
                maxSum = max(rowSum + colSum, maxSum);
                return;
            }
        }
        for (int i = 0; i <= 1; i++) {
            cnt++;
            paperCheck[nextY][nextX] = i;
            cut(nextX, nextY);
            paperCheck[nextY][nextX] = 0;
        }
    }

    static int sumRow() {
        int totalSum = 0;
        for (int y = 0; y < N; y++) {
            int sum = 0;
            for (int x = 0; x < M; x++) {
                if (paperCheck[y][x] == 1) sum = sum * 10 + paper[y][x];
                else {
                    totalSum += sum;
                    sum = 0;
                }
            }
            totalSum += sum;
        }

        return totalSum;
    }

    static int sumColumn() {
        int totalSum = 0;
        for (int x = 0; x < M; x++) {
            int sum = 0;
            for (int y = 0; y < N; y++) {
                if (paperCheck[y][x] == 0) {
                    sum = sum * 10 + paper[y][x];
                } else {
                    totalSum += sum;
                    sum = 0;
                }
            }
            totalSum += sum;
        }
        return totalSum;
    }
}
