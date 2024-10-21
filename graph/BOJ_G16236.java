package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_G16236 {
    private static int[] dirX = {0, 0, -1, 1}, dirY = {-1, 1, 0, 0};
    private static int N, FISH_CNT, EAT_CNT, BABY_SHARK_SIZE, TIME;
    private static int[][] map, dists;
    private static boolean CANNOT_GO;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        FISH_CNT = 0;
        EAT_CNT = 0;
        BABY_SHARK_SIZE = 2;
        CANNOT_GO = false;
        TIME = 0;
        int startX = 0, startY = 0;
        map = new int[N][N];
        dists = new int[N][N];
        for (int y = 0; y < N; y++) {
            String[] line = br.readLine().split(" ");
            for (int x = 0; x < N; x++) {
                map[y][x] = parseInt(line[x]);
                if (map[y][x] == 9) {
                    startY = y;
                    startX = x;
                } else if (map[y][x] != 0) FISH_CNT++;
            }
        }
        map[startY][startX] = 0;
        dfs(startX, startY);
        bw.write(TIME + "\n");

        bw.flush();
        bw.close();
    }

    private static void dfs(int startX, int startY) {
        if (FISH_CNT == 0) return;
        if (CANNOT_GO) return;

        for (int i = 0; i < N; i++) Arrays.fill(dists[i], Integer.MAX_VALUE);
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(startX, startY, 0));
        dists[startY][startX] = 0;
        int nextX = -1, nextY = -1;
        while (!pq.isEmpty()) {
            Point front = pq.poll();
            if (!(front.x == startX && front.y == startY)
                    && map[front.y][front.x] != 0
                    && map[front.y][front.x] < BABY_SHARK_SIZE) {
                nextX = front.x;
                nextY = front.y;
                TIME += front.dist;
                break;
            }
            if (front.dist > dists[front.y][front.x]) continue;

            for (int dir = 0; dir < 4; dir++) {
                int tmpX = front.x + dirX[dir], tmpY = front.y + dirY[dir];
                if (!isValid(tmpX, tmpY)) continue;
                if (map[tmpY][tmpX] <= BABY_SHARK_SIZE && dists[tmpY][tmpX] > front.dist + 1) {
                    dists[tmpY][tmpX] = front.dist + 1;
                    pq.add(new Point(tmpX, tmpY, front.dist + 1));
                }
            }
        }

        if (nextX == -1) {
            CANNOT_GO = true;
            return;
        }
        map[nextY][nextX] = 0;
        FISH_CNT--;
        EAT_CNT++;
        if (EAT_CNT == BABY_SHARK_SIZE) {
            BABY_SHARK_SIZE++;
            EAT_CNT = 0;
        }
        dfs(nextX, nextY);
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static class Point implements Comparable<Point> {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist == o.dist && this.y == o.y) return Integer.compare(this.x, o.x);
            if (this.dist == o.dist) return Integer.compare(this.y, o.y);
            return Integer.compare(this.dist, o.dist);
        }
    }
}
