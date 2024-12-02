package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = parseInt(br.readLine());
        int m = parseInt(br.readLine());

        long[][] map = new long[n][n];
        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                if (from != to) map[from][to] = Long.MAX_VALUE;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");
            int from = parseInt(inputs[0]), to = parseInt(inputs[1]), cost = parseInt(inputs[2]);
            map[from - 1][to - 1] = Long.min(map[from - 1][to - 1], cost);
        }

        for (int mid = 0; mid < n; mid++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (map[from][mid] == Long.MAX_VALUE || map[mid][to] == Long.MAX_VALUE) continue;
                    map[from][to] = Long.min(map[from][to], map[from][mid] + map[mid][to]);
                }
            }
        }

        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                if (map[from][to] == Long.MAX_VALUE) bw.write("0 ");
                else bw.write(map[from][to] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
