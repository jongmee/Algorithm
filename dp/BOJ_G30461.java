package dp;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_G30461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]), Q = parseInt(inputs[2]);

        int[][] sea = new int[N + 2][M + 2];
        int[][] verticalSum = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                int now = parseInt(inputs[j - 1]);
                verticalSum[i][j] = verticalSum[i - 1][j] + now;
                sea[i][j] = verticalSum[i - 1][j] + now + sea[i - 1][j - 1];
            }
        }

        for (int q = 0; q < Q; q++) {
            inputs = br.readLine().split(" ");
            int y = parseInt(inputs[0]), x = parseInt(inputs[1]);
            bw.write(sea[y][x] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
