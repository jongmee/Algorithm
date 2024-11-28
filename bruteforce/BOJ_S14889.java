package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S14889 {
    private static int[][] powers;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        powers = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                powers[i][j] = parseInt(inputs[j]);
            }
        }

        minDiff = Integer.MAX_VALUE;
        combination(0, 0, new int[N]);
        bw.write(minDiff + "\n");

        bw.flush();
        bw.close();
    }

    private static int minDiff;

    private static void combination(int startIdx, int lv, int[] team) {
        if (lv == N / 2) {
            minDiff = Integer.min(minDiff, calculateDiff(team));
            return;
        }

        for (int i = startIdx; i < N; i++) {
            if (team[i] == 0) {
                team[i] = 1;
                combination(i, lv + 1, team);
                team[i] = 0;
            }
            if (startIdx == 0 && lv == 0) break; // 절반만 봐도 됨
        }
    }

    private static int calculateDiff(int[] team) {
        int teamPower = 0, oppositePower = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (team[i] == 1 && team[j] == 1) teamPower += powers[i][j] + powers[j][i];
                if (team[i] == 0 && team[j] == 0) oppositePower += powers[i][j] + powers[j][i];
            }
        }
        return Math.abs(teamPower - oppositePower);
    }
}
