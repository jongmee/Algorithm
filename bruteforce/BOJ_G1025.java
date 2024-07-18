package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G1025 {
    /*
    등차수열: an= a + (n - 1) * d
    a, a + d, a + 2d, a + 3d, a + 4d

    초항(a)은 N * M 개
    초항마다 행과 열의 d는 N * M 개
     */
    static int N, M;
    static int[][] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);
        table = new int[N][M];
        for (int r = 0; r < N; r++) {
            inputs = br.readLine().split("");
            for (int c = 0; c < M; c++) table[r][c] = parseInt(inputs[c]);
        }

        for (int startRow = 0; startRow < N; startRow++) {
            for (int startCol = 0; startCol < M; startCol++) {
                for (int rowD = 0; rowD < N; rowD++) {
                    for (int colD = 0; colD < M; colD++) {
                        calculate(rowD, colD, startRow, startCol);
                        calculate(rowD * -1, colD, startRow, startCol);
                        calculate(rowD, colD * -1, startRow, startCol);
                        calculate(rowD * -1, colD * -1, startRow, startCol);
                    }
                }
            }
        }

        bw.write(maxSquareNum + "\n");
        bw.flush();
        bw.close();
    }

    static int maxSquareNum = -1;

    static void calculate(int rowD, int colD, int startRow, int startCol) {
        if (rowD == 0 && colD == 0) {
            if (isSquareNum(table[startRow][startCol]))
                maxSquareNum = Integer.max(maxSquareNum, table[startRow][startCol]);
            return;
        }
        int row = startRow, col = startCol, sum = 0;
        while (row < N && col < M & row >= 0 && col >= 0) {
            sum = sum * 10 + table[row][col];
            if (isSquareNum(sum)) {
                maxSquareNum = Integer.max(maxSquareNum, sum);
            }
            row += rowD;
            col += colD;
        }
    }

    static boolean isSquareNum(int num) {
        for (int i = 0; i * i <= num; i++) {
            if (i * i == num) return true;
        }
        return false;
    }
}
