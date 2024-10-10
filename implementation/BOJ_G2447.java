package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G2447 {
    private static char[][] ANS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        ANS = new char[N][N];
        draw(N, '*', 0, 0);

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                bw.write(ANS[r][c]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void draw(int depth, char printer, final int startR, final int startC) throws IOException {
        if (depth == 1) {
            for (int r = startR; r < 3 + startR; r++) {
                for (int c = startC; c < 3 + startC; c++) {
                    if (r == 1 && c == 1) ANS[startR][startC] = ' ';
                    else ANS[startR][startC] = printer;
                }
            }
            return;
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (r == 1 && c == 1) draw(depth / 3, ' ', startR + r * depth / 3, startC + c * depth / 3);
                else draw(depth / 3, printer, startR + r * depth / 3, startC + c * depth / 3);
            }
        }
    }
}
