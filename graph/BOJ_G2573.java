package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G2573 {
    private static int[][] map;
    private static int[] dirR = {-1, 1, 0, 0}, dirC = {0, 0, -1, 1};
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < M; c++) map[r][c] = parseInt(inputs[c]);
        }

        int groupCnt = -1, time = 0;
        while (true) {
            if (groupCnt == 0 || groupCnt >= 2) break;
            melt();
            groupCnt = getGroupCnt();
            time++;
        }

        if (groupCnt >= 2) bw.write(time + "\n");
        else bw.write("0\n");

        bw.flush();
        bw.close();
    }

    private static void melt() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) continue;
                int cnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nextR = r + dirR[dir], nextC = c + dirC[dir];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
                    if (map[nextR][nextC] == 0) cnt++;
                }
                if (map[r][c] - cnt <= 0) map[r][c] = -1;
                else map[r][c] -= cnt;
            }
        }

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] == -1) map[r][c] = 0;
    }

    private static int getGroupCnt() {
        int[][] visited = new int[N][M];
        int cnt = 0;
        Queue<Node> q = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c] == 0 && map[r][c] != 0) {
                    cnt++;
                    q.add(new Node(r, c));
                    visited[r][c] = 1;
                    while (!q.isEmpty()) {
                        Node front = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nextR = front.r + dirR[dir], nextC = front.c + dirC[dir];
                            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
                            if (map[nextR][nextC] != 0 && visited[nextR][nextC] == 0) {
                                visited[nextR][nextC] = 1;
                                q.add(new Node(nextR, nextC));
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }

    private static void printMap() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) System.out.print(map[r][c] + " ");
            System.out.println();
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
