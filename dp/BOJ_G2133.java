package dp;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_G2133 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int tile[] = new int[N + 1];

        tile[0] = 1;
        for (int i = 2; i <= N; i += 2) {
            tile[i] = tile[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                tile[i] += tile[j] * 2;
            }
        }

        bw.write(String.valueOf(tile[N]));
        bw.flush();
        bw.close();
    }
}
