package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_G15683 {
    /*
    cctv는 통과할 수 있지만 벽은 통과할 수 없음
    0의 최소 (#의 최대)
     */
    private static final int WALL = 6, ZERO = 0;
    private static final int[] dirX = {0, 0, -1, 1}, dirY = {-1, 1, 0, 0};
    private static final Map<Integer, Integer> DIRECTIONS = Map.of(
            1, 4, 2, 2, 3, 4, 4, 4, 5, 1
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        int map[][] = new int[N][M];
        List<Integer> cctvC = new ArrayList<>(), cctvR = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = parseInt(inputs[c]);
                if (map[r][c] != WALL && map[r][c] != ZERO) {
                    cctvC.add(c);
                    cctvR.add(r);
                }
            }
        }

        dfs(cctvC, cctvR, 0, map, N, M);
        bw.write(minAns + "\n");

        bw.flush();
        bw.close();
    }

    private static int minAns = Integer.MAX_VALUE;
    private static List<Integer> dis = new ArrayList<>();

    private static void dfs(List<Integer> cctvC, List<Integer> cctvR, int startIdx, int[][] map, int N, int M) { // startIdx는 0부터
        if (startIdx == cctvC.size()) {
            int zeroCnt = countZero(map, N, M);
            minAns = Integer.min(minAns, zeroCnt);
            return;
        }
        int cctv = map[cctvR.get(startIdx)][cctvC.get(startIdx)];
        for (int i = 0; i < DIRECTIONS.get(cctv); i++) {
            List<Point> points;
            if (cctv == 1) points = one(i, map, cctvC.get(startIdx), cctvR.get(startIdx), N, M);
            else if (cctv == 2) points = two(i, map, cctvC.get(startIdx), cctvR.get(startIdx), N, M);
            else if (cctv == 3) points = three(i, map, cctvC.get(startIdx), cctvR.get(startIdx), N, M);
            else if (cctv == 4) points = four(i, map, cctvC.get(startIdx), cctvR.get(startIdx), N, M);
            else points = five(map, cctvC.get(startIdx), cctvR.get(startIdx), N, M);
            dis.add(i);
            dfs(cctvC, cctvR, startIdx + 1, map, N, M);
            dis.remove(dis.size() - 1);
            restore(points, map);
        }
    }

    private static void printMap(int[][] map, int N, int M) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int countZero(int[][] map, int N, int M) {
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == ZERO) cnt++;
            }
        }
        return cnt;
    }

    private static List<Point> one(int idx, int[][] map, int startC, int startR, int N, int M) {
        if (idx == 0) return check(startC, startR, N, M, map, 0); // 상
        if (idx == 1) return check(startC, startR, N, M, map, 1);// 하
        if (idx == 2) return check(startC, startR, N, M, map, 2); // 좌
        return check(startC, startR, N, M, map, 3); // 우
    }

    private static List<Point> two(int idx, int[][] map, int startC, int startR, int N, int M) {
        if (idx == 0) {
            List<Point> points = check(startC, startR, N, M, map, 0);
            points.addAll(check(startC, startR, N, M, map, 1));
            return points;
        }
        List<Point> points = check(startC, startR, N, M, map, 2);
        points.addAll(check(startC, startR, N, M, map, 3));
        return points;
    }

    private static List<Point> three(int idx, int[][] map, int startC, int startR, int N, int M) {
        if (idx == 0) {
            List<Point> points = check(startC, startR, N, M, map, 0);
            points.addAll(check(startC, startR, N, M, map, 3));
            return points;
        }
        if (idx == 1) {
            List<Point> points = check(startC, startR, N, M, map, 3);
            points.addAll(check(startC, startR, N, M, map, 1));
            return points;
        }
        if (idx == 2) {
            List<Point> points = check(startC, startR, N, M, map, 1);
            points.addAll(check(startC, startR, N, M, map, 2));
            return points;
        }
        List<Point> points = check(startC, startR, N, M, map, 2);
        points.addAll(check(startC, startR, N, M, map, 0));
        return points;
    }

    private static List<Point> four(int idx, int[][] map, int startC, int startR, int N, int M) {
        if (idx == 0) {
            List<Point> points = check(startC, startR, N, M, map, 0);
            points.addAll(check(startC, startR, N, M, map, 1));
            points.addAll(check(startC, startR, N, M, map, 2));
            return points;
        }
        if (idx == 1) {
            List<Point> points = check(startC, startR, N, M, map, 1);
            points.addAll(check(startC, startR, N, M, map, 2));
            points.addAll(check(startC, startR, N, M, map, 3));
            return points;
        }
        if (idx == 2) {
            List<Point> points = check(startC, startR, N, M, map, 2);
            points.addAll(check(startC, startR, N, M, map, 3));
            points.addAll(check(startC, startR, N, M, map, 0));
            return points;
        }
        List<Point> points = check(startC, startR, N, M, map, 3);
        points.addAll(check(startC, startR, N, M, map, 0));
        points.addAll(check(startC, startR, N, M, map, 1));
        return points;

    }

    private static List<Point> five(int[][] map, int startC, int startR, int N, int M) {
        List<Point> points = check(startC, startR, N, M, map, 0);
        points.addAll(check(startC, startR, N, M, map, 1));
        points.addAll(check(startC, startR, N, M, map, 2));
        points.addAll(check(startC, startR, N, M, map, 3));
        return points;
    }

    private static List<Point> check(int startC, int startR, int N, int M, int[][] map, int dirIdx) {
        int dirC = dirX[dirIdx], dirR = dirY[dirIdx];
        int nextC = startC, nextR = startR;
        List<Point> points = new ArrayList<>();
        while (isValidIdx(nextR, nextC, N, M)) {
            if (DIRECTIONS.containsKey(map[nextR][nextC])) {
                nextR += dirR;
                nextC += dirC;
                continue;
            }
            if (map[nextR][nextC] == WALL) return points;
            else if (map[nextR][nextC] <= ZERO) {
                map[nextR][nextC] -= 1;
                points.add(new Point(nextC, nextR));
            }
            nextR += dirR;
            nextC += dirC;
        }
        return points;
    }

    private static void restore(List<Point> points, int[][] map) {
        for (Point point : points) map[point.y][point.x] += 1;
    }

    private static boolean isValidIdx(int r, int c, int N, int M) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
