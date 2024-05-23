package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_4179 {
    static final int[] dirC = {0, 0, -1, 1}; // 상하좌우
    static final int[] dirR = {-1, 1, 0, 0};

    static char[][] maze;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        R = parseInt(inputs[0]);
        C = parseInt(inputs[1]);

        maze = new char[R][C];
        int startR = -1, startC = -1;
        List<Point> fires = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            inputs = br.readLine().split("");
            for (int c = 0; c < C; c++) {
                maze[r][c] = inputs[c].charAt(0);
                if (maze[r][c] == 'J') {
                    startC = c;
                    startR = r;
                } else if (maze[r][c] == 'F') fires.add(new Point(c, r, 0));
            }
        }

        int[][] times = new int[R][C];
        for (int r = 0; r < R; r++) Arrays.fill(times[r], Integer.MAX_VALUE);
        times[startR][startC] = 0;

        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(startC, startR, 0));
        dijkstra(times, pq);

        int[][] fireTimes = new int[R][C];
        for (int r = 0; r < R; r++) Arrays.fill(fireTimes[r], Integer.MAX_VALUE);

        for (Point firePoint : fires) {
            fireTimes[firePoint.r][firePoint.c] = 0;
            pq.add(new Point(firePoint.c, firePoint.r, 0));
        }
        dijkstra(fireTimes, pq);

        int minTime = getMinTime(times, fireTimes);

        if (minTime == Integer.MAX_VALUE) bw.write("IMPOSSIBLE\n");
        else bw.write((minTime + 1) + "\n");

        bw.flush();
        bw.close();
    }

    static void dijkstra(int[][] times, Queue<Point> pq) {
        while (!pq.isEmpty()) {
            Point front = pq.poll();
            if (front.cost > times[front.r][front.c]) continue;

            for (int i = 0; i < 4; i++) {
                int nextR = front.r + dirR[i];
                int nextC = front.c + dirC[i];
                if (checkIdx(nextR, false) && checkIdx(nextC, true) &&
                        times[nextR][nextC] > front.cost + 1 && maze[nextR][nextC] != '#') {
                    pq.add(new Point(nextC, nextR, front.cost + 1));
                    times[nextR][nextC] = front.cost + 1;
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int c, r, cost;

        public Point(int c, int r, int cost) {
            this.c = c;
            this.r = r;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(cost, o.cost);
        }
    }

    static boolean checkIdx(int idx, boolean isC) {
        if (isC) {
            return idx >= 0 && idx < C;
        } else {
            return idx >= 0 && idx < R;
        }
    }

    private static int getMinTime(int[][] times, int[][] fireTimes) {
        int minTime = Integer.MAX_VALUE;
        for (int r = 0; r < R; r++) {
            if (times[r][0] < fireTimes[r][0]) minTime = Integer.min(minTime, times[r][0]);
            if (times[r][C - 1] < fireTimes[r][C - 1]) minTime = Integer.min(minTime, times[r][C - 1]);
        }
        for (int c = 0; c < C; c++) {
            if (times[0][c] < fireTimes[0][c]) minTime = Integer.min(minTime, times[0][c]);
            if (times[R - 1][c] < fireTimes[R - 1][c]) minTime = Integer.min(minTime, times[R - 1][c]);
        }
        return minTime;
    }
}
