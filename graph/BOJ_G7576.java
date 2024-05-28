package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G7576 {
    static int[] dirX = {0, 0, -1, 1}; // 상 하 좌 우
    static int[] dirY = {-1, 1, 0, 0};
    static int EMPTY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int M = parseInt(inputs[0]), N = parseInt(inputs[1]);

        int[][] box = new int[N + 2][M + 2];
        for (int y = 0; y < N + 2; y++) Arrays.fill(box[y], EMPTY);

        List<Square> starts = new ArrayList<>();
        int totalCnt = 0;
        for (int y = 0; y < N; y++) {
            inputs = br.readLine().split(" ");
            for (int x = 0; x < M; x++) {
                int val = Integer.parseInt(inputs[x]);
                if (val == 1) {
                    box[y + 1][x + 1] = 1;
                    starts.add(new Square(x + 1, y + 1, 0));
                } else box[y + 1][x + 1] = val;
                if (val != EMPTY) totalCnt++;
            }
        }

        int[][] visited = new int[N + 2][M + 2];
        Queue<Square> q = new ArrayDeque<>();
        for (Square square : starts) {
            q.add(square);
            visited[square.y][square.x] = 1;
        }
        int ans = -1;
        while (!q.isEmpty()) {
            Square front = q.poll();
            totalCnt--;
            if (front.days > ans) ans = front.days;

            for (int i = 0; i < 4; i++) {
                int nextX = front.x + dirX[i];
                int nextY = front.y + dirY[i];
                if (visited[nextY][nextX] == 0 && box[nextY][nextX] != EMPTY) {
                    visited[nextY][nextX] = 1;
                    q.add(new Square(nextX, nextY, front.days + 1));
                }
            }
        }

        if (totalCnt != 0) bw.write("-1\n");
        else bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    static class Square {
        int x, y, days;

        public Square(int x, int y, int days) {
            this.x = x;
            this.y = y;
            this.days = days;
        }
    }
}
