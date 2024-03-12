package graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S2468 {
    /*
     높이: 1이상 100 이하의 정수
     N: 2 이상 100 이하의 정수
     높이 * N * N = 1000000
     */
    static int[] dX = {0, 0, -1, 1}; // 상하좌우
    static int[] dY = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[][] heights = new int[N + 2][N + 2];
        int maxHeight = -1;
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                heights[i + 1][j + 1] = parseInt(line[j]);
                if (heights[i + 1][j + 1] > maxHeight) maxHeight = heights[i + 1][j + 1];
            }
        }

        int result = -1;
        for (int condition = 0; condition <= maxHeight; condition++) {
            int cnt = bfsByCondition(N, heights, condition);
            if (cnt > result) result = cnt;
        }
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    private static int bfsByCondition(int N, int[][] heights, int condition) {
        int[][] visited = new int[N + 2][N + 2];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j] == 0 && heights[i][j] > condition) {
                    bfs(heights, visited, new Point(j, i), condition);
                    cnt++;
                }
            }
        }
        return cnt;
    }


    private static void bfs(int[][] heights, int[][] visited, Point start, int condition) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.y][start.x] = 1;
        while (!q.isEmpty()) {
            Point front = q.poll();
            for (int i = 0; i < 4; i++) {
                Point next = new Point(front.x + dX[i], front.y + dY[i]);
                if (visited[next.y][next.x] == 0 && heights[next.y][next.x] > condition) {
                    q.add(next);
                    visited[next.y][next.x] = 1;
                }
            }
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
