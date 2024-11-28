package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
        combination(0, 1, new ArrayDeque<>(List.of(0)));
        bw.write(minDiff + "\n");

        bw.flush();
        bw.close();
    }

    private static int minDiff;

    private static void combination(int startIdx, int lv, Deque<Integer> team) {
        if (lv == N / 2) {
            minDiff = Integer.min(minDiff, calculateDiff(new ArrayList<>(team)));
            return;
        }

        for (int i = startIdx + 1; i < N; i++) {
            team.addLast(i);
            combination(i, lv + 1, team);
            team.pollLast();
        }
    }

    private static int calculateDiff(List<Integer> team) {
        List<Integer> opposite = new ArrayList<>();
        for (int i = 0; i < N; i++)
            if (!team.contains(i)) opposite.add(i);
        int teamPower = 0, oppositePower = 0;
        teamPower = getTotalTeamPower(team, teamPower);
        oppositePower = getTotalTeamPower(opposite, oppositePower);
        return Math.abs(teamPower - oppositePower);
    }

    private static int getTotalTeamPower(List<Integer> team, int teamPower) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                teamPower += powers[team.get(i)][team.get(j)] + powers[team.get(j)][team.get(i)];
            }
        }
        return teamPower;
    }
}
