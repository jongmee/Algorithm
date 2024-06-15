package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_G10026 {
    static int[] dirX = {0, 0, -1, 1}; // 상하좌우
    static int[] dirY = {-1, 1, 0, 0};
    static int N;
    static int[][] visited;
    static char[][] picture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < N; j++) picture[i][j] = line[j].charAt(0);
        }

        int normal = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] == 0) {
                    bfs(x, y, false);
                    normal++;
                }
            }
        }

        for (int i = 0; i < N; i++) Arrays.fill(visited[i], 0);
        int disable = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] == 0) {
                    bfs(x, y, true);
                    disable++;
                }
            }
        }

        bw.write(normal + " " + disable + "\n");

        bw.flush();
        bw.close();
    }

    private static void bfs(int startX, int startY, boolean isDisable) {
        char target = picture[startY][startX];
        Queue<Color> q = new ArrayDeque<>();
        q.add(new Color(startX, startY, picture[startY][startX]));
        visited[startY][startX] = 1;

        while (!q.isEmpty()) {
            Color front = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = front.x + dirX[i], nextY = front.y + dirY[i];
                if (isValidIdx(nextX, nextY) && visited[nextY][nextX] == 0 && isSameColor(nextY, nextX, target, isDisable)) {
                    visited[nextY][nextX] = 1;
                    q.add(new Color(nextX, nextY, picture[nextY][nextX]));
                }
            }
        }
    }

    private static boolean isSameColor(int nextY, int nextX, char target, boolean isDisable) {
        if (!isDisable || target == 'B') return picture[nextY][nextX] == target;
        return picture[nextY][nextX] != 'B';
    }

    private static boolean isValidIdx(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class Color {
        int x, y;
        char val;

        public Color(int x, int y, char val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

}
