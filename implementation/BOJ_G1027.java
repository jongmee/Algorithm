package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G1027 {
    static int[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        buildings = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) buildings[i] = parseInt(line[i]);

        int[] counts = new int[N];
        for (int i = 0; i < N - 1; i++) {
            counts[i]++;
            counts[i + 1]++;
            double slope = calculateScope(i, i + 1);
            for (int j = i + 2; j < N; j++) {
                double nextSlope = calculateScope(i, j);
                if (nextSlope > slope) {
                    slope = nextSlope;
                    counts[i]++;
                    counts[j]++;
                }
            }
        }
        int max = -1;
        for (int count : counts) max = Integer.max(max, count);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    static double calculateScope(int idx1, int idx2) {
        return (double) (buildings[idx2] - buildings[idx1]) / (idx2 - idx1);
    }

}
