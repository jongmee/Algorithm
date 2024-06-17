package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G16946 {
    static int[][] map, visited, ans;
    static Count[][] cnts;
    static int N, M;
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        map = new int[N][M];
        visited = new int[N][M];
        ans = new int[N][M];
        cnts = new Count[N][M];

        for (int y = 0; y < N; y++) {
            inputs = br.readLine().split("");
            for (int x = 0; x < M; x++) map[y][x] = Integer.parseInt(inputs[x]);
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (visited[y][x] == 0 && map[y][x] == 0) {
                    visited[y][x] = 1;
                    Count count = new Count(1);
                    cnts[y][x] = count;
                    dfs(y, x, count);
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 1) {
                    ans[y][x] = 1;
                    Set<Count> candidates = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int nextY = y + dirY[i], nextX = x + dirX[i];
                        if (isValidIdx(nextX, nextY) && cnts[nextY][nextX] != null
                                && !candidates.contains(cnts[nextY][nextX])) {
                            candidates.add(cnts[nextY][nextX]);
                            ans[y][x] += cnts[nextY][nextX].cnt;
                        }
                    }
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) bw.write((ans[y][x] % 10) + "");
            bw.write("\n");
        }


        bw.flush();
        bw.close();
    }

    static void dfs(int startY, int startX, Count count) {
        for (int i = 0; i < 4; i++) {
            int nextX = startX + dirX[i], nextY = startY + dirY[i];
            if (isValidIdx(nextX, nextY) && visited[nextY][nextX] == 0 && map[nextY][nextX] == 0) {
                visited[nextY][nextX] = 1;
                count.cnt += 1;
                cnts[nextY][nextX] = count;
                dfs(nextY, nextX, count);
            }
        }
    }

    static boolean isValidIdx(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static class Count {
        int cnt;

        public Count(int cnt) {
            this.cnt = cnt;
        }
    }
}
