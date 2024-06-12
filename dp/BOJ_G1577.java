package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BOJ_G1577 {
    static int N, M;
    static int[] dirX = {-1, 0}; // 왼쪽, 아래
    static int[] dirY = {0, -1};
    static Map<Road, List<Road>> constructions = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]), b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]), d = Integer.parseInt(line[3]);
            Road startNode = new Road(a, b);
            Road lastNode = new Road(c, d);
            if (constructions.containsKey(startNode)) constructions.get(startNode).add(lastNode);
            else constructions.put(startNode, new ArrayList<>(List.of(lastNode)));
            if (constructions.containsKey(lastNode)) constructions.get(lastNode).add(startNode);
            else constructions.put(lastNode, new ArrayList<>(List.of(startNode)));
        }

        long[][] map = new long[M + 1][N + 1];
        map[0][0] = 1;
        for (int m = 0; m <= M; m++) {
            for (int n = 0; n <= N; n++) {
                for (int i = 0; i < 2; i++) {
                    int prevX = n + dirX[i];
                    int prevY = m + dirY[i];
                    if (isValidIdx(prevX, prevY) && !isConstructions(n, m, prevX, prevY)) {
                        map[m][n] += map[prevY][prevX];
                    }
                }
            }
        }

        bw.write(map[M][N] + "\n");

        bw.flush();
        bw.close();
    }

    private static boolean isValidIdx(int x, int y) {
        return x >= 0 && x < N + 1 && y >= 0 && y < M + 1;
    }

    private static boolean isConstructions(int x1, int y1, int x2, int y2) {
        Road road1 = new Road(x1, y1);
        Road road2 = new Road(x2, y2);
        return constructions.containsKey(road1) && constructions.get(road1).contains(road2);
    }

    static class Road {
        int x, y;

        public Road(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Road road = (Road) o;
            return x == road.x && y == road.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
