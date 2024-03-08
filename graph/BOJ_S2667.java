package graph;

import java.io.*;
import java.util.*;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;

public class BOJ_S2667 {
    static int[] dx = {0, 0, -1, 1};  // 상하좌우
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        int[][] map = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) map[i][j] = getNumericValue(line.charAt(j - 1));
        }

        List<Integer> results = new ArrayList<>();
        Stack<Point> stk = new Stack<>();
        int[][] visited = new int[N + 2][N + 2];

        for (int i = 1; i <= N; i++) { // y
            for (int j = 1; j <= N; j++) { // x
                if (visited[i][j] == 0 && map[i][j] == 1) {
                    results.add(dfs(stk, visited, map, j, i));
                }
            }
        }
        Collections.sort(results);

        StringBuilder sb = new StringBuilder();
        sb.append(results.size()).append("\n");
        for (Integer r : results) sb.append(r).append("\n");
        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static int dfs(Stack<Point> stk, int[][] visited, int[][] map, int startX, int startY) {
        int result = 0;
        stk.push(new Point(startX, startY));
        visited[startY][startX] = 1;
        while (!stk.empty()) {
            Point top = stk.pop();
            result++;
            for (int dir = 0; dir < 4; dir++) {
                int nextX = top.x + dx[dir];
                int nextY = top.y + dy[dir];
                if (visited[nextY][nextX] == 0 && map[nextY][nextX] == 1) {
                    stk.push(new Point(nextX, nextY));
                    visited[nextY][nextX] = 1;
                }
            }
        }
        stk.clear();
        return result;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
