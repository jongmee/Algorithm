package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1992 {
    private static String[][] video;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        video = new String[N][N];
        for (int r = 0; r < N; r++) {
            String[] line = br.readLine().split("");
            for (int c = 0; c < N; c++) video[r][c] = line[c];
        }

        bw.write(compact(0, 0, N) + "\n");


        bw.flush();
        bw.close();
    }

    private static String compact(int startR, int startC, int width) {
        if (width == 1) return video[startR][startC];
        String val1 = compact(startR, startC, width / 2);
        String val2 = compact(startR, startC + width / 2, width / 2);
        String val3 = compact(startR + width / 2, startC, width / 2);
        String val4 = compact(startR + width / 2, startC + width / 2, width / 2);
        if (val1.length() == 1 && val1.equals(val2) && val2.equals(val3) && val3.equals(val4)) {
            return val1;
        }
        return "(" + val1 + val2 + val3 + val4 + ")";
    }
}
