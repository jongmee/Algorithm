package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G15686 {
    static int N, M, storeCnt;
    static int[] dirX = {0, 0, -1, 1}; // 상하좌우
    static int[] dirY = {-1, 1, 0, 0};
    static List<Point> stores = new ArrayList<>(), homes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);
        for (int y = 0; y < N; y++) {
            inputs = br.readLine().split(" ");
            for (int x = 0; x < N; x++) {
                int val = parseInt(inputs[x]);
                if (val == 1) homes.add(new Point(x, y));
                else if (val == 2) stores.add(new Point(x, y));
            }
        }
        storeCnt = stores.size();

        pickStores(0, new int[N][N], -1);

        bw.write(minDist + "\n");

        bw.flush();
        bw.close();
    }

    static int minDist = Integer.MAX_VALUE;

    static void pickStores(int depth, int[][] storeMap, int startIdx) {
        if (M == depth) {
            minDist = Integer.min(calculateTotalDist(storeMap), minDist);
            return;
        }

        for (int i = startIdx + 1; i < storeCnt; i++) {
            Point storePoint = stores.get(i);
            storeMap[storePoint.y][storePoint.x] = 1;
            pickStores(depth + 1, storeMap, i);
            storeMap[storePoint.y][storePoint.x] = 0;
        }
    }

    static int calculateTotalDist(int[][] storeMap) {
        Queue<Point> q = new ArrayDeque<>();
        int totalDist = 0;
        for (Point homePoint : homes) {
            q.clear();
            int[][] visited = new int[N][N];

            q.add(homePoint);
            visited[homePoint.y][homePoint.x] = 1;
            while (!q.isEmpty()) {
                Point front = q.poll();
                if (storeMap[front.y][front.x] == 1) {
                    totalDist += calculateDist(homePoint.x, homePoint.y, front.x, front.y);
                    if (totalDist > minDist) return Integer.MAX_VALUE;
                    break;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nextX = front.x + dirX[dir], nextY = front.y + dirY[dir];
                    if (isValidIdx(nextX, nextY) && visited[nextY][nextX] == 0) {
                        visited[nextY][nextX] = 1;
                        q.add(new Point(nextX, nextY));
                    }
                }
            }
        }
        return totalDist;
    }

    static int calculateDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static boolean isValidIdx(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
