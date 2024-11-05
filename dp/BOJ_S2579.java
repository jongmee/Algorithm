package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] steps = new int[N];
        for (int i = 0; i < N; i++) steps[i] = parseInt(br.readLine());

        if (N == 1) bw.write(steps[0] + "\n");
        else if (N == 2) bw.write((steps[0] + steps[1]) + "\n");
        else {
            int[] maxScores = new int[N];
            maxScores[0] = steps[0];
            maxScores[1] = steps[0] + steps[1];
            maxScores[2] = Integer.max(maxScores[0] + steps[2], steps[1] + steps[2]);
            for (int i = 3; i < N; i++)
                maxScores[i] = Integer.max(maxScores[i - 2] + steps[i], maxScores[i - 3] + steps[i - 1] + steps[i]);

            bw.write(maxScores[N - 1] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
