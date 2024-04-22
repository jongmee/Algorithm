package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S14940 {
    static int[] DIR_X = {0, 0, -1, 1}; // 상하좌우
    static int[] DIR_Y = {-1, 1, 0, 0};
    static final int START_POINT = 2;
    static final int MOVE_POINT = 1;
    static final int CANT_MOVE_POINT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = parseInt(inputs[0]), m = parseInt(inputs[1]);

        int startX = -1;
        int startY = -1;
        int[][] map = new int[n + 2][m + 2];
        for (int y = 1; y <= n; y++) {
            inputs = br.readLine().split(" ");
            for (int x = 1; x <= m; x++) {
                int num = parseInt(inputs[x - 1]);
                map[y][x] = num;
                if (num == START_POINT) {
                    startX = x;
                    startY = y;
                }
            }
        }

        int[][] distances = bfs(n, m, map, startX, startY);

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (map[y][x] == CANT_MOVE_POINT) distances[y][x] = 0;
                bw.write(distances[y][x] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static int[][] bfs(int n, int m, int[][] map, int startX, int startY) {
        int[][] distances = new int[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) Arrays.fill(distances[i], -1);
        int[][] visited = new int[n + 2][m + 2];

        Queue<Point> q = new ArrayDeque<>(n * m);
        q.add(new Point(startX, startY));
        visited[startY][startX] = 1;
        distances[startY][startX] = 0;

        while (!q.isEmpty()) {
            Point front = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextX = front.x + DIR_X[dir];
                int nextY = front.y + DIR_Y[dir];
                if (visited[nextY][nextX] == 0) {
                    visited[nextY][nextX] = 1;
                    if (map[nextY][nextX] == MOVE_POINT) {
                        q.add(new Point(nextX, nextY));
                        distances[nextY][nextX] = distances[front.y][front.x] + 1;
                    }
                }
            }
        }
        return distances;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
