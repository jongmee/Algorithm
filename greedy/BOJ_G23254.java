package greedy;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.*;

public class BOJ_G23254 {
    static int PERFECT_SCORE = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        String[] baseScoresInput = br.readLine().split(" ");
        int[] baseScores = new int[M];
        String[] incs = br.readLine().split(" ");

        Queue<Score> pq = new PriorityQueue<>();
        for (int m = 0; m < M; m++) {
            baseScores[m] = parseInt(baseScoresInput[m]);
            pq.add(new Score(baseScores[m], parseInt(incs[m])));
        }

        int restHour = N * 24;
        long totalScore = 0;
        while (restHour > 0 && !pq.isEmpty()) {
            Score front = pq.poll();
            int restScore = PERFECT_SCORE - front.base;
            if (restScore >= 0) {
                int time = restScore / front.inc;
                if (time < restHour) {
                    totalScore += (long) time * front.inc;
                    front.base += time * front.inc;
                    restHour -= time;
                } else {
                    totalScore += (long) restHour * front.inc;
                    front.base += restHour * front.inc;
                    restHour = 0;
                }
                if (front.base < PERFECT_SCORE && restHour != 0) {
                    front.inc = min(front.inc, PERFECT_SCORE - front.base);
                    pq.add(front);
                }
            }
        }

        for (int score : baseScores) totalScore += score;
        bw.write(totalScore + "\n");

        bw.flush();
        bw.close();
    }

    static class Score implements Comparable<Score> {
        int base, inc;

        public Score(int base, int inc) {
            this.base = base;
            this.inc = inc;
        }

        @Override
        public int compareTo(Score o) {
            return Integer.compare(o.inc, inc);
        }
    }
}
